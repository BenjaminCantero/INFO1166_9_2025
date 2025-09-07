package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "instituciones")
@Getter
@Setter
@ToString(exclude = {"informacionEstudios"})
public class Instituciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long idInstitucion;
    
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    
    // Relación
    @OneToMany(mappedBy = "institucion")
    private List<InformacionEstudios> informacionEstudios;
}