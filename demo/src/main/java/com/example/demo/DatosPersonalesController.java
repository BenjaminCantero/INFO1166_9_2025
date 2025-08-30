package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/api")
public class DatosPersonalesController {

    @PostMapping("/datos")
    public String recibirDatos(@ModelAttribute DatosPersonalesDTO datos, Model model) {
        model.addAttribute("mensaje", "Datos enviados correctamente");
        model.addAttribute("datos", datos);
        return "confirmacion";
    }
}
