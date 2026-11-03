
package com.cepestm.controller;

import com.cepestm.dto.VeterinarioDTO;
import com.cepestm.entity.Veterinario;
import com.cepestm.service.VeterinarioService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {
    private final VeterinarioService service;
    
    //Injeção de dependência recomendada via construtor
    public VeterinarioController(VeterinarioService service){
        this.service = service;
    }
    
    @GetMapping("/novo")
    public String cadastrarVeterinario(Model model){
        model.addAttribute("titulo", "Cadastro de Usuário");
        model.addAttribute("veterinarioDTO", 
                    new VeterinarioDTO(null, null, null, null, null, null, null));
        return "cadastrarVeterinario";
    }
    
    @PostMapping("/salvar")
    public String salvarVeterinario(@ModelAttribute("veterinarioDTO")
        VeterinarioDTO dto,
        RedirectAttributes redAttributes){
        if(dto.id() != null){
            service.update(dto.id(), dto);
            redAttributes.addFlashAttribute("mensagem", 
                    "Dados do Veterinario atualizados com sucesso!");
        }else{
            service.saveByDTO(dto);
            redAttributes.addFlashAttribute("mensagem", "Dados do veterinário salvos com sucesso!");
            
        }
        return "redirect:veterinarios";
    }
    
    public String listarVeterinarios(Model model){
        List<Veterinario> veterinarios = service.listAll();
        model.addAttribute("veterinarios", veterinarios);
        return "listVeterinarios";
    }
    
    @PostMapping("/excluir/{id}")
    public String excluirVeterinario(@PathVariable("id")long Id,
        RedirectAttributes redAtributos){
        service.deleteById(Id);
        redAtributos.addFlashAttribute("mensagem", 
                "Dados do veterinário excluidos com sucesso!");
        return "redirect:/veterinarios";
    }
    
    @GetMapping("/editar/{id}")
    public String editarVeterinario(@PathVariable("id") long id,
        Model model){
        Veterinario veterinario = service.listById(id);
        VeterinarioDTO dto = new VeterinarioDTO(veterinario);
        model.addAttribute("titulo", "Editar Veterinario");
        model.addAttribute("veterinarioDTO", dto);
        return "cadastrarVeterinario";
    }
    
}
