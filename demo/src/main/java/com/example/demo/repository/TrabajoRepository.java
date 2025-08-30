package com.example.demo.repository;

import com.example.demo.entity.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    List<Trabajo> findByPersonaId(Long personaId);
}
