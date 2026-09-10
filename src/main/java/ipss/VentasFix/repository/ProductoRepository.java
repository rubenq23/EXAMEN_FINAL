package ipss.VentasFix.repository;

import ipss.VentasFix.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsBySku(String sku);
}
