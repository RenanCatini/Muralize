package com.renancatini.Muralize.repository;

import com.renancatini.Muralize.dto.ComentarioDTO;
import com.renancatini.Muralize.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepo extends JpaRepository<Comentario, Long> {
    List<Comentario> findAllByOrderByDataCriacaoDesc();
    //
}
