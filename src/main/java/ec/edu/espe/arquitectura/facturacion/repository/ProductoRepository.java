package ec.edu.espe.arquitectura.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, String> {

    List<Producto> findByNombre(String nombre);

    List<Producto> findByNombreLikeOrderByNombre(String nombre);

}