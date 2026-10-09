package com.teste.SegundoAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.SegundoAPI.model.Cliente;

public interface ClienteRepsitory extends JpaRepository<Cliente,Long> {
    List<Cliente> findByNome(String nome);

    List<Cliente> findByEmail(String email);
    Optional<Cliente> findById(Long id);
}
