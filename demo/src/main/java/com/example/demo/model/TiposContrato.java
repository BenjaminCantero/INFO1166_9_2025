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
@Table(name = "tipos_contrato")
@Getter
@Setter
@ToString(exclude = {"antecedentesLaborales"})
public class TiposContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoContrato;

    private String nombreTipo;

    @OneToMany(mappedBy = "tipoContrato")
    private List<AntecedentesLaborales> antecedentesLaborales;
}
