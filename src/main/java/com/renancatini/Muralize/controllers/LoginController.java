package com.renancatini.Muralize.controllers;

import com.renancatini.Muralize.model.Usuario;
import com.renancatini.Muralize.repository.UsuarioRepo;
import com.renancatini.Muralize.service.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/login")
    public String index() {
        return "login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Usuario usuarioParam, String lembrar, HttpServletResponse response){
        Usuario usuario = this.usuarioRepo.Login(usuarioParam.getUsername(), usuarioParam.getSenha());

        if(usuario != null) {
            int tempoLogado = 60 * 10;
            if(lembrar != null) tempoLogado = 60*60*24*365; // 1 Ano de Cookie
            CookieService.setCookie(response, "usuarioId", String.valueOf(usuario.getId()), tempoLogado);
            String primeiroNome = usuario.getNome().split(" ")[0];
            CookieService.setCookie(response, "nomeUsuario", primeiroNome, tempoLogado);
            CookieService.setCookie(response, "usernameUsuario", usuario.getUsername(), tempoLogado);
            return "redirect:/";
        }

        model.addAttribute("erro", "Usuário ou senha inválidos!");
        return "login/index";
    }

    @GetMapping("/sair")
    public String sair(HttpServletResponse response) {
        CookieService.setCookie(response, "usuarioId", "", 0);
        CookieService.setCookie(response, "nomeUsuario", "", 0);
        CookieService.setCookie(response, "usernameUsuario", "", 0);
        return "redirect:/login";
    }


    // Registrar pessoa

    @GetMapping("/registrar")
    public String registrar() {
        return "login/registrar";
    }

    @PostMapping("/registrar/criar")
    public String criar(Usuario usuario, String confirmaSenha, Model model) {

        // Validação de usuário
        String username = usuario.getUsername();

        if(username.contains(" ")) {
            model.addAttribute("erro", "O nome de usuário não pode conter espaços!");
            return "login/registrar";
        }
        if(username.length() < 3 || username.length() > 20) {
            model.addAttribute("erro", "O nome de usuário tem que ter entre 3 e 20 caracteres!");
            return "login/registrar";
        }
        if(!username.matches("^[a-zA-Z0-9._]+$")) {
            model.addAttribute("erro", "Use apenas letras, números, '.' ou '_' no usuário.");
            return "login/registrar";
        }


        // Validação de senha
        if(!usuario.getSenha().equals(confirmaSenha)){
            model.addAttribute("erro", "As senhas não coincidem!");
            return "login/registrar";
        }

        try {
            usuarioRepo.save(usuario);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar: Verifique se o usuário já existe.");
            return "login/registrar";
        }

        return "redirect:/login";
    }
}
