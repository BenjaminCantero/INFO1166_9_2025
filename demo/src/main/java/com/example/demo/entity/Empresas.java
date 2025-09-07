package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@ToString(exclude = {"rubro", "antecedentesLaborales"})
public class Empresas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;
    
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    
    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_rubro")
    private Rubros rubro;
    
    @OneToMany(mappedBy = "empresa")
    private List<AntecedentesLaborales> antecedentesLaborales;
}