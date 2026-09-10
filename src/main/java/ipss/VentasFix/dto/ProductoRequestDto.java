package ipss.VentasFix.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDto {

    @NotBlank(message = "El SKU es obligatorio")
    private String sku;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción corta es obligatoria")
    private String descripcionCorta;

    @NotBlank(message = "La descripción larga es obligatoria")
    private String descripcionLarga;

    @NotBlank(message = "La imagen del producto es obligatoria")
    private String imagen; // nombre/ruta del archivo ya subido

    @NotNull(message = "El precio neto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio neto debe ser mayor a 0")
    private BigDecimal precioNeto; // precioVenta NO se pide aquí, lo calcula el backend

    @NotNull(message = "El stock actual es obligatorio")
    @Min(value = 0, message = "El stock actual no puede ser negativo")
    private Integer stockActual;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;

    @NotNull(message = "El stock bajo es obligatorio")
    @Min(value = 0, message = "El stock bajo no puede ser negativo")
    private Integer stockBajo;

    @NotNull(message = "El stock alto es obligatorio")
    @Min(value = 0, message = "El stock alto no puede ser negativo")
    private Integer stockAlto;
}
