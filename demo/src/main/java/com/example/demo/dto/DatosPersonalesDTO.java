package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DatosPersonalesDTO {
    // Datos personales
    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String rut;

    @JsonAlias({"fecha_nacimiento"})
    private String fechaNacimiento;

    private String sexo;
    private String nacionalidad;

    @JsonAlias({"estado_civil"})
    private String estadoCivil;

    private String telefono;

    @Email
    private String correo;

    private String direccion;
    private String discapacidad;

    // Antecedentes laborales (opcionales)
    private String empresa;
    private String cargo;
    private String rubro;

    @JsonAlias({"fecha_inicio"})
    private String fechaInicio;

    @JsonAlias({"fecha_fin"})
    private String fechaFin;

    @JsonAlias({"tipo_contrato"})
    private String tipoContrato;

    private String sueldo;
    private String motivo;
    private String descripcion;

    // Estudios (opcionales)
    @JsonAlias({"nivel_educativo"})
    private String nivelEducativo;

    private String institucion;
    private String carrera;

    @JsonAlias({"anio_inicio"})
    private String anioInicio;

    @JsonAlias({"anio_fin"})
    private String anioFin;

    @JsonAlias({"estado_estudio"})
    private String estadoEstudio;

    // Getters/Setters
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDiscapacidad() { return discapacidad; }
    public void setDiscapacidad(String discapacidad) { this.discapacidad = discapacidad; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getRubro() { return rubro; }
    public void setRubro(String rubro) { this.rubro = rubro; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    public String getSueldo() { return sueldo; }
    public void setSueldo(String sueldo) { this.sueldo = sueldo; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getNivelEducativo() { return nivelEducativo; }
    public void setNivelEducativo(String nivelEducativo) { this.nivelEducativo = nivelEducativo; }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getAnioInicio() { return anioInicio; }
    public void setAnioInicio(String anioInicio) { this.anioInicio = anioInicio; }

    public String getAnioFin() { return anioFin; }
    public void setAnioFin(String anioFin) { this.anioFin = anioFin; }

    public String getEstadoEstudio() { return estadoEstudio; }
    public void setEstadoEstudio(String estadoEstudio) { this.estadoEstudio = estadoEstudio; }
}
