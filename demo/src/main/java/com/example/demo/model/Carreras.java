package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "carreras")
@Getter
@Setter
@ToString(exclude = {"informacionEstudios"})
public class Carreras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrera;

    private String nombreCarrera;

    // Relacion
    @OneToMany(mappedBy = "carrera")
    private List<InformacionEstudios> informacionEstudios;

}

