package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "carreras")
@Getter
@Setter
@ToString(exclude = {"informacionEstudios"})
public class Carreras {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Long idCarrera;
    
    @Column(name = "nombre_carrera")
    private String nombreCarrera;
    
    // Relación
    @OneToMany(mappedBy = "carrera")
    private List<InformacionEstudios> informacionEstudios;
}