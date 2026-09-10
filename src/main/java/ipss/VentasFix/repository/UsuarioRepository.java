package ipss.VentasFix.repository;

import ipss.VentasFix.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email); // usado para el login

    boolean existsByEmail(String email);  //buscar el Usuario por email para validar la contraseña.

    boolean existsByRut(String rut);  //Evitar duplicados, antes de crear un usuarioRUT no exista
}
