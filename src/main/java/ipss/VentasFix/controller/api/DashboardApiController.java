package ipss.VentasFix.controller.api;

import ipss.VentasFix.repository.ClienteRepository;
import ipss.VentasFix.repository.ProductoRepository;
import ipss.VentasFix.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardApiController {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<DashboardResponse> obtenerResumen() {
        return ResponseEntity.ok(new DashboardResponse(
                usuarioRepository.count(),
                productoRepository.count(),
                clienteRepository.count()
        ));
    }

    @Data
    @AllArgsConstructor
    public static class DashboardResponse {
        private long totalUsuarios;
        private long totalProductos;
        private long totalClientes;
    }
}
