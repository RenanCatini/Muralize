package com.renancatini.Muralize.controllers;

import com.renancatini.Muralize.repository.ComentarioRepo;
import com.renancatini.Muralize.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/usuarios")
    public String index(Model model) {

        var usuarios = usuarioRepo.findAll();
        model.addAttribute("usuarios", usuarios);

        return "usuarios/index";
    }

    @GetMapping("/usuarios/{id}/excluir")
    public String excluir(@PathVariable long id) {
        usuarioRepo.deleteById(id);

        return "redirect:/usuarios";
    }

}
