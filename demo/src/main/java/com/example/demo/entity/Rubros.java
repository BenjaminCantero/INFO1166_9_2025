package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "rubros")
@Getter
@Setter
@ToString(exclude = {"empresas"})
public class Rubros {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubros")
    private Long idRubros;
    
    @Column(name = "nombre_rubro")
    private String nombreRubro;
    
    // Relación
    @OneToMany(mappedBy = "rubro")
    private List<Empresas> empresas;
}