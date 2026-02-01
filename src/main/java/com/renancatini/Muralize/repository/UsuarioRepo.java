package com.renancatini.Muralize.repository;

import com.renancatini.Muralize.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.senha = :senha")
    Usuario Login(String username, String senha);
}
