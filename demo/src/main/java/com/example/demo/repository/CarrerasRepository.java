package com.example.demo.repository;

import com.example.demo.entity.Carreras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrerasRepository extends JpaRepository<Carreras, Long> {
}