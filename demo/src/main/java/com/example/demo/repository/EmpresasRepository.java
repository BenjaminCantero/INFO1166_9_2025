package com.example.demo.repository;

import com.example.demo.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresasRepository extends JpaRepository<Empresas, Long> {
    List<Empresas> findByRubro_IdRubros(Long idRubro);
}