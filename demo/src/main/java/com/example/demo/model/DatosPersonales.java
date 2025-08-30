package com.example.demo.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "datos_personales")
@Getter
@Setter
@ToString(exclude = {"antecedentesLaborales", "informacionEstudios"})
public class DatosPersonales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDatosPersonales;

    private String nombres;
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String rut;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String sexo;
    private String nacionalidad;
    private String estadoCivil;
    private String telefono;

    @Column(nullable = false, unique = true)
    private String correo;
    
    private String direccion;
    private Boolean discapacidad;

    // Relaciones
    @OneToMany(mappedBy = "datosPersonales", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AntecedentesLaborales> antecedentesLaborales;

    @OneToMany(mappedBy = "datosPersonales", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformacionEstudios> informacionEstudios;
}
