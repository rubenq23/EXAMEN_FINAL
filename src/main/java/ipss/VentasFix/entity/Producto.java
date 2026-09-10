package ipss.VentasFix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El SKU es obligatorio")
    @Column(name = "SKU", nullable = false, unique = true, length = 50)
    private String sku;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @NotBlank(message = "La descripción corta es obligatoria")
    @Column(name = "DESCRIPCION_CORTA", nullable = false, length = 255)
    private String descripcionCorta;

    @NotBlank(message = "La descripción larga es obligatoria")
    @Column(name = "DESCRIPCION_LARGA", nullable = false, length = 2000)
    private String descripcionLarga;

    @NotBlank(message = "La imagen del producto es obligatoria")
    @Column(name = "IMAGEN", nullable = false)
    private String imagen; // nombre/ruta del archivo almacenado

    @NotNull(message = "El precio neto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio neto debe ser mayor a 0")
    @Column(name = "PRECIO_NETO", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioNeto;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser mayor a 0")
    @Column(name = "PRECIO_VENTA", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioVenta; // precioNeto * 1.19 (IVA 19%)

    @NotNull(message = "El stock actual es obligatorio")
    @Min(value = 0, message = "El stock actual no puede ser negativo")
    @Column(name = "STOCK_ACTUAL", nullable = false)
    private Integer stockActual;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    @Column(name = "STOCK_MINIMO", nullable = false)
    private Integer stockMinimo;

    @NotNull(message = "El stock bajo es obligatorio")
    @Min(value = 0, message = "El stock bajo no puede ser negativo")
    @Column(name = "STOCK_BAJO", nullable = false)
    private Integer stockBajo;

    @NotNull(message = "El stock alto es obligatorio")
    @Min(value = 0, message = "El stock alto no puede ser negativo")
    @Column(name = "STOCK_ALTO", nullable = false)
    private Integer stockAlto;

}
