package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class FormController {

    @GetMapping("/formulario")
    public String mostrarFormulario(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("session", session);
        return "form";
    }

    @PostMapping("/enviar")
    public String procesarFormulario(@RequestParam String nombre, @RequestParam String email, Model model) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("email", email);
        return "resultado";
    }
}
