package com.example.demo.repository;

import com.example.demo.entity.InformacionEstudios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacionEstudiosRepository extends JpaRepository<InformacionEstudios, Long> {
    List<InformacionEstudios> findByDatosPersonales_IdDatosPersonales(Long idDatosPersonales);
}