package ipss.VentasFix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDto {

    private Long id;
    private String sku;
    private String nombre;
    private String descripcionCorta;
    private String descripcionLarga;
    private String imagen;
    private BigDecimal precioNeto;
    private BigDecimal precioVenta; // sí se muestra, aunque no se recibe como input
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockBajo;
    private Integer stockAlto;
}
