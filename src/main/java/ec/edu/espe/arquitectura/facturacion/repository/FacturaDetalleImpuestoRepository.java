package ec.edu.espe.arquitectura.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.FacturaDetalleImpuesto;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaDetalleImpuestoRepository extends JpaRepository<FacturaDetalleImpuesto, Integer> {

}