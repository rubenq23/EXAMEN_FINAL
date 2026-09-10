package ipss.VentasFix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT es obligatorio")
    @Column(name = "RUT", nullable = false, unique = true, length = 12)
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@ventasfix\\.cl$",
            message = "El email debe ser del dominio @ventasfix.cl")
    @Column(name = "EMAIL", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "PASSWORD", nullable = false)
    private String password; // se almacena ya cifrada (BCrypt), nunca en texto plano
}
