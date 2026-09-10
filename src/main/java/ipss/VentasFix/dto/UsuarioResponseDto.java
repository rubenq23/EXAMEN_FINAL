package ipss.VentasFix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    // sin password: nunca se expone, ni siquiera cifrado
}
