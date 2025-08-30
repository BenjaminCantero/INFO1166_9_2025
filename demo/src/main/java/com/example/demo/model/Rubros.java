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
@Table(name = "rubros")
@Getter
@Setter
@ToString(exclude = {"empresas"})
public class Rubros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRubros;

    private String nombreRubro;

    // Relacion
    @OneToMany(mappedBy = "rubro")
    private List<Empresas> empresas;
}
