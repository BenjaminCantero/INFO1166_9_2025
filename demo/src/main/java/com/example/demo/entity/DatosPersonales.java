package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "datos_personales")
@Getter
@Setter
@ToString(exclude = {"antecedentesLaborales", "informacionEstudios"})
public class DatosPersonales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_personales")
    private Long idDatosPersonales;
    
    private String nombres;
    private String apellidos;
    
    @Column(nullable = false, unique = true)
    private String rut;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    
    private String sexo;
    private String nacionalidad;
    
    @Column(name = "estado_civil")
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