package com.example.demo.controller;

import com.example.demo.dto.DatosPersonalesDTO2;
import com.example.demo.entity.DatosPersonales;
import com.example.demo.service.DatosPersonalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/datos-personales")
@CrossOrigin(origins = "*")
public class DatosPersonalesController {

    @Autowired
    private DatosPersonalesService datosPersonalesService;

    @PostMapping("/guardar")
    public ResponseEntity<Map<String, Object>> guardarDatosPersonales(
            @RequestBody DatosPersonalesDTO2 datosPersonales) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validaciones básicas
            if (datosPersonales.getNombres() == null || datosPersonales.getNombres().trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "El nombre es obligatorio");
                return ResponseEntity.badRequest().body(response);
            }
            
            if (datosPersonales.getRut() == null || datosPersonales.getRut().trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "El RUT es obligatorio");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Guardar en la base de datos usando el service
            DatosPersonales savedDatos = datosPersonalesService.guardarDatosPersonales(datosPersonales);
            
            response.put("success", true);
            response.put("message", "Datos guardados correctamente en la base de datos");
            response.put("id", savedDatos.getIdDatosPersonales());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
            
            String mensaje = "Error al guardar en base de datos";
            
            // Detectar errores específicos
            if (e.getMessage().contains("constraint") || e.getMessage().contains("Constraint")) {
                if (e.getMessage().contains("rut") || e.getMessage().contains("RUT")) {
                    mensaje = "El RUT ya está registrado en el sistema";
                } else if (e.getMessage().contains("correo") || e.getMessage().contains("CORREO")) {
                    mensaje = "El correo electrónico ya está registrado en el sistema";
                } else {
                    mensaje = "Ya existe un registro con estos datos. Verifica el RUT y correo electrónico";
                }
            }
            
            response.put("success", false);
            response.put("message", mensaje);
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Backend conectado correctamente");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }
}