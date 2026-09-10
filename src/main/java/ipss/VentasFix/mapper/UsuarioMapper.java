package ipss.VentasFix.mapper;

import ipss.VentasFix.dto.UsuarioRequestDto;
import ipss.VentasFix.dto.UsuarioResponseDto;
import ipss.VentasFix.entity.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static Usuario toEntity(UsuarioRequestDto dto) {
        Usuario usuario = new Usuario();
        usuario.setRut(dto.getRut());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword()); // aún en texto plano, se cifra en el service
        return usuario;
    }

    public static UsuarioResponseDto toResponseDto(Usuario usuario) {
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(usuario.getId());
        dto.setRut(usuario.getRut());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        return dto; // password nunca se copia
    }
}
