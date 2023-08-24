package ec.edu.espe.arquitectura.facturacion.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.arquitectura.facturacion.model.ImpuestoPorcentaje;
import ec.edu.espe.arquitectura.facturacion.service.ImpuestoService;

@RestController
@RequestMapping("/api/v1/porcentajeImp")
public class ImpuestoPorcentajeController {
    private final ImpuestoService impuestoPorcentajeService;

    public ImpuestoPorcentajeController(ImpuestoService impuestoPorcentajeService) {
        this.impuestoPorcentajeService = impuestoPorcentajeService;
    }

    @GetMapping("/{porcentaje}/{codigoImpuesto}/{fechaInicio}")
    public ResponseEntity<ImpuestoPorcentaje> obtainByPK(
            @PathVariable(name = "porcentaje") BigDecimal porcentaje,
            @PathVariable(name = "codigoImpuesto") String codigoImpuesto,
            @PathVariable(name = "fechaInicio") Date fechaInicio) {
        Optional<ImpuestoPorcentaje> impuestoPorcentaje = this.impuestoPorcentajeService
                .obtainImpuestoPorcentajeByCode(codigoImpuesto, porcentaje, fechaInicio);
        if (impuestoPorcentaje.isPresent()) {
            return ResponseEntity.ok(impuestoPorcentaje.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{codigoImpuesto}")
    public ResponseEntity<List<ImpuestoPorcentaje>> obtainPorcentajeByImpuesto(
            @PathVariable(name = "codigoImpuesto") String codigoImpuesto) {
        List<ImpuestoPorcentaje> impuestos = this.impuestoPorcentajeService.obtainPorcentajeDeImpuesto(codigoImpuesto);
        if (impuestos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(impuestos);
        }
    }

    @PostMapping
    public ResponseEntity<ImpuestoPorcentaje> create(@RequestBody ImpuestoPorcentaje impuestoPorcentaje) {
        try {
            ImpuestoPorcentaje impuestoPorcentajeRS = this.impuestoPorcentajeService
                    .createImpuestoPorcentaje(impuestoPorcentaje);
            return ResponseEntity.ok(impuestoPorcentajeRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

}