package com.renancatini.Muralize.service;

import com.renancatini.Muralize.dto.ComentarioDTO;
import com.renancatini.Muralize.dto.ComentarioExibicaoDTO;
import com.renancatini.Muralize.model.Comentario;
import com.renancatini.Muralize.model.Usuario;
import com.renancatini.Muralize.repository.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioService usuarioService;

    // Função para listar todos os comentários
    public List<ComentarioExibicaoDTO> listarTodos() {
        List<Comentario> comentariosBanco = comentarioRepo.findAllByOrderByDataCriacaoDesc();

        List<ComentarioExibicaoDTO> comentarioDTOList = new ArrayList<>();

        for (Comentario c : comentariosBanco) {

            ComentarioExibicaoDTO comentarioExibicaoDTO = new ComentarioExibicaoDTO(
                    c.getId(),
                    c.getUsuario().getNome(),
                    c.getUsuario().getUsername(),
                    c.getTexto(),
                    c.getDataCriacao()
            );

            // Adicionar a lista de DTOs
            comentarioDTOList.add(comentarioExibicaoDTO);
        }

        return comentarioDTOList;
    }

    // Salvar um comentario de um usuario
    public void salvar(Long id, ComentarioDTO comentarioDTO) {
        Usuario usuario = usuarioService.buscarPorId(id);

        if(usuario != null) {
            Comentario comentario = new Comentario();
            comentario.setUsuario(usuario);
            comentario.setTexto(comentarioDTO.texto());
            comentario.setDataCriacao(LocalDateTime.now());

            comentarioRepo.save(comentario);
        }

    }

    // Deletar um comentario
    public void apagar(Long id) {
        comentarioRepo.deleteById(id);
    }

}
