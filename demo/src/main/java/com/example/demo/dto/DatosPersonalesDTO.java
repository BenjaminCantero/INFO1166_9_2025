package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;

// Clase principal
@Data
public class DatosPersonalesDTO {
    
    private Long idDatosPersonales;
    private String nombres;
    private String apellidos;
    private String rut;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    
    private String sexo;
    private String nacionalidad;
    private String estadoCivil;
    private String telefono;
    private String correo;
    private String direccion;
    private Boolean discapacidad;
    
    // Relaciones
    private List<AntecedentesLaboralesDTO> antecedentesLaborales;
    private List<InformacionEstudiosDTO> informacionEstudios;
    
    // Clases estáticas internas
    @Data
    public static class AntecedentesLaboralesDTO {
        private Long idAntecedentesLaborales;
        private String cargo;
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date fechaInicio;
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date fechaFin;
        
        private Double sueldo;
        private String motivo;
        private String descripcion;
        
        private EmpresaSimpleDTO empresa;
        private TipoContratoSimpleDTO tipoContrato;
    }
    
    @Data
    public static class InformacionEstudiosDTO {
        private Long idInformacionEstudio;
        private Integer anioInicio;
        private Integer anioFin;
        
        private InstitucionSimpleDTO institucion;
        private CarreraSimpleDTO carrera;
        private EstadoEstudioSimpleDTO estadoEstudio;
    }
    
    @Data
    public static class EmpresaSimpleDTO {
        private Long idEmpresa;
        private String nombreEmpresa;
        private RubroSimpleDTO rubro;
    }
    
    @Data
    public static class RubroSimpleDTO {
        private Long idRubros;
        private String nombreRubro;
    }
    
    @Data
    public static class TipoContratoSimpleDTO {
        private Long idTipoContrato;
        private String nombreTipo;
    }
    
    @Data
    public static class InstitucionSimpleDTO {
        private Long idInstitucion;
        private String nombreInstitucion;
    }
    
    @Data
    public static class CarreraSimpleDTO {
        private Long idCarrera;
        private String nombreCarrera;
    }
    
    @Data
    public static class EstadoEstudioSimpleDTO {
        private Long idEstadoEstudio;
        private String descripcion;
    }
}