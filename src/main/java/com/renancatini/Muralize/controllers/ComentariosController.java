package com.renancatini.Muralize.controllers;

import com.renancatini.Muralize.model.Comentario;
import com.renancatini.Muralize.model.Usuario;
import com.renancatini.Muralize.repository.ComentarioRepo;
import com.renancatini.Muralize.repository.UsuarioRepo;
import com.renancatini.Muralize.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComentariosController {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/comentarios")
    public String index(Model model) {
        // Busca todos os comentarios do banco
        var comentarios = comentarioRepo.findAll();

        // Passa a lista para o HTML com o nome "comentarios"
        model.addAttribute("comentarios", comentarios);

        return "comentarios/index";
    }

    @GetMapping("/comentarios/novo") // Define a rota HTTP GET para acessar esta funcionalidade
    public String novo() {
        return "comentarios/novo";
    }

    @PostMapping("/comentarios/criar")
    public String criar(Comentario comentario, HttpServletRequest request) {

        String idLogado = CookieService.getCookie(request, "usuarioId");
        Long id = Long.parseLong(idLogado);

        // Provisório
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        if (usuario != null) {
            comentario.setUsuario(usuario);
            comentarioRepo.save(comentario);
        }

        return "redirect:/comentarios";
    }


    @GetMapping("/comentarios/{id}/excluir")
    public String excluir(@PathVariable int id) {
        comentarioRepo.deleteById((long)id);

        return "redirect:/comentarios";
    }
}
