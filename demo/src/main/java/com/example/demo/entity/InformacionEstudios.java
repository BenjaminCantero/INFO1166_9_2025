package com.example.demo.entity;

import jakarta.persistence.*;
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
    @Column(name = "id_informacion_estudio")
    private Long idInformacionEstudio;
    
    @Column(name = "anio_inicio")
    private Integer anioInicio;
    
    @Column(name = "anio_fin")
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