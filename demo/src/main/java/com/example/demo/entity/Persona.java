package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personas", uniqueConstraints = {
        @UniqueConstraint(name = "uk_persona_rut", columnNames = "rut"),
        @UniqueConstraint(name = "uk_persona_correo", columnNames = "correo")
})
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos personales
    @Column(nullable = false, length = 80)
    private String nombres;

    @Column(nullable = false, length = 80)
    private String apellidos;

    @Column(nullable = false, length = 20)
    private String rut;

    private LocalDate fechaNacimiento;

    @Column(length = 30)
    private String sexo;

    @Column(length = 60)
    private String nacionalidad;

    @Column(length = 30)
    private String estadoCivil;

    @Column(length = 30)
    private String telefono;

    @Column(nullable = false, length = 120)
    private String correo;

    @Column(length = 200)
    private String direccion;

    @Column(length = 120)
    private String discapacidad;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Experiencia> experiencias = new ArrayList<>();

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Educacion> estudios = new ArrayList<>();

    // Helpers
    public void addExperiencia(Experiencia e) {
        experiencias.add(e);
        e.setPersona(this);
    }
    public void removeExperiencia(Experiencia e) {
        experiencias.remove(e);
        e.setPersona(null);
    }
    public void addEstudio(Educacion ed) {
        estudios.add(ed);
        ed.setPersona(this);
    }
    public void removeEstudio(Educacion ed) {
        estudios.remove(ed);
        ed.setPersona(null);
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

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

    public List<Experiencia> getExperiencias() { return experiencias; }
    public void setExperiencias(List<Experiencia> experiencias) { this.experiencias = experiencias; }

    public List<Educacion> getEstudios() { return estudios; }
    public void setEstudios(List<Educacion> estudios) { this.estudios = estudios; }
}
