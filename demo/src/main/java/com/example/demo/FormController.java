package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/formulario")
    public String mostrarFormulario() {
        return "form";
    }

    @PostMapping("/enviar")
    public String procesarFormulario(@RequestParam String nombre, @RequestParam String email, Model model) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("email", email);
        return "resultado";
    }
}
