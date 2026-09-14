package ipss.VentasFix.service;

import ipss.VentasFix.dto.UsuarioRequestDto;
import ipss.VentasFix.dto.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDto> listarTodos();
    UsuarioResponseDto obtenerPorId(Long id);
    UsuarioResponseDto crear(UsuarioRequestDto dto);
    UsuarioResponseDto actualizar(Long id, UsuarioRequestDto dto);
    void eliminar(Long id);
}
