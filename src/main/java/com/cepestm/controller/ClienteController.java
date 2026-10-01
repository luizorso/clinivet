package com.cepestm.controller;

import com.cepestm.dto.ClienteDTO;
import com.cepestm.entity.Cliente;
import com.cepestm.service.ClienteService;
import java.util.UUID;
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
        if (dto.idCliente() != null) {
            service.update(dto.idCliente(), dto);
            redAttributos.addFlashAttribute("mensagem",
                    "Cliente Cadastrado com sucesso!");
        } else {
            service.salvar(dto);
            redAttributos.addFlashAttribute("mensagem",
                    "Cliente Cadastrado com sucesso!");
        }
        return "redirect:/clientes";
    }

    @PostMapping("/excluir/{idCliente}")
    public String excluirPerfil(@PathVariable("idCliente") UUID idCliente, RedirectAttributes redAttributes) {
        service.excluir(idCliente);
        redAttributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{idCliente}")
    public String editarCliente(@PathVariable("idCliente") UUID idCliente, Model model) {
        Cliente c = service.buscarPorId(idCliente);

        ClienteDTO clienteDTO = new ClienteDTO(
                c.getIdCliente(),
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
