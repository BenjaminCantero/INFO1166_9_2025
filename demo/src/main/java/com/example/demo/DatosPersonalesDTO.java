package com.example.demo;

import java.util.List;

public class DatosPersonalesDTO {
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

    // Getters y setters
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
    public List<AntecedentesLaboralesDTO> getAntecedentesLaborales() { return antecedentesLaborales; }
    public void setAntecedentesLaborales(List<AntecedentesLaboralesDTO> antecedentesLaborales) { this.antecedentesLaborales = antecedentesLaborales; }
    public List<InformacionEstudiosDTO> getInformacionEstudios() { return informacionEstudios; }
    public void setInformacionEstudios(List<InformacionEstudiosDTO> informacionEstudios) { this.informacionEstudios = informacionEstudios; }

    // Clases anidadas
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
        // Getters y setters
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
    }

    public static class InformacionEstudiosDTO {
        private String nivelEducativo;
        private String institucion;
        private String carrera;
        private String anioInicio;
        private String anioFin;
        private String estadoEstudio;
        // Getters y setters
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
}