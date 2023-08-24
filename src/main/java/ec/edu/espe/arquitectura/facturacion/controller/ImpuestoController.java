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

import ec.edu.espe.arquitectura.facturacion.model.Impuesto;
import ec.edu.espe.arquitectura.facturacion.service.ImpuestoService;

@RestController
@RequestMapping("/api/v1/impuestos")
public class ImpuestoController {

    private final ImpuestoService impuestoService;

    public ImpuestoController(ImpuestoService impuestoService) {
        this.impuestoService = impuestoService;
    }

    @GetMapping
    public ResponseEntity<List<Impuesto>> obtainAll() {
        List<Impuesto> impuestos = this.impuestoService.obtainAllImpuestos();
        return ResponseEntity.ok(impuestos);
    }

    @GetMapping("/{codigoImpuesto}")
    public ResponseEntity<Impuesto> obtainByCodigoImpuesto(
            @PathVariable(name = "codigoImpuesto") String codigoImpuesto) {
        Optional<Impuesto> impuesto = this.impuestoService.obtainImpuestoByCode(codigoImpuesto);
        if (impuesto.isPresent()) {
            return ResponseEntity.ok(impuesto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Impuesto> create(@RequestBody Impuesto impuesto) {
        try {
            Impuesto impuestoRS = this.impuestoService.createImpuesto(impuesto);
            return ResponseEntity.ok(impuestoRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping
    public ResponseEntity<Impuesto> update(@RequestBody Impuesto impuesto) {
        try {
            Impuesto impuestoRS = this.impuestoService.updateImpuesto(impuesto);
            return ResponseEntity.ok(impuestoRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }

    }
}