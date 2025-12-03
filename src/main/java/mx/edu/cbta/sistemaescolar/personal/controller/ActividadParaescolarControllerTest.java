package mx.edu.cbta.sistemaescolar.personal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.edu.cbta.sistemaescolar.paraescolar.controller.ActividadParaescolarController;
import mx.edu.cbta.sistemaescolar.paraescolar.dto.ActividadParaescolarDTO;
import mx.edu.cbta.sistemaescolar.paraescolar.mapper.ActividadParaescolarMapper;
import mx.edu.cbta.sistemaescolar.paraescolar.model.ActividadParaescolar;
import mx.edu.cbta.sistemaescolar.paraescolar.service.ActividadParaescolarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ActividadParaescolarController.class)
class ActividadParaescolarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActividadParaescolarService service;

    @MockBean
    private ActividadParaescolarMapper mapper; // Necesitamos mockear el mapper también

    @Autowired
    private ObjectMapper objectMapper;

    private ActividadParaescolarDTO paraescolarDTO;
    private ActividadParaescolar paraescolarEntidad;

    @BeforeEach
    void setUp() {
        // Preparamos datos de prueba
        paraescolarDTO = new ActividadParaescolarDTO();
        paraescolarDTO.setId(1L);
        paraescolarDTO.setNombre("Banda de Guerra");
        paraescolarDTO.setHorario("Viernes 12:00");
        paraescolarDTO.setCupoMaximo(30);

        paraescolarEntidad = new ActividadParaescolar();
        paraescolarEntidad.setId(1L);
        paraescolarEntidad.setNombre("Banda de Guerra");
    }

    @Test
    void listarParaescolares_DebeRetornarLista() throws Exception {
        // GIVEN
        List<ActividadParaescolar> lista = Arrays.asList(paraescolarEntidad);
        when(service.obtenerParaescolares()).thenReturn(lista);
        when(mapper.toDTO(any())).thenReturn(paraescolarDTO);

        // WHEN & THEN
        mockMvc.perform(get("/paraescolares"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Banda de Guerra"));
    }

    @Test
    void crearParaescolar_DebeRetornarCreated() throws Exception {
        // GIVEN
        when(mapper.toEntity(any())).thenReturn(paraescolarEntidad);
        when(service.crearActividadParaescolar(any())).thenReturn(paraescolarEntidad);
        when(mapper.toDTO(any())).thenReturn(paraescolarDTO);

        // WHEN & THEN
        mockMvc.perform(post("/paraescolares")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paraescolarDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Banda de Guerra"));
    }

    @Test
    void modificarParaescolar_DebeRetornarOk() throws Exception {
        // GIVEN
        when(mapper.toEntity(any())).thenReturn(paraescolarEntidad);
        when(service.modificarParaescolar(eq(1L), any())).thenReturn(paraescolarEntidad);
        when(mapper.toDTO(any())).thenReturn(paraescolarDTO);

        // WHEN & THEN
        mockMvc.perform(patch("/paraescolares/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paraescolarDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarParaescolar_DebeRetornarOk() throws Exception {
        // WHEN & THEN (El servicio retorna void si todo sale bien)
        mockMvc.perform(delete("/paraescolares/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("La actividad paraescolar ha sido eliminada con éxito."));
    }
}