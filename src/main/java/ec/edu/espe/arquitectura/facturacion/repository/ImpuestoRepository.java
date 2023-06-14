package ec.edu.espe.arquitectura.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.Impuesto;
import org.springframework.stereotype.Repository;

@Repository

public interface ImpuestoRepository extends JpaRepository<Impuesto, String> {
    List<Impuesto> findByNombre(String nombre);

    List<Impuesto> findByNombreLikeOrderByNombre(String nombre);

    List<Impuesto> findBySiglas(String siglas);

}