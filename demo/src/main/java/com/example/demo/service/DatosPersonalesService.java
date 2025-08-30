package com.example.demo.service;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.entity.DatosPersonales;
import com.example.demo.repository.DatosPersonalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DatosPersonalesService {
    
    private final DatosPersonalesRepository datosPersonalesRepository;
    private final DatosPersonalesMapper mapper;
    
    // Obtener todos
    @Transactional(readOnly = true)
    public List<DatosPersonalesDTO> findAll() {
        return datosPersonalesRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    // Obtener por ID con todas las relaciones
    @Transactional(readOnly = true)
    public Optional<DatosPersonalesDTO> findById(Long id) {
        Optional<DatosPersonales> personaOpt = datosPersonalesRepository.findById(id);
        if (personaOpt.isEmpty()) {
            return Optional.empty();
        }
        
        DatosPersonales persona = personaOpt.get();
        
        // Cargar antecedentes laborales si existen
        if (persona.getAntecedentesLaborales() != null && !persona.getAntecedentesLaborales().isEmpty()) {
            // Forzar la carga lazy
            persona.getAntecedentesLaborales().size();
            persona.getAntecedentesLaborales().forEach(al -> {
                if (al.getEmpresa() != null) {
                    al.getEmpresa().getNombreEmpresa(); // trigger lazy loading
                    if (al.getEmpresa().getRubro() != null) {
                        al.getEmpresa().getRubro().getNombreRubro();
                    }
                }
                if (al.getTipoContrato() != null) {
                    al.getTipoContrato().getNombreTipo();
                }
            });
        }
        
        // Cargar información de estudios si existen
        if (persona.getInformacionEstudios() != null && !persona.getInformacionEstudios().isEmpty()) {
            persona.getInformacionEstudios().size();
            persona.getInformacionEstudios().forEach(ie -> {
                if (ie.getInstitucion() != null) {
                    ie.getInstitucion().getNombreInstitucion();
                }
                if (ie.getCarrera() != null) {
                    ie.getCarrera().getNombreCarrera();
                }
                if (ie.getEstadoEstudio() != null) {
                    ie.getEstadoEstudio().getDescripcion();
                }
            });
        }
        
        return Optional.of(mapper.toDTOComplete(persona));
    }
    
    // Obtener por RUT
    @Transactional(readOnly = true)
    public Optional<DatosPersonalesDTO> findByRut(String rut) {
        return datosPersonalesRepository.findByRut(rut)
                .map(mapper::toDTOComplete);
    }
    
    // Buscar por nombre
    @Transactional(readOnly = true)
    public List<DatosPersonalesDTO> findByNombre(String nombre) {
        return datosPersonalesRepository.findByNombresOrApellidosContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    
    // Crear nuevo
    public DatosPersonalesDTO create(DatosPersonalesDTO dto) {
        DatosPersonales entity = mapper.toEntity(dto);
        DatosPersonales saved = datosPersonalesRepository.save(entity);
        return mapper.toDTO(saved);
    }
    
    // Actualizar
    public Optional<DatosPersonalesDTO> update(Long id, DatosPersonalesDTO dto) {
        return datosPersonalesRepository.findById(id)
                .map(existing -> {
                    mapper.updateEntityFromDTO(dto, existing);
                    DatosPersonales updated = datosPersonalesRepository.save(existing);
                    return mapper.toDTO(updated);
                });
    }
    
    // Eliminar
    public boolean delete(Long id) {
        if (datosPersonalesRepository.existsById(id)) {
            datosPersonalesRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Verificar si existe por RUT
    @Transactional(readOnly = true)
    public boolean existsByRut(String rut) {
        return datosPersonalesRepository.findByRut(rut).isPresent();
    }
    
    // Verificar si existe por correo
    @Transactional(readOnly = true)
    public boolean existsByCorreo(String correo) {
        return datosPersonalesRepository.findByCorreo(correo).isPresent();
    }
}