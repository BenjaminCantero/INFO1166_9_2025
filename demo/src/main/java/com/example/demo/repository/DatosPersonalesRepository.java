package com.example.demo.repository;

import com.example.demo.entity.DatosPersonales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatosPersonalesRepository extends JpaRepository<DatosPersonales, Long> {
    
    // Buscar por RUT
    Optional<DatosPersonales> findByRut(String rut);
    
    // Buscar por correo
    Optional<DatosPersonales> findByCorreo(String correo);
    
    // Buscar por nombres y apellidos (busqueda parcial)
    @Query("SELECT dp FROM DatosPersonales dp WHERE " +
           "LOWER(dp.nombres) LIKE LOWER(CONCAT('%', :nombre, '%')) OR " +
           "LOWER(dp.apellidos) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<DatosPersonales> findByNombresOrApellidosContainingIgnoreCase(@Param("nombre") String nombre);
    
    // Buscar con experiencia laboral incluida
    @Query("SELECT DISTINCT dp FROM DatosPersonales dp " +
           "LEFT JOIN FETCH dp.antecedentesLaborales al " +
           "LEFT JOIN FETCH al.empresa e " +
           "LEFT JOIN FETCH al.tipoContrato tc " +
           "LEFT JOIN FETCH e.rubro r")
    List<DatosPersonales> findAllWithAntecedentesLaborales();
    
    // Buscar con estudios incluidos
    @Query("SELECT DISTINCT dp FROM DatosPersonales dp " +
           "LEFT JOIN FETCH dp.informacionEstudios ie " +
           "LEFT JOIN FETCH ie.institucion i " +
           "LEFT JOIN FETCH ie.carrera c " +
           "LEFT JOIN FETCH ie.estadoEstudio ee")
    List<DatosPersonales> findAllWithInformacionEstudios();
    
    // Buscar completo con todas las relaciones - usando múltiples queries para evitar MultipleBagFetchException
    @Query("SELECT dp FROM DatosPersonales dp WHERE dp.idDatosPersonales = :id")
    Optional<DatosPersonales> findByIdWithAllRelations(@Param("id") Long id);
    
    // Query separada para antecedentes laborales
    @Query("SELECT DISTINCT dp FROM DatosPersonales dp " +
           "LEFT JOIN FETCH dp.antecedentesLaborales al " +
           "LEFT JOIN FETCH al.empresa e " +
           "LEFT JOIN FETCH al.tipoContrato tc " +
           "LEFT JOIN FETCH e.rubro r " +
           "WHERE dp.idDatosPersonales = :id")
    Optional<DatosPersonales> findByIdWithAntecedentes(@Param("id") Long id);
    
    // Query separada para información de estudios  
    @Query("SELECT DISTINCT dp FROM DatosPersonales dp " +
           "LEFT JOIN FETCH dp.informacionEstudios ie " +
           "LEFT JOIN FETCH ie.institucion i " +
           "LEFT JOIN FETCH ie.carrera c " +
           "LEFT JOIN FETCH ie.estadoEstudio ee " +
           "WHERE dp.idDatosPersonales = :id")
    Optional<DatosPersonales> findByIdWithEstudios(@Param("id") Long id);
}