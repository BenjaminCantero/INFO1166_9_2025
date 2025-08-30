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
        @RequestParam String empresa,
        @RequestParam String cargo,
        @RequestParam String rubro,
        @RequestParam String fecha_inicio,
        @RequestParam(required = false) String fecha_fin,
        @RequestParam String tipo_contrato,
        @RequestParam String sueldo,
        @RequestParam String motivo,
        @RequestParam String descripcion,
        @RequestParam String nivel_educativo,
        @RequestParam String institucion,
        @RequestParam String carrera,
        @RequestParam String anio_inicio,
        @RequestParam(required = false) String anio_fin,
        @RequestParam String estado_estudio,
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
        return "resultado";
    }
}
