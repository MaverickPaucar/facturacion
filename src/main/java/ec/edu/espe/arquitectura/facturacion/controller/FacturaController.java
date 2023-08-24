package ec.edu.espe.arquitectura.facturacion.controller;

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

import ec.edu.espe.arquitectura.facturacion.model.Factura;
import ec.edu.espe.arquitectura.facturacion.model.FacturaDetalle;
import ec.edu.espe.arquitectura.facturacion.service.FacturaService;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Factura> obtainByCode(@PathVariable(name = "code") Integer code) {
        Optional<Factura> factura = this.facturaService.obtainByCode(code);
        if (factura.isPresent()) {
            return ResponseEntity.ok(factura.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/facturadetalle/{facturaDetalleCodigo}")
    public ResponseEntity<FacturaDetalle> obtainByFacturaDetalleCode(
            @PathVariable(name = "facturaDetalleCodigo") Integer facturaDetalleCodigo) {
        Optional<FacturaDetalle> factura = this.facturaService.obtainByFacturaDetalleCode(facturaDetalleCodigo);
        if (factura.isPresent()) {
            return ResponseEntity.ok(factura.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{codigoCliente}")

    public ResponseEntity<List<Factura>> obtainFacturaByCliente(

            @PathVariable(name = "codigoCliente") Integer codigoCliente) {
        List<Factura> factura = this.facturaService.obtainByCliente(codigoCliente);
        if (factura.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(factura);
        }
    }

    @GetMapping("/detalle/{codigoCliente}")

    public ResponseEntity<List<FacturaDetalle>> obtainFacturaDetalleByCliente(

            @PathVariable(name = "codigoCliente") Integer codigoCliente) {
        List<FacturaDetalle> factura = this.facturaService.obtainByClienteCode(codigoCliente);
        if (factura.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(factura);
        }
    }

    @PostMapping
    public ResponseEntity<Factura> create(@RequestBody Factura factura) {
        try {
            Factura facturaRS = this.facturaService.crearFactura(factura);
            return ResponseEntity.ok(facturaRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Factura> update(@RequestBody Factura factura) {
        try {
            Factura facturaRS = this.facturaService.updateFactura(factura);
            return ResponseEntity.ok(facturaRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }
}