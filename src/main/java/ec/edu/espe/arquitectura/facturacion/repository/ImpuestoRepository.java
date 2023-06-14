package ec.edu.espe.arquitectura.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.Impuesto;

public interface ImpuestoRepository extends JpaRepository<Impuesto, String> {

}
