package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tipos_contrato")
@Getter
@Setter
@ToString(exclude = {"antecedentesLaborales"})
public class TiposContrato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contrato")
    private Long idTipoContrato;
    
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    
    // Relación
    @OneToMany(mappedBy = "tipoContrato")
    private List<AntecedentesLaborales> antecedentesLaborales;
}