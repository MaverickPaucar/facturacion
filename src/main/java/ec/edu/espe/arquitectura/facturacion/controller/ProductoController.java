package ec.edu.espe.arquitectura.facturacion.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.arquitectura.facturacion.model.Producto;
import ec.edu.espe.arquitectura.facturacion.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Producto> obtainByCode(@PathVariable(name = "code") String code) {
        Optional<Producto> producto = this.productoService.obtainByCode(code);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{existencia}/{estado}")
    public ResponseEntity<List<Producto>> obtainByExistenciaAndEstado(
            @PathVariable(name = "existencia") BigDecimal existencia,
            @PathVariable(name = "estado") String estado) {
        List<Producto> productos = this.productoService.listByExistenciaAndEstado(existencia, estado);
        return ResponseEntity.ok(productos);

    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Producto>> obtainByNombre(@PathVariable(name = "nombre") String nombre) {
        List<Producto> productos = this.productoService.listByNombre(nombre);
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(productos);
        }

    }

    @GetMapping("/inventario/{estado}")

    public ResponseEntity<List<Producto>> obtainByEstado(@PathVariable(name = "estado") String estado) {
        List<Producto> productos = this.productoService.listByEstado(estado);
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(productos);
        }

    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        try {
            Producto productoRS = this.productoService.create(producto);
            return ResponseEntity.ok(productoRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        try {
            Producto productoRS = this.productoService.update(producto);
            return ResponseEntity.ok(productoRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

}