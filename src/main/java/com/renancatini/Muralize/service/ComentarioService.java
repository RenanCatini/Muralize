package com.renancatini.Muralize.service;

import com.renancatini.Muralize.model.Comentario;
import com.renancatini.Muralize.model.Usuario;
import com.renancatini.Muralize.repository.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioService usuarioService;

    // Função para listar todos os comentários
    public List<Comentario> listarTodos() {
        return comentarioRepo.findAll();
    }

    // Salvar um comentario de um usuario
    public void salvar(Long id, Comentario comentario) {
        Usuario usuario = usuarioService.buscarPorId(id);

        if(usuario != null) {
            comentario.setUsuario(usuario);
            comentarioRepo.save(comentario);
        }

    }

    // Deletar um comentario
    public void apagar(Long id) {
        comentarioRepo.deleteById(id);
    }

}
