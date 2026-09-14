package ipss.VentasFix.service;

import ipss.VentasFix.dto.ClienteRequestDto;
import ipss.VentasFix.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
    List<ClienteResponseDto> listarTodos();
    ClienteResponseDto obtenerPorId(Long id);
    ClienteResponseDto crear(ClienteRequestDto dto);
    ClienteResponseDto actualizar(Long id, ClienteRequestDto dto);
    void eliminar(Long id);
}
