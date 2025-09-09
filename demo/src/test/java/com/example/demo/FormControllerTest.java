package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCrearUsuarioDevuelve201() throws Exception {
        String json = """
                {
                    "nombres": "Juan",
                    "apellidos": "Pérez",
                    "rut": "12345678-5",
                    "correo": "juan2@example.com",
                    "fechaNacimiento": "1990-01-01",
                    "estadoCivil": "Soltero",
                    "telefono": "123456789"
                }
                """;

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                // Muestra la respuesta completa en consola
                .andDo(result -> {
                    System.out.println("Status real: " + result.getResponse().getStatus());
                    System.out.println("Respuesta: " + result.getResponse().getContentAsString());
                })
                // Verifica que sea 201
                .andExpect(status().isCreated());
    }
}
