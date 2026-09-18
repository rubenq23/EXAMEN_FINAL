package ipss.VentasFix.controller.web;

import ipss.VentasFix.dto.ClienteRequestDto;
import ipss.VentasFix.dto.ClienteResponseDto;
import ipss.VentasFix.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteWebController {

    private final ClienteService clienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("cliente", new ClienteRequestDto());
        model.addAttribute("modo", "crear");
        return "clientes/form";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        ClienteResponseDto cliente = clienteService.obtenerPorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/detail";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        ClienteResponseDto existente = clienteService.obtenerPorId(id);

        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setRutEmpresa(existente.getRutEmpresa());
        dto.setRubro(existente.getRubro());
        dto.setRazonSocial(existente.getRazonSocial());
        dto.setTelefono(existente.getTelefono());
        dto.setDireccion(existente.getDireccion());
        dto.setNombreContacto(existente.getNombreContacto());
        dto.setEmailContacto(existente.getEmailContacto());

        model.addAttribute("cliente", dto);
        model.addAttribute("modo", "editar");
        model.addAttribute("id", id);
        return "clientes/form";
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("cliente") ClienteRequestDto dto,
                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "crear");
            return "clientes/form";
        }
        try {
            clienteService.crear(dto);
        } catch (ResponseStatusException e) {
            model.addAttribute("errorGeneral", e.getReason());
            model.addAttribute("modo", "crear");
            return "clientes/form";
        }
        return "redirect:/clientes";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("cliente") ClienteRequestDto dto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "editar");
            model.addAttribute("id", id);
            return "clientes/form";
        }
        try {
            clienteService.actualizar(id, dto);
        } catch (ResponseStatusException e) {
            model.addAttribute("errorGeneral", e.getReason());
            model.addAttribute("modo", "editar");
            model.addAttribute("id", id);
            return "clientes/form";
        }
        return "redirect:/clientes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}

