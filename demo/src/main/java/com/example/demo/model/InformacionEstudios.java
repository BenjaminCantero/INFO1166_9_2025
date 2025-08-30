package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "informacion_estudios")
@Getter
@Setter
@ToString(exclude = {"datosPersonales", "institucion", "carrera", "estadoEstudio"})
public class InformacionEstudios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInformacionEstudio;

    private Integer anioInicio;
    private Integer anioFin;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_datos_personales")
    private DatosPersonales datosPersonales;

    @ManyToOne
    @JoinColumn(name = "id_institucion")
    private Instituciones institucion;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carreras carrera;

    @ManyToOne
    @JoinColumn(name = "id_estado_estudio")
    private EstadoEstudio estadoEstudio;
}

