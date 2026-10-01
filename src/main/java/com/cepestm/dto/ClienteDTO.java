package com.cepestm.dto;

import com.cepestm.entity.Cliente;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.UUID;

public record ClienteDTO(
    UUID idCliente,
    String nome,
    String cpf,
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataNascimento,
    String email,
    String observacoesGerais
) {

    // 1. Construtor utilitário para conversão Entidade -> DTO
    public ClienteDTO(Cliente cliente) {
        this(
            cliente != null ? cliente.getIdCliente() : null,
            cliente != null ? cliente.getNome() : null,
            cliente != null ? cliente.getCpf() : null,
            cliente != null ? cliente.getDataNascimento() : null,
            cliente != null ? cliente.getEmail() : null,
            cliente != null ? cliente.getObservacoesGerais() : null
        );
    }

    // 2. Método para conversão DTO -> Entidade
    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(this.idCliente);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setEmail(this.email);
        cliente.setObservacoesGerais(this.observacoesGerais);
        return cliente;
    }
}