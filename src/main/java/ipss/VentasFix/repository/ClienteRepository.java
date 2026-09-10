package ipss.VentasFix.repository;

import ipss.VentasFix.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByRutEmpresa(String rutEmpresa);
}
