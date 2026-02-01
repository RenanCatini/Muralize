package com.renancatini.Muralize.controllers;

import com.renancatini.Muralize.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        String nome = CookieService.getCookie(request, "nomeUsuario");
        model.addAttribute("nomeUsuario", nome != null ? nome : "Visitante");

        return "home/index";
    }
}
