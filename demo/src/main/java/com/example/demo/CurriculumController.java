package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurriculumController {
    @PostMapping("/generar-curriculum")
    public String generarCurriculum(
        @RequestParam String nombres,
        @RequestParam String apellidos,
        @RequestParam String telefono,
        @RequestParam String correo,
        @RequestParam String fecha_nacimiento,
        @RequestParam String direccion,
        @RequestParam java.util.Map<String, String> allParams,
        Model model
    ) {
        model.addAttribute("nombres", nombres);
        model.addAttribute("apellidos", apellidos);
        model.addAttribute("telefono", telefono);
        model.addAttribute("correo", correo);
        model.addAttribute("direccion", direccion);
        // Calcular edad
        int edad = 0;
        try {
            java.time.LocalDate nacimiento = java.time.LocalDate.parse(fecha_nacimiento);
            edad = java.time.Period.between(nacimiento, java.time.LocalDate.now()).getYears();
        } catch (Exception e) {}
        model.addAttribute("edad", edad);
        // Experiencias laborales dinámicas
        java.util.List<java.util.Map<String, String>> experienciasLaborales = new java.util.ArrayList<>();
        int expIndex = 0;
        while (true) {
            String empresa = allParams.get("empresa_" + expIndex);
            String cargo = allParams.get("cargo_" + expIndex);
            if (empresa == null && cargo == null) break;
            java.util.Map<String, String> exp = new java.util.HashMap<>();
            exp.put("empresa", empresa != null ? empresa : "");
            exp.put("cargo", cargo != null ? cargo : "");
            exp.put("rubro", allParams.getOrDefault("rubro_" + expIndex, ""));
            exp.put("fecha_inicio", allParams.getOrDefault("fecha_inicio_" + expIndex, ""));
            exp.put("fecha_fin", allParams.getOrDefault("fecha_fin_" + expIndex, ""));
            exp.put("descripcion", allParams.getOrDefault("descripcion_" + expIndex, ""));
            experienciasLaborales.add(exp);
            expIndex++;
        }
        model.addAttribute("experienciasLaborales", experienciasLaborales);
        // Estudios dinámicos
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
        return "curriculum";
    }

    @GetMapping("/curriculum-demo")
    public String demoCurriculum(Model model) {
        // Puedes poblar datos de ejemplo aquí si lo deseas
        return "curriculum";
    }
}
