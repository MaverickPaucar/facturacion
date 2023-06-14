package ec.edu.espe.arquitectura.facturacion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.arquitectura.facturacion.model.Cliente;
import ec.edu.espe.arquitectura.facturacion.model.Factura;
import ec.edu.espe.arquitectura.facturacion.model.FacturaDetalle;
import ec.edu.espe.arquitectura.facturacion.repository.ClienteRepository;
import ec.edu.espe.arquitectura.facturacion.repository.FacturaDetalleRepository;
import ec.edu.espe.arquitectura.facturacion.repository.FacturaRepository;
import jakarta.transaction.Transactional;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;
    private final FacturaDetalleRepository facturaDetalleRepository;
    private final ClienteRepository clienteRepository;

    public FacturaService(FacturaRepository facturaRepository, FacturaDetalleRepository facturaDetalleRepository,
            ClienteRepository clienteRepository) {
        this.facturaRepository = facturaRepository;
        this.facturaDetalleRepository = facturaDetalleRepository;
        this.clienteRepository = clienteRepository;
    }

    public Optional<Factura> obtainByCode(Integer codigo) {
        return this.facturaRepository.findById(codigo);

    }

    public Optional<FacturaDetalle> obtainByFacturaDetalleCode(Integer codigoFacturaDetalle) {
        return this.facturaDetalleRepository.findById(codigoFacturaDetalle);
    }

    public List<Factura> obtainByCliente(Integer codigo) {
        Optional<Cliente> clienteOpt = this.clienteRepository.findById(codigo);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            return this.facturaRepository.findByCliente(cliente);
        } else {
            throw new RuntimeException("El cliente no existe");
        }
    }

    public List<FacturaDetalle> obtainByClienteCode(Integer codigo) {
        List<Factura> factura = this.facturaRepository.findByCodigoCliente(codigo);
        if (factura.isEmpty()) {
            throw new RuntimeException("El cliente no está asociado a ninguna factura");
        } else {
            List<FacturaDetalle> facturaDetalle = new ArrayList<>();
            for (Factura facturaTmp : factura) {
                facturaDetalle.addAll(this.facturaDetalleRepository.findByCodigoFactura(facturaTmp.getCodigo()));
            }
            return facturaDetalle;
        }

    }

    @Transactional
    public Factura crearFactura(Factura factura) {
        if (!this.facturaRepository.existsById(factura.getCodigo())) {
            return this.facturaRepository.save(factura);
        } else {
            throw new RuntimeException("Factura ya existe");
        }
    }

    @Transactional
    public Factura updateFactura(Factura factura) {
        try {
            Optional<Factura> facturaOpt = this.facturaRepository.findById(factura.getCodigo());
            if (facturaOpt.isPresent()) {
                Factura facturaTmp = facturaOpt.get();
                facturaTmp.setCodigoCliente(factura.getCodigoCliente());
                facturaTmp.setCodigoEstablecimiento(factura.getCodigoEstablecimiento());
                facturaTmp.setPuntoEmision(factura.getPuntoEmision());
                facturaTmp.setSecuencial(factura.getSecuencial());
                facturaTmp.setAutorizacion(factura.getAutorizacion());
                facturaTmp.setFecha(factura.getFecha());
                facturaTmp.setSubtotal(factura.getSubtotal());
                facturaTmp.setTotal(factura.getTotal());
                this.facturaRepository.save(facturaTmp);
                return facturaTmp;
            } else {
                throw new RuntimeException("La factura que desea modificar no esta registrada");
            }
        } catch (RuntimeException rte) {
            throw new RuntimeException("Error al editar los campos", rte);
        }
    }

    @Transactional
    public void delete(Integer facturaCode) {
        try {
            Optional<Factura> facturaOpt = this.facturaRepository.findById(facturaCode);
            if (facturaOpt.isPresent()) {
                this.facturaRepository.delete(facturaOpt.get());
            } else {
                throw new RuntimeException("La factura  no esta registrada: " + facturaCode);
            }
        } catch (RuntimeException rte) {
            throw new RuntimeException("No se puede eliminar la factura con Codigo: " + facturaCode, rte);
        }

    }

}