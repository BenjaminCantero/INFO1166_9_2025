package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatosPersonalesDTO2 {
    // Datos personales
    private String nombres;
    private String apellidos;
    private String rut;
    private String fechaNacimiento;
    private String sexo;
    private String nacionalidad;
    private String estadoCivil;
    private String telefono;
    private String correo;
    private String direccion;
    private String discapacidad;

    // Antecedentes laborales
    private List<AntecedentesLaboralesDTO> antecedentesLaborales;

    // Información de estudios
    private List<InformacionEstudiosDTO> informacionEstudios;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AntecedentesLaboralesDTO {
        private String empresa;
        private String cargo;
        private String rubro;
        private String fechaInicio;
        private String fechaFin;
        private String tipoContrato;
        private String sueldo;
        private String motivo;
        private String descripcion;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InformacionEstudiosDTO {
        private String nivelEducativo;
        private String institucion;
        private String carrera;
        private String anioInicio;
        private String anioFin;
        private String estadoEstudio;
    }
}
