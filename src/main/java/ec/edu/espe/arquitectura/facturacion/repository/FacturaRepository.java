package ec.edu.espe.arquitectura.facturacion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ec.edu.espe.arquitectura.facturacion.model.Factura;
import org.springframework.stereotype.Repository;

@Repository

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    List<Factura> findByCodigoCliente(Integer codigoCliente);

    List<Factura> findByCodigoEstablecimiento(String codigoEstablecimiento);

    List<Factura> findByFecha(Date fecha);

}