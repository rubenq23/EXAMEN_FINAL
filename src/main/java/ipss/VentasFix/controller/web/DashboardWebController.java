package ipss.VentasFix.controller.web;

import ipss.VentasFix.repository.ClienteRepository;
import ipss.VentasFix.repository.ProductoRepository;
import ipss.VentasFix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardWebController {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUsuarios", usuarioRepository.count());
        model.addAttribute("totalProductos", productoRepository.count());
        model.addAttribute("totalClientes", clienteRepository.count());
        return "dashboard";
    }
}