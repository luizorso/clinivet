package com.cepestm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="veterinario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Veterinario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ajustado para IDENTITY para o MySQL
    @Column(name = "id_veterinario")
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(name = "crmv", nullable = false, unique = true, length = 20)
    private String crmv;
    
    @Column(name = "salario", nullable = false, precision = 8, scale = 2)
    private BigDecimal salario;
    
    @Column(name="email", nullable = false, unique = true, length= 150)
    private String email;
    
    @Column(name="data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    // ===== ASSOCIAÇÃO COM USUÁRIO =====
    @OneToOne(cascade = CascadeType.ALL) // Cascade faz salvar/deletar o usuário junto com o veterinário se necessário
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false, unique = true)
    private Usuario usuario;
}