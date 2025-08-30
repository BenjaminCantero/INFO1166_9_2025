package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "antecedentes_laborales")
@Getter
@Setter
@ToString(exclude = {"datosPersonales", "empresa", "tipoContrato"})
public class AntecedentesLaborales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedentesLaborales;

    private String cargo;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
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
