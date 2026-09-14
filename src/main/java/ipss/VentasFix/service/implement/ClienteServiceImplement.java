package ipss.VentasFix.service.implement;

import ipss.VentasFix.dto.ClienteRequestDto;
import ipss.VentasFix.dto.ClienteResponseDto;
import ipss.VentasFix.entity.Cliente;
import ipss.VentasFix.mapper.ClienteMapper;
import ipss.VentasFix.repository.ClienteRepository;
import ipss.VentasFix.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImplement implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponseDto> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }

    @Override
    public ClienteResponseDto obtenerPorId(Long id) {
        return ClienteMapper.toResponseDto(buscarPorIdOLanzarError(id));
    }

    @Override
    public ClienteResponseDto crear(ClienteRequestDto dto) {
        if (clienteRepository.existsByRutEmpresa(dto.getRutEmpresa())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con ese RUT empresa");
        }

        Cliente cliente = ClienteMapper.toEntity(dto);
        return ClienteMapper.toResponseDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteResponseDto actualizar(Long id, ClienteRequestDto dto) {
        Cliente cliente = buscarPorIdOLanzarError(id);

        if (!cliente.getRutEmpresa().equals(dto.getRutEmpresa()) && clienteRepository.existsByRutEmpresa(dto.getRutEmpresa())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con ese RUT empresa");
        }

        cliente.setRutEmpresa(dto.getRutEmpresa());
        cliente.setRubro(dto.getRubro());
        cliente.setRazonSocial(dto.getRazonSocial());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setNombreContacto(dto.getNombreContacto());
        cliente.setEmailContacto(dto.getEmailContacto());

        return ClienteMapper.toResponseDto(clienteRepository.save(cliente));
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.delete(buscarPorIdOLanzarError(id));
    }

    private Cliente buscarPorIdOLanzarError(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con id " + id));
    }
}
