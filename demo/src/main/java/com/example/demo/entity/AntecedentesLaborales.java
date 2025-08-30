package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "antecedentes_laborales")
@Getter
@Setter
@ToString(exclude = {"datosPersonales", "empresa", "tipoContrato"})
public class AntecedentesLaborales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_antecedentes_laborales")
    private Long idAntecedentesLaborales;
    
    private String cargo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechaFin;
    
    private Double sueldo;
    private String motivo;
    private String descripcion;
    
    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_datos_personales")
    private DatosPersonales datosPersonales;
    
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresas empresa;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_contrato")
    private TiposContrato tipoContrato;
}