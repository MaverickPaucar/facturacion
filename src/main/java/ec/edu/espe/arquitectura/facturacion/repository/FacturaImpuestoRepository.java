package ec.edu.espe.arquitectura.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.FacturaImpuesto;
import org.springframework.stereotype.Repository;

@Repository

public interface FacturaImpuestoRepository extends JpaRepository<FacturaImpuesto, Integer> {

}