package com.example.calcTmb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.calcTmb.model.entity.Usuarios;

@Repository
public interface UsuariosRespository extends JpaRepository<Usuarios, Long> {

    List<Usuarios> findByOrderByDataCalculoDesc();
}
