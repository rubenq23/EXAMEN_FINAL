package ipss.VentasFix.mapper;

import ipss.VentasFix.dto.ProductoRequestDto;
import ipss.VentasFix.dto.ProductoResponseDto;
import ipss.VentasFix.entity.Producto;

public class ProductoMapper {

    private ProductoMapper() {}

    public static Producto toEntity(ProductoRequestDto dto) {
        Producto producto = new Producto();
        producto.setSku(dto.getSku());
        producto.setNombre(dto.getNombre());
        producto.setDescripcionCorta(dto.getDescripcionCorta());
        producto.setDescripcionLarga(dto.getDescripcionLarga());
        producto.setImagen(dto.getImagen());
        producto.setPrecioNeto(dto.getPrecioNeto());
        // precioVenta NO se setea aquí, lo calcula ProductoServiceImpl antes de guardar
        producto.setStockActual(dto.getStockActual());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setStockBajo(dto.getStockBajo());
        producto.setStockAlto(dto.getStockAlto());
        return producto;
    }

    public static ProductoResponseDto toResponseDto(Producto producto) {
        ProductoResponseDto dto = new ProductoResponseDto();
        dto.setId(producto.getId());
        dto.setSku(producto.getSku());
        dto.setNombre(producto.getNombre());
        dto.setDescripcionCorta(producto.getDescripcionCorta());
        dto.setDescripcionLarga(producto.getDescripcionLarga());
        dto.setImagen(producto.getImagen());
        dto.setPrecioNeto(producto.getPrecioNeto());
        dto.setPrecioVenta(producto.getPrecioVenta());
        dto.setStockActual(producto.getStockActual());
        dto.setStockMinimo(producto.getStockMinimo());
        dto.setStockBajo(producto.getStockBajo());
        dto.setStockAlto(producto.getStockAlto());
        return dto;
    }
}
