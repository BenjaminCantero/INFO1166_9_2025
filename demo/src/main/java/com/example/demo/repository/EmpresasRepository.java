package com.example.demo.repository;

import com.example.demo.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresasRepository extends JpaRepository<Empresas, Long> {
}
