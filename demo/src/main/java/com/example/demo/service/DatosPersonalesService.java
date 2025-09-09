package com.example.demo.service;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.dto.DatosPersonalesDTO2;
import com.example.demo.entity.DatosPersonales;
import com.example.demo.repository.DatosPersonalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class DatosPersonalesService {

    @Autowired
    private DatosPersonalesRepository datosPersonalesRepository;

    // Método para el FormController (DTO original)
    public DatosPersonalesDTO createComplete(DatosPersonalesDTO dto) {
        try {
            // Crear entidad desde DTO original
            DatosPersonales datos = new DatosPersonales();
            
            // Mapear campos básicos
            datos.setNombres(dto.getNombres());
            datos.setApellidos(dto.getApellidos());
            datos.setRut(dto.getRut());
            datos.setSexo(dto.getSexo());
            datos.setNacionalidad(dto.getNacionalidad());
            datos.setEstadoCivil(dto.getEstadoCivil());
            datos.setTelefono(dto.getTelefono());
            datos.setCorreo(dto.getCorreo());
            datos.setDireccion(dto.getDireccion());
            datos.setFechaNacimiento(dto.getFechaNacimiento());
            datos.setDiscapacidad(dto.getDiscapacidad());
            
            // Guardar en base de datos
            DatosPersonales savedDatos = datosPersonalesRepository.save(datos);
            
            // Retornar DTO con ID actualizado
            dto.setIdDatosPersonales(savedDatos.getIdDatosPersonales());
            
            System.out.println("✅ Datos guardados en BD con ID: " + savedDatos.getIdDatosPersonales());
            
            return dto;
            
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar datos personales: " + e.getMessage(), e);
        }
    }

    // Método para el REST Controller (DTO2 - desde JavaScript)
    public DatosPersonales guardarDatosPersonales(DatosPersonalesDTO2 dto) {
        try {
            // Verificar si ya existe el RUT
            if (datosPersonalesRepository.findByRut(dto.getRut()).isPresent()) {
                throw new RuntimeException("Ya existe un registro con el RUT: " + dto.getRut());
            }
            
            // Verificar si ya existe el correo
            if (datosPersonalesRepository.findByCorreo(dto.getCorreo()).isPresent()) {
                throw new RuntimeException("Ya existe un registro con el correo: " + dto.getCorreo());
            }
            
            // Crear entidad desde DTO2
            DatosPersonales datos = new DatosPersonales();
            
            // Mapear campos básicos
            datos.setNombres(dto.getNombres());
            datos.setApellidos(dto.getApellidos());
            datos.setRut(dto.getRut());
            datos.setSexo(dto.getSexo());
            datos.setNacionalidad(dto.getNacionalidad());
            datos.setEstadoCivil(dto.getEstadoCivil());
            datos.setTelefono(dto.getTelefono());
            datos.setCorreo(dto.getCorreo());
            datos.setDireccion(dto.getDireccion());
            
            // Convertir fecha de String a Date
            if (dto.getFechaNacimiento() != null && !dto.getFechaNacimiento().isEmpty()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formatter.parse(dto.getFechaNacimiento());
                datos.setFechaNacimiento(fecha);
            }
            
            // Convertir discapacidad de String a Boolean
            datos.setDiscapacidad("Si".equalsIgnoreCase(dto.getDiscapacidad()));
            
            // Guardar en base de datos
            DatosPersonales savedDatos = datosPersonalesRepository.save(datos);
            
            System.out.println("✅ Datos guardados en BD con ID: " + savedDatos.getIdDatosPersonales());
            
            return savedDatos;
            
        } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar datos personales: " + e.getMessage(), e);
        }
    }
}