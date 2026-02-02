package com.renancatini.Muralize.dto;

import java.time.LocalDateTime;

public record ComentarioExibicaoDTO(
        Long id,
        String nome,
        String username,
        String texto,
        LocalDateTime dataCriacao
) {}
