package ipss.VentasFix.controller.web;

import ipss.VentasFix.dto.UsuarioRequestDto;
import ipss.VentasFix.dto.UsuarioResponseDto;
import ipss.VentasFix.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioWebController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDto());
        model.addAttribute("modo", "crear");
        return "usuarios/form";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        UsuarioResponseDto existente = usuarioService.obtenerPorId(id);

        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setRut(existente.getRut());
        dto.setNombre(existente.getNombre());
        dto.setApellido(existente.getApellido());
        dto.setEmail(existente.getEmail());
        // password queda vacío: el usuario debe volver a escribirla para guardar

        model.addAttribute("usuario", dto);
        model.addAttribute("modo", "editar");
        model.addAttribute("id", id);
        return "usuarios/form";
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("usuario") UsuarioRequestDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "crear");
            return "usuarios/form";
        }
        try {
            usuarioService.crear(dto);
        } catch (ResponseStatusException e) {
            model.addAttribute("errorGeneral", e.getReason());
            model.addAttribute("modo", "crear");
            return "usuarios/form";
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @Valid @ModelAttribute("usuario") UsuarioRequestDto dto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "editar");
            model.addAttribute("id", id);
            return "usuarios/form";
        }
        try {
            usuarioService.actualizar(id, dto);
        } catch (ResponseStatusException e) {
            model.addAttribute("errorGeneral", e.getReason());
            model.addAttribute("modo", "editar");
            model.addAttribute("id", id);
            return "usuarios/form";
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }
}