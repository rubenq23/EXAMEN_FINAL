package ipss.VentasFix.controller.web;

import ipss.VentasFix.dto.ProductoRequestDto;
import ipss.VentasFix.dto.ProductoResponseDto;
import ipss.VentasFix.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoWebController {

    private final ProductoService productoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("producto", new ProductoRequestDto());
        model.addAttribute("modo", "crear");
        return "productos/form";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        ProductoResponseDto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        return "productos/detail";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        ProductoResponseDto existente = productoService.obtenerPorId(id);

        ProductoRequestDto dto = new ProductoRequestDto();
        dto.setSku(existente.getSku());
        dto.setNombre(existente.getNombre());
        dto.setDescripcionCorta(existente.getDescripcionCorta());
        dto.setDescripcionLarga(existente.getDescripcionLarga());
        dto.setImagen(existente.getImagen());
        dto.setPrecioNeto(existente.getPrecioNeto());
        dto.setStockActual(existente.getStockActual());
        dto.setStockMinimo(existente.getStockMinimo());
        dto.setStockBajo(existente.getStockBajo());
        dto.setStockAlto(existente.getStockAlto());

        model.addAttribute("producto", dto);
        model.addAttribute("modo", "editar");
        model.addAttribute("id", id);
        return "productos/form";
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("producto") ProductoRequestDto dto,
                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "crear");
            return "productos/form";
        }
        try {
            productoService.crear(dto);
        } catch (ResponseStatusException e) {
            model.addAttribute("errorGeneral", e.getReason());
            model.addAttribute("modo", "crear");
            return "productos/form";
        }
        return "redirect:/productos";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute("producto") ProductoRequestDto dto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modo", "editar");
            model.addAttribute("id", id);
            return "productos/form";
        }
        try {
            productoService.actualizar(id, dto);
        } catch (ResponseStatusException e) {
            model.addAttribute("errorGeneral", e.getReason());
            model.addAttribute("modo", "editar");
            model.addAttribute("id", id);
            return "productos/form";
        }
        return "redirect:/productos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}


