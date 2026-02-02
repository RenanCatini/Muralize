package com.renancatini.Muralize.controllers;

import com.renancatini.Muralize.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String index(Model model) {

        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);

        return "usuarios/index";
    }

    @GetMapping("/usuarios/{id}/excluir")
    public String excluir(@PathVariable long id) {

        usuarioService.apagarUsuarioPorId(id);
        return "redirect:/usuarios";
    }

}
