package com.example.demo.repository;

import com.example.demo.entity.DatosPersonales;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DatosPersonalesRepository extends JpaRepository<DatosPersonales, Long> {
    Optional<DatosPersonales> findByRut(String rut);
}
