package com.example.demo.service;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.entity.*;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
public class DatosPersonalesMapper {
    
    // Entity to DTO (básico, sin relaciones)
    public DatosPersonalesDTO toDTO(DatosPersonales entity) {
        if (entity == null) return null;
        
        DatosPersonalesDTO dto = new DatosPersonalesDTO();
        dto.setIdDatosPersonales(entity.getIdDatosPersonales());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setRut(entity.getRut());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setSexo(entity.getSexo());
        dto.setNacionalidad(entity.getNacionalidad());
        dto.setEstadoCivil(entity.getEstadoCivil());
        dto.setTelefono(entity.getTelefono());
        dto.setCorreo(entity.getCorreo());
        dto.setDireccion(entity.getDireccion());
        dto.setDiscapacidad(entity.getDiscapacidad());
        
        return dto;
    }
    
    // Entity to DTO (completo, con relaciones)
    public DatosPersonalesDTO toDTOComplete(DatosPersonales entity) {
        if (entity == null) return null;
        
        DatosPersonalesDTO dto = toDTO(entity);
        
        // Mapear antecedentes laborales
        if (entity.getAntecedentesLaborales() != null) {
            dto.setAntecedentesLaborales(
                entity.getAntecedentesLaborales().stream()
                    .map(this::toAntecedentesLaboralesDTO)
                    .collect(Collectors.toList())
            );
        }
        
        // Mapear información de estudios
        if (entity.getInformacionEstudios() != null) {
            dto.setInformacionEstudios(
                entity.getInformacionEstudios().stream()
                    .map(this::toInformacionEstudiosDTO)
                    .collect(Collectors.toList())
            );
        }
        
        return dto;
    }
    
    // DTO to Entity
    public DatosPersonales toEntity(DatosPersonalesDTO dto) {
        if (dto == null) return null;
        
        DatosPersonales entity = new DatosPersonales();
        entity.setIdDatosPersonales(dto.getIdDatosPersonales());
        entity.setNombres(dto.getNombres());
        entity.setApellidos(dto.getApellidos());
        entity.setRut(dto.getRut());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setSexo(dto.getSexo());
        entity.setNacionalidad(dto.getNacionalidad());
        entity.setEstadoCivil(dto.getEstadoCivil());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setDireccion(dto.getDireccion());
        entity.setDiscapacidad(dto.getDiscapacidad());
        
        return entity;
    }
    
    // Actualizar entity desde DTO
    public void updateEntityFromDTO(DatosPersonalesDTO dto, DatosPersonales entity) {
        if (dto == null || entity == null) return;
        
        entity.setNombres(dto.getNombres());
        entity.setApellidos(dto.getApellidos());
        entity.setRut(dto.getRut());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setSexo(dto.getSexo());
        entity.setNacionalidad(dto.getNacionalidad());
        entity.setEstadoCivil(dto.getEstadoCivil());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setDireccion(dto.getDireccion());
        entity.setDiscapacidad(dto.getDiscapacidad());
    }
    
    // Mapper para AntecedentesLaborales
    private DatosPersonalesDTO.AntecedentesLaboralesDTO toAntecedentesLaboralesDTO(AntecedentesLaborales entity) {
        if (entity == null) return null;
        
        DatosPersonalesDTO.AntecedentesLaboralesDTO dto = new DatosPersonalesDTO.AntecedentesLaboralesDTO();
        dto.setIdAntecedentesLaborales(entity.getIdAntecedentesLaborales());
        dto.setCargo(entity.getCargo());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFin(entity.getFechaFin());
        dto.setSueldo(entity.getSueldo());
        dto.setMotivo(entity.getMotivo());
        dto.setDescripcion(entity.getDescripcion());
        
        // Mapear empresa
        if (entity.getEmpresa() != null) {
            DatosPersonalesDTO.EmpresaSimpleDTO empresaDTO = new DatosPersonalesDTO.EmpresaSimpleDTO();
            empresaDTO.setIdEmpresa(entity.getEmpresa().getIdEmpresa());
            empresaDTO.setNombreEmpresa(entity.getEmpresa().getNombreEmpresa());
            
            // Mapear rubro
            if (entity.getEmpresa().getRubro() != null) {
                DatosPersonalesDTO.RubroSimpleDTO rubroDTO = new DatosPersonalesDTO.RubroSimpleDTO();
                rubroDTO.setIdRubros(entity.getEmpresa().getRubro().getIdRubros());
                rubroDTO.setNombreRubro(entity.getEmpresa().getRubro().getNombreRubro());
                empresaDTO.setRubro(rubroDTO);
            }
            dto.setEmpresa(empresaDTO);
        }
        
        // Mapear tipo contrato
        if (entity.getTipoContrato() != null) {
            DatosPersonalesDTO.TipoContratoSimpleDTO tipoDTO = new DatosPersonalesDTO.TipoContratoSimpleDTO();
            tipoDTO.setIdTipoContrato(entity.getTipoContrato().getIdTipoContrato());
            tipoDTO.setNombreTipo(entity.getTipoContrato().getNombreTipo());
            dto.setTipoContrato(tipoDTO);
        }
        
        return dto;
    }
    
    // Mapper para InformacionEstudios
    private DatosPersonalesDTO.InformacionEstudiosDTO toInformacionEstudiosDTO(InformacionEstudios entity) {
        if (entity == null) return null;
        
        DatosPersonalesDTO.InformacionEstudiosDTO dto = new DatosPersonalesDTO.InformacionEstudiosDTO();
        dto.setIdInformacionEstudio(entity.getIdInformacionEstudio());
        dto.setAnioInicio(entity.getAnioInicio());
        dto.setAnioFin(entity.getAnioFin());
        
        // Mapear institución
        if (entity.getInstitucion() != null) {
            DatosPersonalesDTO.InstitucionSimpleDTO institucionDTO = new DatosPersonalesDTO.InstitucionSimpleDTO();
            institucionDTO.setIdInstitucion(entity.getInstitucion().getIdInstitucion());
            institucionDTO.setNombreInstitucion(entity.getInstitucion().getNombreInstitucion());
            dto.setInstitucion(institucionDTO);
        }
        
        // Mapear carrera
        if (entity.getCarrera() != null) {
            DatosPersonalesDTO.CarreraSimpleDTO carreraDTO = new DatosPersonalesDTO.CarreraSimpleDTO();
            carreraDTO.setIdCarrera(entity.getCarrera().getIdCarrera());
            carreraDTO.setNombreCarrera(entity.getCarrera().getNombreCarrera());
            dto.setCarrera(carreraDTO);
        }
        
        // Mapear estado estudio
        if (entity.getEstadoEstudio() != null) {
            DatosPersonalesDTO.EstadoEstudioSimpleDTO estadoDTO = new DatosPersonalesDTO.EstadoEstudioSimpleDTO();
            estadoDTO.setIdEstadoEstudio(entity.getEstadoEstudio().getIdEstadoEstudio());
            estadoDTO.setDescripcion(entity.getEstadoEstudio().getDescripcion());
            dto.setEstadoEstudio(estadoDTO);
        }
        
        return dto;
    }
}