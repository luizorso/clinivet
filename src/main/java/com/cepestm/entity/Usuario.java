package com.cepestm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ajustado para IDENTITY
    private long id;
    
    @Column(name="nome", nullable= false, length = 100)
    private String nome;
    
    @Column(name="login", nullable = false, unique = true, length = 100)
    private String login;
    
    @Column(name="senha", nullable = false, length= 256)
    private String senha;
    
    @Column(name="email", nullable = false, unique = true, length= 150)
    private String email;
    
    @Column(name="status", nullable = false, columnDefinition="boolean default true")
    private boolean status = true;
    
    // Lado inverso da relação (opcional, permite achar o veterinario a partir do usuario)
    @OneToOne(mappedBy = "usuario")
    private Veterinario veterinario;
}
    
