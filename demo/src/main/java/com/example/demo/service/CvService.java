package com.example.demo.service;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.entity.Educacion;
import com.example.demo.entity.Experiencia;
import com.example.demo.entity.Persona;
import com.example.demo.repository.EducacionRepository;
import com.example.demo.repository.ExperienciaRepository;
import com.example.demo.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CvService {

    private final PersonaRepository personaRepo;
    private final ExperienciaRepository expRepo;
    private final EducacionRepository eduRepo;

    public CvService(PersonaRepository personaRepo, ExperienciaRepository expRepo, EducacionRepository eduRepo) {
        this.personaRepo = personaRepo;
        this.expRepo = expRepo;
        this.eduRepo = eduRepo;
    }

    // Crear o actualizar persona (por rut o correo) y agregar (opcional) experiencia/estudio del DTO
    public Persona createOrUpdateFromDto(DatosPersonalesDTO dto) {
        Persona p = findPersonaByRutOrCorreo(dto.getRut(), dto.getCorreo())
                .orElseGet(Persona::new);

        // datos personales
        p.setNombres(dto.getNombres());
        p.setApellidos(dto.getApellidos());
        p.setRut(dto.getRut());
        p.setFechaNacimiento(parseDate(dto.getFechaNacimiento()));
        p.setSexo(dto.getSexo());
        p.setNacionalidad(dto.getNacionalidad());
        p.setEstadoCivil(dto.getEstadoCivil());
        p.setTelefono(dto.getTelefono());
        p.setCorreo(dto.getCorreo());
        p.setDireccion(dto.getDireccion());
        p.setDiscapacidad(dto.getDiscapacidad());

        Persona saved = personaRepo.save(p);

        // experiencia (si viene empresa/cargo)
        if (notBlank(dto.getEmpresa()) && notBlank(dto.getCargo())) {
            Experiencia e = new Experiencia();
            e.setEmpresa(dto.getEmpresa());
            e.setCargo(dto.getCargo());
            e.setRubro(dto.getRubro());
            e.setFechaInicio(parseDate(dto.getFechaInicio()));
            e.setFechaFin(parseDate(dto.getFechaFin()));
            e.setTipoContrato(dto.getTipoContrato());
            e.setSueldo(dto.getSueldo());
            e.setMotivo(dto.getMotivo());
            e.setDescripcion(dto.getDescripcion());
            e.setPersona(saved);
            saved.addExperiencia(e); // mantiene ambos lados
        }

        // educación (si viene institución/carrera o nivel educativo)
        if (notBlank(dto.getInstitucion()) || notBlank(dto.getCarrera()) || notBlank(dto.getNivelEducativo())) {
            Educacion ed = new Educacion();
            ed.setNivelEducativo(dto.getNivelEducativo());
            ed.setInstitucion(dto.getInstitucion());
            ed.setCarrera(dto.getCarrera());
            ed.setAnioInicio(parseInt(dto.getAnioInicio()));
            ed.setAnioFin(parseInt(dto.getAnioFin()));
            ed.setEstadoEstudio(dto.getEstadoEstudio());
            ed.setPersona(saved);
            saved.addEstudio(ed);
        }

        // al salvar persona con cascade=ALL, se guardan hijos nuevos
        return personaRepo.save(saved);
    }

    @Transactional(readOnly = true)
    public Optional<Persona> findPersonaById(Long id) {
        return personaRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Persona> findPersonaByRutOrCorreo(String rut, String correo) {
        if (notBlank(rut)) {
            Optional<Persona> byRut = personaRepo.findByRut(rut);
            if (byRut.isPresent()) return byRut;
        }
        if (notBlank(correo)) {
            return personaRepo.findByCorreo(correo);
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public List<Persona> listAll() {
        return personaRepo.findAll();
    }

    public Experiencia addExperiencia(Long personaId, Experiencia e) {
        Persona p = personaRepo.findById(personaId)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + personaId));
        p.addExperiencia(e);
        personaRepo.save(p);
        return e;
    }

    public Educacion addEducacion(Long personaId, Educacion ed) {
        Persona p = personaRepo.findById(personaId)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + personaId));
        p.addEstudio(ed);
        personaRepo.save(p);
        return ed;
    }

    public void deleteExperiencia(Long expId) {
        expRepo.deleteById(expId);
    }

    public void deleteEducacion(Long eduId) {
        eduRepo.deleteById(eduId);
    }

    // -------- utilitarios --------
    private boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private LocalDate parseDate(String s) {
        if (!notBlank(s)) return null;
        for (String p : Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "yyyy/MM/dd")) {
            try {
                return LocalDate.parse(s.trim(), DateTimeFormatter.ofPattern(p));
            } catch (Exception ignored) {}
        }
        return null;
    }

    private Integer parseInt(String s) {
        try { return notBlank(s) ? Integer.valueOf(s.trim()) : null; }
        catch (NumberFormatException e) { return null; }
    }
}
