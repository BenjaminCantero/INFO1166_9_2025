package com.example.demo.repository;

import com.example.demo.entity.AntecedentesLaborales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedentesLaboralesRepository extends JpaRepository<AntecedentesLaborales, Long> {
    
    // Buscar por persona
    List<AntecedentesLaborales> findByDatosPersonales_IdDatosPersonales(Long idDatosPersonales);
    
    // Buscar por empresa
    List<AntecedentesLaborales> findByEmpresa_IdEmpresa(Long idEmpresa);
    
    // Buscar por tipo de contrato
    List<AntecedentesLaborales> findByTipoContrato_IdTipoContrato(Long idTipoContrato);
    
    // Buscar con todas las relaciones cargadas
    @Query("SELECT al FROM AntecedentesLaborales al " +
           "LEFT JOIN FETCH al.empresa e " +
           "LEFT JOIN FETCH al.tipoContrato tc " +
           "LEFT JOIN FETCH e.rubro r " +
           "WHERE al.datosPersonales.idDatosPersonales = :idPersona")
    List<AntecedentesLaborales> findByPersonaWithRelations(@Param("idPersona") Long idPersona);
}