package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import com.example.demo.dto.DatosPersonalesDTO;
import com.example.demo.service.DatosPersonalesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FormController {
    
    private final DatosPersonalesService datosPersonalesService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/")
    public String redirigirPrincipal() {
        return "redirect:/formulario";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(HttpSession session, Model model) {
        model.addAttribute("session", session);
        return "form";
    }

    @PostMapping("/enviar")
    public String procesarFormulario(@RequestParam Map<String, String> allParams, Model model) throws ParseException {

        // Crear DTO para almacenar en base de datos
        DatosPersonalesDTO dto = new DatosPersonalesDTO();

        // Datos personales básicos
        dto.setNombres(allParams.get("nombres"));
        dto.setApellidos(allParams.get("apellidos"));
        dto.setRut(allParams.get("rut"));
        dto.setCorreo(allParams.get("correo"));
        dto.setTelefono(allParams.get("telefono"));
        dto.setSexo(allParams.get("sexo"));
        dto.setDireccion(allParams.get("direccion"));
        dto.setEstadoCivil(allParams.get("estado_civil"));
        dto.setNacionalidad(allParams.get("nacionalidad"));
        dto.setDiscapacidad(Boolean.parseBoolean(allParams.get("discapacidad")));

        // Procesar fecha de nacimiento
        String fechaNacimientoStr = allParams.get("fecha_nacimiento");
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
            dto.setFechaNacimiento(fechaNacimiento);
        }

        // Procesar experiencias laborales dinámicas
        List<DatosPersonalesDTO.AntecedentesLaboralesDTO> experiencias = new ArrayList<>();
        int expIndex = 0;
        while(allParams.containsKey("empresa_" + expIndex)) {
            DatosPersonalesDTO.AntecedentesLaboralesDTO exp = new DatosPersonalesDTO.AntecedentesLaboralesDTO();
            exp.setCargo(allParams.get("cargo_" + expIndex));
            exp.setMotivo(allParams.get("motivo_" + expIndex));
            exp.setDescripcion(allParams.get("descripcion_" + expIndex));
            
            // Procesar fechas
            String fechaInicioStr = allParams.get("fecha_inicio_" + expIndex);
            String fechaFinStr = allParams.get("fecha_fin_" + expIndex);
            if(fechaInicioStr != null && !fechaInicioStr.isEmpty()) {
                exp.setFechaInicio(sdf.parse(fechaInicioStr));
            }
            if(fechaFinStr != null && !fechaFinStr.isEmpty()) {
                exp.setFechaFin(sdf.parse(fechaFinStr));
            }
            
            // Procesar sueldo
            String sueldoStr = allParams.get("sueldo_" + expIndex);
            if(sueldoStr != null && !sueldoStr.isEmpty()) {
                exp.setSueldo(Double.parseDouble(sueldoStr));
            }

            // Configurar empresa y rubro
            DatosPersonalesDTO.EmpresaSimpleDTO empresa = new DatosPersonalesDTO.EmpresaSimpleDTO();
            empresa.setNombreEmpresa(allParams.get("empresa_" + expIndex));
            DatosPersonalesDTO.RubroSimpleDTO rubro = new DatosPersonalesDTO.RubroSimpleDTO();
            rubro.setNombreRubro(allParams.get("rubro_" + expIndex));
            empresa.setRubro(rubro);
            exp.setEmpresa(empresa);

            // Configurar tipo de contrato
            DatosPersonalesDTO.TipoContratoSimpleDTO tipoContrato = new DatosPersonalesDTO.TipoContratoSimpleDTO();
            tipoContrato.setNombreTipo(allParams.get("tipo_contrato_" + expIndex));
            exp.setTipoContrato(tipoContrato);

            experiencias.add(exp);
            expIndex++;
        }
        dto.setAntecedentesLaborales(experiencias);

        // Procesar estudios dinámicos
        List<DatosPersonalesDTO.InformacionEstudiosDTO> estudios = new ArrayList<>();
        int estIndex = 0;
        while(allParams.containsKey("nivel_educativo_" + estIndex)) {
            String nivelEducativo = allParams.get("nivel_educativo_" + estIndex);
            if (nivelEducativo != null && !nivelEducativo.isEmpty()) {
                DatosPersonalesDTO.InformacionEstudiosDTO est = new DatosPersonalesDTO.InformacionEstudiosDTO();
                
                // Procesar años
                String anioInicioStr = allParams.get("anio_inicio_" + estIndex);
                String anioFinStr = allParams.get("anio_fin_" + estIndex);
                if (anioInicioStr != null && !anioInicioStr.isEmpty()) {
                    est.setAnioInicio(Integer.parseInt(anioInicioStr));
                }
                if (anioFinStr != null && !anioFinStr.isEmpty()) {
                    est.setAnioFin(Integer.parseInt(anioFinStr));
                }

                // Configurar institución
                DatosPersonalesDTO.InstitucionSimpleDTO institucion = new DatosPersonalesDTO.InstitucionSimpleDTO();
                institucion.setNombreInstitucion(allParams.get("institucion_" + estIndex));
                est.setInstitucion(institucion);

                // Configurar carrera
                DatosPersonalesDTO.CarreraSimpleDTO carrera = new DatosPersonalesDTO.CarreraSimpleDTO();
                carrera.setNombreCarrera(allParams.get("carrera_" + estIndex));
                est.setCarrera(carrera);

                // Configurar estado de estudio
                DatosPersonalesDTO.EstadoEstudioSimpleDTO estado = new DatosPersonalesDTO.EstadoEstudioSimpleDTO();
                estado.setDescripcion(allParams.get("estado_estudio_" + estIndex));
                est.setEstadoEstudio(estado);

                estudios.add(est);
            }
            estIndex++;
        }
        dto.setInformacionEstudios(estudios);

        // Guardar en base de datos usando el service
        DatosPersonalesDTO guardado = datosPersonalesService.createComplete(dto);

        // Pasar datos al modelo para mostrar en la vista
        model.addAttribute("datosPersonales", guardado);
        
        return "resultado";
    }
}