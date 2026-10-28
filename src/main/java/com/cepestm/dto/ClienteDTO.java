package com.cepestm.dto;

import com.cepestm.entity.Cliente;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public record ClienteDTO(
    Long id,
    String nome,
    String cpf,
    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataNascimento,
    String email,
    String observacoesGerais
) {
    
    public ClienteDTO(String nome, String cpf, LocalDate dataNascimento, String email, String observacoesGerais){
        this(null, nome, cpf, dataNascimento, email, observacoesGerais);
    }

    // Método para conversão DTO -> Entidade
    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setDataNascimento(this.dataNascimento);
        cliente.setEmail(this.email);
        cliente.setObservacoesGerais(this.observacoesGerais);
        return cliente;
    }
}