package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "personas",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_persona_rut", columnNames = "rut"),
        @UniqueConstraint(name = "uk_persona_correo", columnNames = "correo")
    }
)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nombre;

    @Column(nullable = false, length = 80)
    private String apellido;

    @Column(nullable = false, length = 20)
    private String rut;

    @Column(nullable = false, length = 120)
    private String correo;

    @Column(length = 30)
    private String telefono;

    // write-only: se recibe en requests (register/login) pero no se serializa en responses
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 200)
    private String password;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Trabajo> trabajos = new ArrayList<>();

    // --- helpers para mantener ambos lados de la relación ---
    public void addTrabajo(Trabajo t) {
        trabajos.add(t);
        t.setPersona(this);
    }

    public void removeTrabajo(Trabajo t) {
        trabajos.remove(t);
        t.setPersona(null);
    }

    // --- getters/setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Trabajo> getTrabajos() { return trabajos; }
    public void setTrabajos(List<Trabajo> trabajos) { this.trabajos = trabajos; }
}
