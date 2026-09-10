package ipss.VentasFix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDto {

    private Long id;
    private String rutEmpresa;
    private String rubro;
    private String razonSocial;
    private String telefono;
    private String direccion;
    private String nombreContacto;
    private String emailContacto;
}
