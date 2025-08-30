package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "datos_personales")
public class DatosPersonales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "datosPersonales", cascade = CascadeType.ALL)
    private List<AntecedentesLaborales> antecedentesLaborales;

    @OneToMany(mappedBy = "datosPersonales", cascade = CascadeType.ALL)
    private List<InformacionEstudios> informacionEstudios;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public List<AntecedentesLaborales> getAntecedentesLaborales() {
        return antecedentesLaborales;
    }

    public void setAntecedentesLaborales(List<AntecedentesLaborales> antecedentesLaborales) {
        this.antecedentesLaborales = antecedentesLaborales;
    }

    public List<InformacionEstudios> getInformacionEstudios() {
        return informacionEstudios;
    }

    public void setInformacionEstudios(List<InformacionEstudios> informacionEstudios) {
        this.informacionEstudios = informacionEstudios;
    }
}
