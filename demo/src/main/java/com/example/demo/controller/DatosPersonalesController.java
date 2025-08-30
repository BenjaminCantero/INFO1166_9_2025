package com.example.demo.controller;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.service.DatosPersonalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/datos-personales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DatosPersonalesController {
    
    private final DatosPersonalesService datosPersonalesService;
    
    // GET /api/datos-personales - Obtener todos
    @GetMapping
    public ResponseEntity<List<DatosPersonalesDTO>> getAllDatosPersonales() {
        List<DatosPersonalesDTO> personas = datosPersonalesService.findAll();
        return ResponseEntity.ok(personas);
    }
    
    // GET /api/datos-personales/{id} - Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<DatosPersonalesDTO> getDatosPersonalesById(@PathVariable Long id) {
        return datosPersonalesService.findById(id)
                .map(persona -> ResponseEntity.ok(persona))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /api/datos-personales/rut/{rut} - Obtener por RUT
    @GetMapping("/rut/{rut}")
    public ResponseEntity<DatosPersonalesDTO> getDatosPersonalesByRut(@PathVariable String rut) {
        return datosPersonalesService.findByRut(rut)
                .map(persona -> ResponseEntity.ok(persona))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /api/datos-personales/buscar?nombre={nombre} - Buscar por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<DatosPersonalesDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<DatosPersonalesDTO> personas = datosPersonalesService.findByNombre(nombre);
        return ResponseEntity.ok(personas);
    }
    
    // POST /api/datos-personales - Crear nuevo
    @PostMapping
    public ResponseEntity<?> createDatosPersonales(@Valid @RequestBody DatosPersonalesDTO datosPersonalesDTO) {
        try {
            // Validar RUT único
            if (datosPersonalesService.existsByRut(datosPersonalesDTO.getRut())) {
                return ResponseEntity.badRequest()
                        .body("Error: Ya existe una persona con el RUT " + datosPersonalesDTO.getRut());
            }
            
            // Validar correo único
            if (datosPersonalesService.existsByCorreo(datosPersonalesDTO.getCorreo())) {
                return ResponseEntity.badRequest()
                        .body("Error: Ya existe una persona con el correo " + datosPersonalesDTO.getCorreo());
            }
            
            DatosPersonalesDTO nuevaPersona = datosPersonalesService.create(datosPersonalesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear los datos personales: " + e.getMessage());
        }
    }
    
    // PUT /api/datos-personales/{id} - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDatosPersonales(
            @PathVariable Long id, 
            @Valid @RequestBody DatosPersonalesDTO datosPersonalesDTO) {
        try {
            return datosPersonalesService.update(id, datosPersonalesDTO)
                    .map(persona -> ResponseEntity.ok(persona))
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar los datos personales: " + e.getMessage());
        }
    }
    
    // DELETE /api/datos-personales/{id} - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDatosPersonales(@PathVariable Long id) {
        try {
            boolean deleted = datosPersonalesService.delete(id);
            if (deleted) {
                return ResponseEntity.ok().body("Datos personales eliminados correctamente");
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar los datos personales: " + e.getMessage());
        }
    }
    
    // GET /api/datos-personales/{id}/exists - Verificar existencia
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        boolean exists = datosPersonalesService.findById(id).isPresent();
        return ResponseEntity.ok(exists);
    }
}