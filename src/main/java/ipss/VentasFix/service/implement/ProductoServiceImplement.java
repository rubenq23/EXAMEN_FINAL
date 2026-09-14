package ipss.VentasFix.service.implement;

import ipss.VentasFix.dto.ProductoRequestDto;
import ipss.VentasFix.dto.ProductoResponseDto;
import ipss.VentasFix.entity.Producto;
import ipss.VentasFix.mapper.ProductoMapper;
import ipss.VentasFix.repository.ProductoRepository;
import ipss.VentasFix.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImplement implements ProductoService {
    private static final BigDecimal FACTOR_IVA = new BigDecimal("1.19");

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDto> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public ProductoResponseDto obtenerPorId(Long id) {
        return ProductoMapper.toResponseDto(buscarPorIdOLanzarError(id));
    }

    @Override
    public ProductoResponseDto crear(ProductoRequestDto dto) {
        if (productoRepository.existsBySku(dto.getSku())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un producto con ese SKU");
        }

        Producto producto = ProductoMapper.toEntity(dto);
        producto.setPrecioVenta(calcularPrecioVenta(dto.getPrecioNeto()));

        return ProductoMapper.toResponseDto(productoRepository.save(producto));
    }

    @Override
    public ProductoResponseDto actualizar(Long id, ProductoRequestDto dto) {
        Producto producto = buscarPorIdOLanzarError(id);

        if (!producto.getSku().equals(dto.getSku()) && productoRepository.existsBySku(dto.getSku())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un producto con ese SKU");
        }

        producto.setSku(dto.getSku());
        producto.setNombre(dto.getNombre());
        producto.setDescripcionCorta(dto.getDescripcionCorta());
        producto.setDescripcionLarga(dto.getDescripcionLarga());
        producto.setImagen(dto.getImagen());
        producto.setPrecioNeto(dto.getPrecioNeto());
        producto.setPrecioVenta(calcularPrecioVenta(dto.getPrecioNeto()));
        producto.setStockActual(dto.getStockActual());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setStockBajo(dto.getStockBajo());
        producto.setStockAlto(dto.getStockAlto());

        return ProductoMapper.toResponseDto(productoRepository.save(producto));
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.delete(buscarPorIdOLanzarError(id));
    }

    private BigDecimal calcularPrecioVenta(BigDecimal precioNeto) {
        return precioNeto.multiply(FACTOR_IVA).setScale(2, RoundingMode.HALF_UP);
    }

    private Producto buscarPorIdOLanzarError(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con id " + id));
    }
}
