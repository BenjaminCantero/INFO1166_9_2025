package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "educaciones")
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=80)
    private String nivelEducativo;

    @Column(length=120)
    private String institucion;

    @Column(length=120)
    private String carrera;

    private Integer anioInicio;
    private Integer anioFin;

    @Column(length=60)
    private String estadoEstudio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", foreignKey = @ForeignKey(name = "fk_educacion_persona"))
    @JsonBackReference
    private Persona persona;

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNivelEducativo() { return nivelEducativo; }
    public void setNivelEducativo(String nivelEducativo) { this.nivelEducativo = nivelEducativo; }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public Integer getAnioInicio() { return anioInicio; }
    public void setAnioInicio(Integer anioInicio) { this.anioInicio = anioInicio; }

    public Integer getAnioFin() { return anioFin; }
    public void setAnioFin(Integer anioFin) { this.anioFin = anioFin; }

    public String getEstadoEstudio() { return estadoEstudio; }
    public void setEstadoEstudio(String estadoEstudio) { this.estadoEstudio = estadoEstudio; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
}
