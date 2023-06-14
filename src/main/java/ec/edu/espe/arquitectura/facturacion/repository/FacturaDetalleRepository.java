package ec.edu.espe.arquitectura.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.Factura;
import ec.edu.espe.arquitectura.facturacion.model.FacturaDetalle;
import org.springframework.stereotype.Repository;

@Repository

public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Integer> {
    List<FacturaDetalle> findByCodigoFactura(Integer codigoFactura);

    List<FacturaDetalle> findByFactura(Factura factura);

}