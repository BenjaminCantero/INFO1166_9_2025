package com.example.demo.service;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CvService {

    private final DatosPersonalesRepository personaRepo;
    private final AntecedentesLaboralesRepository labRepo;
    private final InformacionEstudiosRepository estRepo;

    public CvService(DatosPersonalesRepository personaRepo,
            AntecedentesLaboralesRepository labRepo,
            InformacionEstudiosRepository estRepo) {
        this.personaRepo = personaRepo;
        this.labRepo = labRepo;
        this.estRepo = estRepo;
    }

    public DatosPersonales createOrUpdate(DatosPersonalesDTO dto) {
        DatosPersonales p = personaRepo.findByRut(dto.getRut())
                .orElseGet(DatosPersonales::new);

        p.setNombres(dto.getNombres());
        p.setApellidos(dto.getApellidos());
        p.setRut(dto.getRut());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setSexo(dto.getSexo());
        p.setNacionalidad(dto.getNacionalidad());
        p.setEstadoCivil(dto.getEstadoCivil());
        p.setTelefono(dto.getTelefono());
        p.setCorreo(dto.getCorreo());
        p.setDireccion(dto.getDireccion());
        p.setDiscapacidad(dto.getDiscapacidad());

        return personaRepo.save(p);
    }

    public Optional<DatosPersonales> findById(Long id) {
        return personaRepo.findById(id);
    }

    public List<DatosPersonales> listAll() {
        return personaRepo.findAll();
    }

    public AntecedentesLaborales addLaboral(Long personaId, AntecedentesLaborales al) {
        DatosPersonales p = personaRepo.findById(personaId)
                .orElseThrow(() -> new IllegalArgumentException("No existe persona " + personaId));
        al.setDatosPersonales(p);
        return labRepo.save(al);
    }

    public InformacionEstudios addEstudio(Long personaId, InformacionEstudios ie) {
        DatosPersonales p = personaRepo.findById(personaId)
                .orElseThrow(() -> new IllegalArgumentException("No existe persona " + personaId));
        ie.setDatosPersonales(p);
        return estRepo.save(ie);
    }
}
