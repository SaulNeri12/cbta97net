package mx.edu.cbta.sistemaescolar.personal.service;

import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.repository.ActividadParaescolarRepository;
import mx.edu.cbta.sistemaescolar.paraescolar.service.GrupoParaescolarService;
import mx.edu.cbta.sistemaescolar.paraescolar.service.impl.ActividadParaescolarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActividadParaescolarServiceTest {

    @Mock
    private ActividadParaescolarRepository repository;

    @Mock
    private GrupoParaescolarService grupoParaescolarService; // Mock de la dependencia externa

    @InjectMocks
    private ActividadParaescolarServiceImpl service;

    private ActividadParaescolar actividad;

    @BeforeEach
    void setUp() {
        actividad = new ActividadParaescolar();
        actividad.setId(1L);
        actividad.setNombre("Fútbol");
        actividad.setDescripcion("Equipo varonil");
        actividad.setHorario("Lun-Mie 16:00");
        actividad.setCupoMaximo(20);
    }

    // --- PRUEBAS DE CREAR (Ref: SD_Crear Actividad) ---

    @Test
    void crearActividad_Exito() {
        // GIVEN: No existe una actividad con ese nombre
        when(repository.findByNombre("Fútbol")).thenReturn(Optional.empty());
        when(repository.save(any(ActividadParaescolar.class))).thenReturn(actividad);

        // WHEN: Se llama al servicio
        ActividadParaescolar resultado = service.crearActividadParaescolar(actividad);

        // THEN: Se guarda y retorna correctamente
        assertNotNull(resultado);
        assertEquals("Fútbol", resultado.getNombre());
        verify(repository).save(actividad);
    }

    @Test
    void crearActividad_FallaPorNombreDuplicado() {
        // GIVEN: Ya existe "Fútbol" en la BD
        when(repository.findByNombre("Fútbol")).thenReturn(Optional.of(actividad));

        // WHEN & THEN: Se espera una excepción
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.crearActividadParaescolar(actividad);
        });

        assertEquals("La actividad paraescolar ya existe con ese nombre.", exception.getMessage());
        verify(repository, never()).save(any()); // Asegura que NUNCA se guardó
    }

    // --- PRUEBAS DE MODIFICAR (Ref: SD_Modificar Paraescolar) ---

    @Test
    void modificarActividad_Exito() {
        // GIVEN: La actividad existe y el nuevo nombre no choca con otra
        ActividadParaescolar cambios = new ActividadParaescolar();
        cambios.setNombre("Fútbol Pro");
        cambios.setCupoMaximo(25);
        // (Otros campos necesarios para que no sean null si tu entidad lo requiere)
        cambios.setDescripcion("Desc");
        cambios.setHorario("H");

        when(repository.findById(1L)).thenReturn(Optional.of(actividad));
        when(repository.existsByNombreAndIdNot("Fútbol Pro", 1L)).thenReturn(false);
        when(repository.save(any(ActividadParaescolar.class))).thenReturn(actividad);

        // WHEN
        ActividadParaescolar resultado = service.modificarParaescolar(1L, cambios);

        // THEN
        assertEquals("Fútbol Pro", resultado.getNombre()); // Verifica que se actualizó el objeto en memoria
        assertEquals(25, resultado.getCupoMaximo());
        verify(repository).save(actividad);
    }

    @Test
    void modificarActividad_FallaNombreDuplicadoEnOtra() {
        // GIVEN: Intentamos cambiar el nombre a "Danza", pero ya existe otra actividad con ID 2 que se llama "Danza"
        ActividadParaescolar cambios = new ActividadParaescolar();
        cambios.setNombre("Danza");

        when(repository.findById(1L)).thenReturn(Optional.of(actividad));
        when(repository.existsByNombreAndIdNot("Danza", 1L)).thenReturn(true); // ¡Conflicto!

        // WHEN & THEN
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.modificarParaescolar(1L, cambios);
        });

        assertEquals("Ya existe otra actividad con este nombre.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    // --- PRUEBAS DE ELIMINAR (Ref: SD_Eliminar Actividad) ---

    @Test
    void eliminarActividad_Exito_SinAlumnos() {
        // GIVEN: La actividad existe y NO tiene alumnos inscritos
        when(repository.existsById(1L)).thenReturn(true);
        when(grupoParaescolarService.tieneAlumnosInscritosEnParaescolar(1L)).thenReturn(false);

        // WHEN
        service.eliminarParaescolar(1L);

        // THEN
        verify(repository).deleteById(1L); // Se ejecutó el borrado
    }

    @Test
    void eliminarActividad_Falla_ConAlumnos() {
        // GIVEN: La actividad existe PERO tiene alumnos (Simulación del Mock)
        when(repository.existsById(1L)).thenReturn(true);
        when(grupoParaescolarService.tieneAlumnosInscritosEnParaescolar(1L)).thenReturn(true);

        // WHEN & THEN
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.eliminarParaescolar(1L);
        });

        assertEquals("No se puede eliminar una paraescolar con alumnos activos", exception.getMessage());
        verify(repository, never()).deleteById(any()); // Asegura que NO se borró
    }
}