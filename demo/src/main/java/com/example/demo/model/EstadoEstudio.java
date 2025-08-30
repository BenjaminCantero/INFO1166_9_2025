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
@Table(name = "estado_estudio")
@Getter
@Setter
@ToString(exclude = {"informacionEstudios"})
public class EstadoEstudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoEstudio;

    private String descripcion;

    // Relacion
    @OneToMany(mappedBy = "estadoEstudio")
    private List<InformacionEstudios> informacionEstudios;
}
