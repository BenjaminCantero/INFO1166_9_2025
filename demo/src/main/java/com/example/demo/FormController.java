package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class FormController {
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
    public String procesarFormulario(
        @RequestParam String nombres,
        @RequestParam String apellidos,
        @RequestParam String rut,
        @RequestParam String fecha_nacimiento,
        @RequestParam String sexo,
        @RequestParam String nacionalidad,
        @RequestParam String estado_civil,
        @RequestParam String telefono,
        @RequestParam String correo,
        @RequestParam String direccion,
        @RequestParam String discapacidad,
        @RequestParam(required = false) String empresa,
        @RequestParam(required = false) String cargo,
        @RequestParam(required = false) String rubro,
        @RequestParam(required = false) String fecha_inicio,
        @RequestParam(required = false) String fecha_fin,
        @RequestParam(required = false) String tipo_contrato,
        @RequestParam(required = false) String sueldo,
        @RequestParam(required = false) String motivo,
        @RequestParam(required = false) String descripcion,
        @RequestParam(required = false) String nivel_educativo,
        @RequestParam(required = false) String institucion,
        @RequestParam(required = false) String carrera,
        @RequestParam(required = false) String anio_inicio,
        @RequestParam(required = false) String anio_fin,
        @RequestParam(required = false) String estado_estudio,
        @RequestParam java.util.Map<String, String> allParams,
        Model model
    ) {
        model.addAttribute("nombres", nombres);
        model.addAttribute("apellidos", apellidos);
        model.addAttribute("rut", rut);
        model.addAttribute("fecha_nacimiento", fecha_nacimiento);
        model.addAttribute("sexo", sexo);
        model.addAttribute("nacionalidad", nacionalidad);
        model.addAttribute("estado_civil", estado_civil);
        model.addAttribute("telefono", telefono);
        model.addAttribute("correo", correo);
        model.addAttribute("direccion", direccion);
        model.addAttribute("discapacidad", discapacidad);
        // Si existen campos simples, los muestra
        model.addAttribute("empresa", empresa);
        model.addAttribute("cargo", cargo);
        model.addAttribute("rubro", rubro);
        model.addAttribute("fecha_inicio", fecha_inicio);
        model.addAttribute("fecha_fin", fecha_fin);
        model.addAttribute("tipo_contrato", tipo_contrato);
        model.addAttribute("sueldo", sueldo);
        model.addAttribute("motivo", motivo);
        model.addAttribute("descripcion", descripcion);
        model.addAttribute("nivel_educativo", nivel_educativo);
        model.addAttribute("institucion", institucion);
        model.addAttribute("carrera", carrera);
        model.addAttribute("anio_inicio", anio_inicio);
        model.addAttribute("anio_fin", anio_fin);
        model.addAttribute("estado_estudio", estado_estudio);
        // Si existen campos dinámicos, los procesa
        java.util.List<java.util.Map<String, String>> experienciasLaborales = new java.util.ArrayList<>();
        int expIndex = 0;
        while (allParams.containsKey("empresa_" + expIndex)) {
            java.util.Map<String, String> exp = new java.util.HashMap<>();
            exp.put("empresa", allParams.get("empresa_" + expIndex));
            exp.put("cargo", allParams.get("cargo_" + expIndex));
            exp.put("rubro", allParams.get("rubro_" + expIndex));
            exp.put("fecha_inicio", allParams.get("fecha_inicio_" + expIndex));
            exp.put("fecha_fin", allParams.get("fecha_fin_" + expIndex));
            exp.put("tipo_contrato", allParams.get("tipo_contrato_" + expIndex));
            exp.put("sueldo", allParams.get("sueldo_" + expIndex));
            exp.put("motivo", allParams.get("motivo_" + expIndex));
            exp.put("descripcion", allParams.get("descripcion_" + expIndex));
            experienciasLaborales.add(exp);
            expIndex++;
        }
        model.addAttribute("experienciasLaborales", experienciasLaborales);
        java.util.List<java.util.Map<String, String>> estudios = new java.util.ArrayList<>();
        int estIndex = 0;
        while (allParams.containsKey("nivel_educativo_" + estIndex)) {
            String nivel = allParams.get("nivel_educativo_" + estIndex);
            if (nivel != null && !nivel.isEmpty()) {
                java.util.Map<String, String> est = new java.util.HashMap<>();
                est.put("nivel_educativo", nivel);
                est.put("institucion", allParams.get("institucion_" + estIndex));
                est.put("carrera", allParams.get("carrera_" + estIndex));
                est.put("anio_inicio", allParams.get("anio_inicio_" + estIndex));
                est.put("anio_fin", allParams.get("anio_fin_" + estIndex));
                est.put("estado_estudio", allParams.get("estado_estudio_" + estIndex));
                estudios.add(est);
            }
            estIndex++;
        }
        model.addAttribute("estudios", estudios);
        return "resultado";
    }
}