package ec.edu.espe.arquitectura.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.Impuesto;
import ec.edu.espe.arquitectura.facturacion.model.ImpuestoPorcentaje;
import ec.edu.espe.arquitectura.facturacion.model.ImpuestoPorcentajePK;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpuestoPorcentajeRepository extends JpaRepository<ImpuestoPorcentaje, ImpuestoPorcentajePK> {
    List<ImpuestoPorcentaje> findByImpuestoAndEstado(Impuesto impuesto, String estado);

}