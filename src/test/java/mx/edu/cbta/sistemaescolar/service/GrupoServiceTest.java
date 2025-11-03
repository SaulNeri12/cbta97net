package mx.edu.cbta.sistemaescolar.service;


import mx.edu.cbta.sistemaescolar.academica.dto.GrupoDTO;
import mx.edu.cbta.sistemaescolar.academica.model.Grupo;
import mx.edu.cbta.sistemaescolar.academica.repository.GrupoRepository;
import mx.edu.cbta.sistemaescolar.academica.service.GrupoService;
import mx.edu.cbta.sistemaescolar.academica.service.exceptions.GrupoException;
import mx.edu.cbta.sistemaescolar.academica.service.impl.GrupoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceTest {
    @Mock
    GrupoRepository grupoRepository;

    @InjectMocks
    GrupoServiceImpl grupoService;


    /**
     * Seccion para hacer pruebas al registrar grupos
     *
     * Esta prueba es una prueba para registrar un grupo con constructor vacio.
     *
     * @throws GrupoException
     */
    @Test
    void registrarGrupoTest() throws GrupoException {
        Grupo grupo = new Grupo();
        grupoService.registrarGrupo(grupo);
//        Mockito.when(grupoRepository.save(grupo)).thenReturn(grupo);
//        Grupo grupoAgregado = grupoService.obtenerGrupoPorId(grupo.getId());
//        Assertions.assertEquals(grupo.getId(),grupoAgregado.getId() );
    }


    @Test
    void crearGrupoConClasesTest() throws GrupoException {

    }




}
