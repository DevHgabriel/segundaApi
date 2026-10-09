package com.teste.SegundoAPI.service;

import com.teste.SegundoAPI.model.Cliente;
import com.teste.SegundoAPI.repository.ClienteRepsitory;

public class ClienteService {
    private final ClienteRepsitory clienteRepsitory;

    public ClienteService(ClienteRepsitory clienteRepsitory) {
        this.clienteRepsitory = clienteRepsitory;
    }

    public Cliente atualizar(Long id, Cliente dadosNovos){
        Cliente clienteExistente =  clienteRepsitory.findById(id).orElse(null);

        if(clienteExistente != null){
            clienteExistente.setNome(dadosNovos.getNome());
            clienteExistente.setEmail(dadosNovos.getEmail());
            clienteRepsitory.save(clienteExistente);
        }
        return clienteExistente;
    }
}
