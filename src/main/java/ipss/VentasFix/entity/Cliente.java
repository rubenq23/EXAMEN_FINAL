package ipss.VentasFix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT empresa es obligatorio")
    @Column(name = "RUT_EMPRESA", nullable = false, unique = true, length = 12)
    private String rutEmpresa;

    @NotBlank(message = "El rubro es obligatorio")
    @Column(name = "RUBRO", nullable = false, length = 100)
    private String rubro;

    @NotBlank(message = "La razón social es obligatoria")
    @Column(name = "RAZON_SOCIAL", nullable = false, length = 150)
    private String razonSocial;

    @NotBlank(message = "El teléfono es obligatorio")
    @Column(name = "TELEFONO", nullable = false, length = 20)
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(name = "DIRECCION", nullable = false, length = 255)
    private String direccion;

    @NotBlank(message = "El nombre de contacto es obligatorio")
    @Column(name = "NOMBRE_CONTACTO", nullable = false, length = 150)
    private String nombreContacto;

    @NotBlank(message = "El email de contacto es obligatorio")
    @Email(message = "El email de contacto debe tener un formato válido")
    @Column(name = "EMAIL_CONTACTO", nullable = false, length = 150)
    private String emailContacto;

}
