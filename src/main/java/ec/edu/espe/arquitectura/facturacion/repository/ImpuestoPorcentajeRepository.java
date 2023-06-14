package ec.edu.espe.arquitectura.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.arquitectura.facturacion.model.ImpuestoPorcentaje;
import ec.edu.espe.arquitectura.facturacion.model.ImpuestoPorcentajePK;

public interface ImpuestoPorcentajeRepository extends JpaRepository<ImpuestoPorcentaje, ImpuestoPorcentajePK> {

}
