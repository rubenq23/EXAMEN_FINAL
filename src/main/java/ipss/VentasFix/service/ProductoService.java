package ipss.VentasFix.service;

import ipss.VentasFix.dto.ProductoRequestDto;
import ipss.VentasFix.dto.ProductoResponseDto;

import java.util.List;

public interface ProductoService {
    List<ProductoResponseDto> listarTodos();
    ProductoResponseDto obtenerPorId(Long id);
    ProductoResponseDto crear(ProductoRequestDto dto);
    ProductoResponseDto actualizar(Long id, ProductoRequestDto dto);
    void eliminar(Long id);
}
