package com.example.demo.service;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DatosPersonalesService {
    
    private final DatosPersonalesRepository datosPersonalesRepository;
    private final EmpresasRepository empresasRepository;
    private final RubrosRepository rubrosRepository;
    private final TiposContratoRepository tiposContratoRepository;
    private final InstitucionesRepository institucionesRepository;
    private final CarrerasRepository carrerasRepository;
    private final EstadoEstudioRepository estadoEstudioRepository;
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
    
    // Crear nuevo (solo datos básicos)
    public DatosPersonalesDTO create(DatosPersonalesDTO dto) {
        DatosPersonales entity = mapper.toEntity(dto);
        DatosPersonales saved = datosPersonalesRepository.save(entity);
        return mapper.toDTO(saved);
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
    
    // Crear nuevo con relaciones completas
    public DatosPersonalesDTO createComplete(DatosPersonalesDTO dto) {
        // Crear la persona principal
        DatosPersonales persona = mapper.toEntity(dto);
        
        // Procesar antecedentes laborales
        if (dto.getAntecedentesLaborales() != null && !dto.getAntecedentesLaborales().isEmpty()) {
            List<AntecedentesLaborales> antecedentes = new ArrayList<>();
            
            for (DatosPersonalesDTO.AntecedentesLaboralesDTO antDTO : dto.getAntecedentesLaborales()) {
                AntecedentesLaborales antecedente = new AntecedentesLaborales();
                antecedente.setCargo(antDTO.getCargo());
                antecedente.setFechaInicio(antDTO.getFechaInicio());
                antecedente.setFechaFin(antDTO.getFechaFin());
                antecedente.setSueldo(antDTO.getSueldo());
                antecedente.setMotivo(antDTO.getMotivo());
                antecedente.setDescripcion(antDTO.getDescripcion());
                antecedente.setDatosPersonales(persona);
                
                // Buscar o crear empresa
                if (antDTO.getEmpresa() != null) {
                    Empresas empresa = findOrCreateEmpresa(antDTO.getEmpresa());
                    antecedente.setEmpresa(empresa);
                }
                
                // Buscar o crear tipo contrato
                if (antDTO.getTipoContrato() != null) {
                    TiposContrato tipoContrato = findOrCreateTipoContrato(antDTO.getTipoContrato());
                    antecedente.setTipoContrato(tipoContrato);
                }
                
                antecedentes.add(antecedente);
            }
            
            persona.setAntecedentesLaborales(antecedentes);
        }
        
        // Procesar información de estudios
        if (dto.getInformacionEstudios() != null && !dto.getInformacionEstudios().isEmpty()) {
            List<InformacionEstudios> estudios = new ArrayList<>();
            
            for (DatosPersonalesDTO.InformacionEstudiosDTO estDTO : dto.getInformacionEstudios()) {
                InformacionEstudios estudio = new InformacionEstudios();
                estudio.setAnioInicio(estDTO.getAnioInicio());
                estudio.setAnioFin(estDTO.getAnioFin());
                estudio.setDatosPersonales(persona);
                
                // Buscar o crear institución
                if (estDTO.getInstitucion() != null) {
                    Instituciones institucion = findOrCreateInstitucion(estDTO.getInstitucion());
                    estudio.setInstitucion(institucion);
                }
                
                // Buscar o crear carrera
                if (estDTO.getCarrera() != null) {
                    Carreras carrera = findOrCreateCarrera(estDTO.getCarrera());
                    estudio.setCarrera(carrera);
                }
                
                // Buscar o crear estado estudio
                if (estDTO.getEstadoEstudio() != null) {
                    EstadoEstudio estadoEstudio = findOrCreateEstadoEstudio(estDTO.getEstadoEstudio());
                    estudio.setEstadoEstudio(estadoEstudio);
                }
                
                estudios.add(estudio);
            }
            
            persona.setInformacionEstudios(estudios);
        }
        
        DatosPersonales saved = datosPersonalesRepository.save(persona);
        return mapper.toDTOComplete(saved);
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
    
    // Métodos auxiliares para buscar o crear entidades relacionadas
    private Empresas findOrCreateEmpresa(DatosPersonalesDTO.EmpresaSimpleDTO empresaDTO) {
        if (empresaDTO.getIdEmpresa() != null) {
            // Si tiene ID, buscar la empresa existente
            Optional<Empresas> existing = empresasRepository.findById(empresaDTO.getIdEmpresa());
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        
        // Crear nueva empresa
        Empresas empresa = new Empresas();
        empresa.setNombreEmpresa(empresaDTO.getNombreEmpresa());
        
        // Buscar o crear rubro
        if (empresaDTO.getRubro() != null) {
            Rubros rubro = findOrCreateRubro(empresaDTO.getRubro());
            empresa.setRubro(rubro);
        }
        
        return empresasRepository.save(empresa);
    }
    
    private Rubros findOrCreateRubro(DatosPersonalesDTO.RubroSimpleDTO rubroDTO) {
        if (rubroDTO.getIdRubros() != null) {
            Optional<Rubros> existing = rubrosRepository.findById(rubroDTO.getIdRubros());
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        
        Rubros rubro = new Rubros();
        rubro.setNombreRubro(rubroDTO.getNombreRubro());
        return rubrosRepository.save(rubro);
    }
    
    private TiposContrato findOrCreateTipoContrato(DatosPersonalesDTO.TipoContratoSimpleDTO tipoDTO) {
        if (tipoDTO.getIdTipoContrato() != null) {
            Optional<TiposContrato> existing = tiposContratoRepository.findById(tipoDTO.getIdTipoContrato());
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        
        TiposContrato tipo = new TiposContrato();
        tipo.setNombreTipo(tipoDTO.getNombreTipo());
        return tiposContratoRepository.save(tipo);
    }
    
    private Instituciones findOrCreateInstitucion(DatosPersonalesDTO.InstitucionSimpleDTO institucionDTO) {
        if (institucionDTO.getIdInstitucion() != null) {
            Optional<Instituciones> existing = institucionesRepository.findById(institucionDTO.getIdInstitucion());
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        
        Instituciones institucion = new Instituciones();
        institucion.setNombreInstitucion(institucionDTO.getNombreInstitucion());
        return institucionesRepository.save(institucion);
    }
    
    private Carreras findOrCreateCarrera(DatosPersonalesDTO.CarreraSimpleDTO carreraDTO) {
        if (carreraDTO.getIdCarrera() != null) {
            Optional<Carreras> existing = carrerasRepository.findById(carreraDTO.getIdCarrera());
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        
        Carreras carrera = new Carreras();
        carrera.setNombreCarrera(carreraDTO.getNombreCarrera());
        return carrerasRepository.save(carrera);
    }
    
    private EstadoEstudio findOrCreateEstadoEstudio(DatosPersonalesDTO.EstadoEstudioSimpleDTO estadoDTO) {
        if (estadoDTO.getIdEstadoEstudio() != null) {
            Optional<EstadoEstudio> existing = estadoEstudioRepository.findById(estadoDTO.getIdEstadoEstudio());
            if (existing.isPresent()) {
                return existing.get();
            }
        }
        
        EstadoEstudio estado = new EstadoEstudio();
        estado.setDescripcion(estadoDTO.getDescripcion());
        return estadoEstudioRepository.save(estado);
    }
}