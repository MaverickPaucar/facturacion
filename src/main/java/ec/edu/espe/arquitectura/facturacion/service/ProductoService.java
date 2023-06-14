package ec.edu.espe.arquitectura.facturacion.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.arquitectura.facturacion.model.Producto;
import ec.edu.espe.arquitectura.facturacion.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Optional<Producto> obtainByCode(String code) {
        return this.productoRepository.findById(code);
    }

    public List<Producto> listByExistenciaAndEstado(BigDecimal cantidad, String estado) {
        return this.productoRepository.findByExistenciaLessThanAndEstado(cantidad, estado);
    }

    public List<Producto> listByNombre(String nombre) {
        return this.productoRepository.findByNombreContainingAndEstado(nombre, "ACT"); // review
    }

    public List<Producto> listByEstado(String estado) {
        return this.productoRepository.findByEstadoOrderByNombreAsc(estado);
    }

    @Transactional
    public Producto create(Producto producto) {
        Producto productoTmp = this.productoRepository.findByCodigo(producto.getCodigo());
        if (productoTmp == null) {
            return this.productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto ya existe");
        }
    }

    @Transactional
    public Producto update(Producto producto) {
        Optional<Producto> productoOpt = this.productoRepository.findById(producto.getCodigo());
        if (productoOpt.isPresent()) {
            Producto productoTmp = productoOpt.get();
            productoTmp.setNombre(producto.getNombre());
            productoTmp.setDescripcion(producto.getDescripcion());
            productoTmp.setPrecio(producto.getPrecio());
            productoTmp.setExistencia(producto.getExistencia());
            productoTmp.setEstado(producto.getEstado());
            productoTmp.setIva(producto.getIva());
            productoTmp.setIce(producto.getIce());
            this.productoRepository.save(productoTmp); // update
            return productoTmp;
        } else {
            throw new RuntimeException("Producto que desea modificar no esta registrado");
        }
    }

    @Transactional
    public void delete(String productoCode) {
        try {
            Optional<Producto> productoOpt = this.productoRepository.findById(productoCode);
            if (productoOpt.isPresent()) {
                this.productoRepository.delete(productoOpt.get());
            } else {
                throw new RuntimeException("El prodcuto no esta registrado: " + productoCode);
            }
        } catch (RuntimeException rte) {
            throw new RuntimeException("No se puede eliminar el producto con Codigo: " + productoCode, rte);
        }
    }

}