package com.example.demo.repository;

import com.example.demo.entity.EstadoEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoEstudioRepository extends JpaRepository<EstadoEstudio, Long> {
}