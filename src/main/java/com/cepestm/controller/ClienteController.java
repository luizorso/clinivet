package com.cepestm.controller;

import com.cepestm.dto.ClienteDTO;
import com.cepestm.entity.Cliente;
import com.cepestm.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("clientes", service.listarTodos());
        return "listarClientes";
    }

    @GetMapping("/novo")
    public String exibirFormularioNovo(Model model) {
        model.addAttribute("titulo", "Cadastrar Novo Cliente");
        model.addAttribute("clienteDTO", new ClienteDTO(null, "", "", null, "", ""));
        return "cadastrarCliente";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute("clienteDTO") ClienteDTO dto,
            RedirectAttributes redAttributos) {
        if (dto.id() != null) {
            service.update(dto.id(), dto);
            redAttributos.addFlashAttribute("mensagem",
                    "Cliente Cadastrado com sucesso!");
        } else {
            service.salvar(dto);
            redAttributos.addFlashAttribute("mensagem",
                    "Cliente Cadastrado com sucesso!");
        }
        return "redirect:/clientes";
    }

    @PostMapping("/excluir/{id}")
    public String excluirPerfil(@PathVariable("id") Long id, RedirectAttributes redAttributes) {
        service.excluir(id);
        redAttributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Long id, Model model) {
        Cliente c = service.buscarPorId(id);

        ClienteDTO clienteDTO = new ClienteDTO(
                c.getId(),
                c.getNome(),
                c.getCpf(),
                c.getDataNascimento(),
                c.getEmail(),
                c.getObservacoesGerais()
        );

        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("clienteDTO", clienteDTO);
        return "cadastrarCliente";
    }

}
