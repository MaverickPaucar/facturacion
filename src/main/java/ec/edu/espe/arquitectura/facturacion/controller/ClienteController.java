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

import ec.edu.espe.arquitectura.facturacion.model.Cliente;
import ec.edu.espe.arquitectura.facturacion.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Cliente> obtainByCode(@PathVariable(name = "code") Integer code) {
        Optional<Cliente> cliente = this.clienteService.obtainByCode(code);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{tipoIdentificacion}/{name}")
    public ResponseEntity<List<Cliente>> obtainByTipoIdentificacionAndName(
            @PathVariable(name = "tipoIdentificacion") String tipoIdentificacion,
            @PathVariable(name = "name") String name) {
        List<Cliente> clientes = this.clienteService.listByTipoIdentificacionAndPattern(tipoIdentificacion, name);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        try {
            Cliente clienteRS = this.clienteService.create(cliente);
            return ResponseEntity.ok(clienteRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
        try {
            Cliente clienteRS = this.clienteService.update(cliente);
            return ResponseEntity.ok(clienteRS);
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }
}
