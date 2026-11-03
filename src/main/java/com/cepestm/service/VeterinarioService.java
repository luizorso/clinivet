
package com.cepestm.service;

import com.cepestm.dto.VeterinarioDTO;
import com.cepestm.entity.Veterinario;
import com.cepestm.exception.ResourceNotFoundException;
import com.cepestm.repository.VeterinarioRepository;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeterinarioService {
    
    private final VeterinarioRepository repository;
    
    public VeterinarioService(VeterinarioRepository repository){
        this.repository = repository;
    }
    
    @Transactional
    public Veterinario saveByDTO(VeterinarioDTO dto){
        //Converte o veterinarioDTO Para veterinario
        Veterinario veterinario = repository.save(dto.toVeterinario());
        return veterinario;
        
    }
    
    @Transactional(readOnly = true)
    public List<Veterinario> listAll(){
       return repository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Veterinario listById(Long id){
        return repository.findById(id).orElseThrow(()->
            new ResourceNotFoundException(
            "Veterinário não encontrado para o ID: " +id));
    }
    
    @Transactional
    public Veterinario update(Long id, VeterinarioDTO dto){
        Veterinario veterinario = listById(id);
        veterinario.setNome(dto.nome());
        veterinario.setCpf(dto.cpf());
        veterinario.setCrmv(dto.crmv());
        veterinario.setSalario(dto.salario());
        veterinario.setEmail(dto.email());
        veterinario.setDataNascimento(dto.dataNascimento());
        return repository.save(veterinario);
        
    }
    @Transactional
    public void deleteById(Long id){
        Veterinario veterinario = listById(id);
        repository.delete(veterinario);
    }
    
}
