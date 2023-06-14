package ec.edu.espe.arquitectura.facturacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ec.edu.espe.arquitectura.facturacion.model.Cliente;
import ec.edu.espe.arquitectura.facturacion.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> obtainByCode(Integer code) {
        return this.clienteRepository.findById(code);
    }

    public List<Cliente> listByTipoIdentificacionAndPattern(String tipoIdentificacion, String patron) {
        if ("RUC".equals(tipoIdentificacion)) {
            return this.clienteRepository.findByApellidoLikeOrderByApellido(patron);
        } else {
            return this.clienteRepository.findByRazonSocialLikeOrderByRazonSocial(patron);
        }
    }

    @Transactional
    public Cliente create(Cliente cliente) {
        Cliente clienteTmp = this.clienteRepository.findByTipoIdentificacionAndIdentificacion(
                cliente.getTipoIdentificacion(), cliente.getIdentificacion());
        if (clienteTmp == null) {
            return this.clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente ya existe");
        }
    }

    @Transactional
    public Cliente update(Cliente cliente) {
        Optional<Cliente> clienteOpt = this.clienteRepository.findById(cliente.getCodigo());
        if (clienteOpt.isPresent()) {
            Cliente clienteTmp = clienteOpt.get(); // memoria del entity manager
            clienteTmp.setApellido(cliente.getApellido());
            clienteTmp.setNombre(cliente.getNombre());
            clienteTmp.setRazonSocial(cliente.getRazonSocial());
            clienteTmp.setDireccion(cliente.getDireccion());
            clienteTmp.setTelefono(cliente.getTelefono());
            clienteTmp.setEmail(cliente.getEmail());
            this.clienteRepository.save(clienteTmp); // update
            return clienteTmp;
        } else {
            throw new RuntimeException("Cliente que desea modificar no esta registrado");
        }
    }

    @Transactional
    public void delete(Integer clienteCode) {
        try {
            Optional<Cliente> clienteOpt = this.clienteRepository.findById(clienteCode);
            if (clienteOpt.isPresent()) {
                this.clienteRepository.delete(clienteOpt.get());
            } else {
                throw new RuntimeException("El cliente no esta registrado: " + clienteCode);
            }
        } catch (RuntimeException rte) {
            throw new RuntimeException("No se puede eliminar el cliente con Codigo: " + clienteCode, rte);
        }
    }

}
