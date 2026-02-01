package com.renancatini.Muralize.service;

import com.renancatini.Muralize.model.Usuario;
import com.renancatini.Muralize.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Aqui ficam as regras lógicas de decisões
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    // Registrar fazer as veriifcações, retorna a mensagem de erro
    public String registrarUsuario(Usuario usuario, String confirmaSenha) {

        // Validação de usuário
        String username = usuario.getUsername();

        if(username.contains(" ")) {
            return "O nome de usuário não pode conter espaços!";
        }
        if(username.length() < 3 || username.length() > 20) {
            return "O nome de usuário tem que ter entre 3 e 20 caracteres!";
        }
        if(!username.matches("^[a-zA-Z0-9._]+$")) {
            return "Use apenas letras, números, '.' ou '_' no usuário.";
        }

        // Validação de senha
        if(!usuario.getSenha().equals(confirmaSenha)){
            return "As senhas não coincidem!";
        }

        try {
            usuarioRepo.save(usuario);
            return null; // Sucesso!
        } catch (Exception e) {
            return "Erro ao cadastrar: Verifique se o usuário já existe.";
        }
    }

    // Fazer a autenticação do user e senha
    public Usuario autenticar(String username, String senha) {
        return usuarioRepo.Login(username, senha);
    }

    // Encontrar usuario por id
    public Usuario buscarPorId(Long usuarioId) {
        return usuarioRepo.findById(usuarioId).orElse(null);
    }

}
