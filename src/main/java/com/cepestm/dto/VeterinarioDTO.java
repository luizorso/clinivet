
package com.cepestm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.cepestm.entity.Veterinario;

public record VeterinarioDTO(

Long id, // <--- Alterado de 'long' para 'Long' (agora aceita null),
String nome,
String cpf,
String crmv,
BigDecimal salario,
String email,
LocalDate dataNascimento
)

{
    /*Construtor utilitário para cadastros novos (sem ID) */

    public VeterinarioDTO(String nome, String cpf, String crmv, BigDecimal salario,
        String email, LocalDate dataNascimento){
            this(null, nome, cpf, crmv, salario, email, dataNascimento);

        }


    public Veterinario toVeterinario(){
        Veterinario veterinario = new Veterinario();
        veterinario.setId(this.id);
        veterinario.setNome(this.nome);
        veterinario.setCrmv(this.crmv);
        veterinario.setSalario(this.salario);
        veterinario.setEmail(this.email);
        veterinario.setDataNascimento(this.dataNascimento);
        return veterinario;


    }
    

}