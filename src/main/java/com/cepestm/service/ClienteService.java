package com.cepestm.service;

import com.cepestm.dto.ClienteDTO;
import com.cepestm.entity.Cliente;
import com.cepestm.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ClienteService {

    private final ClienteRepository rep;

    public ClienteService(ClienteRepository rep) {
        this.rep = rep;
    }

    // Salvar ou Atualizar
    @Transactional
    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = dto.toCliente();
        cliente = rep.save(cliente);
        return new ClienteDTO(cliente);
    }

    // Listar Todos
    public List<ClienteDTO> listarTodos() {
        return rep.findAll()
                .stream()
                .map(ClienteDTO::new) // Utiliza o construtor ClienteDTO(Cliente entity)
                .toList();
    }

    // Buscar por ID
    public Cliente buscarPorId(UUID id) {
        return rep.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
        
    }
    
    @Transactional
    public Cliente update(UUID id, ClienteDTO dto){
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setEmail(dto.email());
        cliente.setObservacoesGerais(dto.observacoesGerais());
        return rep.save(cliente);
    }
    
    // Excluir por ID
    @Transactional
    public void excluir(UUID id) {
        Cliente cliente = buscarPorId(id);
        
        if (!rep.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado para exclusão com o ID: " + id);
        }
        rep.deleteById(id);
    }
}


