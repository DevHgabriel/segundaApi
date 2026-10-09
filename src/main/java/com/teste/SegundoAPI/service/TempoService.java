package com.teste.SegundoAPI.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teste.SegundoAPI.model.Cliente;
import com.teste.SegundoAPI.model.RegintroTempo;
import com.teste.SegundoAPI.repository.ClienteRepsitory;
import com.teste.SegundoAPI.repository.TempoRepository;

@Service
public class TempoService {

  private final TempoRepository tempoRepository;
  private final ClienteRepsitory clienteRepsitory;

  public TempoService(TempoRepository tempoRepository, ClienteRepsitory clienteRepsitory) {
    this.tempoRepository = tempoRepository;
    this.clienteRepsitory = clienteRepsitory;
  }

  public RegintroTempo salva(RegintroTempo registro) {
    if (registro.getValor() == 0 || registro.getValor() <= 0) {
      throw new IllegalArgumentException("Valor inválido!");
    }
    if (registro.getTempo() == null) {
      throw new IllegalArgumentException("O tempo é obrigatório!");
    }
    if (registro.getTempo().isAfter(OffsetDateTime.now())) {
      throw new IllegalArgumentException("O tempo não pode estar no futuro!");
    }
    return tempoRepository.save(registro);
  }

  public Cliente salvaClie(Cliente cliente) {
    return clienteRepsitory.save(cliente);
  }

  public Optional<RegintroTempo> buscaId(Long id) {
    return tempoRepository.findById(id);
  }

  public List<RegintroTempo> listarTodos() {
    return tempoRepository.findAll();
  }

  public void deletar(Long id) {
    tempoRepository.deleteById(id);
  }

}
