package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "estado_estudio")
@Getter
@Setter
@ToString(exclude = {"informacionEstudios"})
public class EstadoEstudio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_estudio")
    private Long idEstadoEstudio;
    
    private String descripcion;
    
    // Relación
    @OneToMany(mappedBy = "estadoEstudio")
    private List<InformacionEstudios> informacionEstudios;
}