package ec.edu.espe.arquitectura.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.Producto;
import java.util.List;
import java.math.BigDecimal;

public interface ProductoRepository extends JpaRepository<Producto, String> {

    List<Producto> findByNombreContainingAndEstado(String nombre, String estado);

    List<Producto> findByEstadoOrderByNombreAsc(String estado);

    Producto findByCodigo(String codigo);

    List<Producto> findByExistenciaLessThanAndEstado(BigDecimal existencia, String estado);
}