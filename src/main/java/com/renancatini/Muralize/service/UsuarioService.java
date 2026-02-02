package com.renancatini.Muralize.service;

import com.renancatini.Muralize.dto.LoginDTO;
import com.renancatini.Muralize.dto.UsuarioExibicaoDTO;
import com.renancatini.Muralize.dto.UsuarioRegistroDTO;
import com.renancatini.Muralize.model.Usuario;
import com.renancatini.Muralize.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Aqui ficam as regras lógicas de decisões
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    // Registrar fazer as veriifcações, retorna a mensagem de erro
    public String registrarUsuario(UsuarioRegistroDTO dto) {

        // Validação de usuário
        String username = dto.username();

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
        if(!dto.senha().equals(dto.confirmaSenha())){
            return "As senhas não coincidem!";
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setUsername(dto.username());
        novoUsuario.setSenha(dto.senha());

        try {
            usuarioRepo.save(novoUsuario);
            return null; // Sucesso!
        } catch (Exception e) {
            return "Erro ao cadastrar: Verifique se o usuário já existe.";
        }
    }

    // Fazer a autenticação do user e senha
    public Usuario autenticar(LoginDTO dto) {
        return usuarioRepo.Login(dto.username(), dto.senha());
    }

    // Encontrar usuario por id
    public Usuario buscarPorId(Long usuarioId) {
        return usuarioRepo.findById(usuarioId).orElse(null);
    }

    // Apagar usuario
    public void apagarUsuarioPorId(Long id) {
        usuarioRepo.deleteById(id);
    }

    // Listar todos os usuarios
    public List<UsuarioExibicaoDTO> listarUsuarios(){
        List<Usuario> usuariosBanco = usuarioRepo.findAll();

        List<UsuarioExibicaoDTO> usuarioExibicaoDTOList = new ArrayList<>();

        for(Usuario u : usuariosBanco) {
            UsuarioExibicaoDTO usuarioExibicaoDTO = new UsuarioExibicaoDTO(
                    u.getId(),
                    u.getNome(),
                    u.getUsername()
            );

            usuarioExibicaoDTOList.add(usuarioExibicaoDTO);
        }


        return usuarioExibicaoDTOList;
    }

}
