package ipss.VentasFix.service.implement;

import ipss.VentasFix.dto.UsuarioRequestDto;
import ipss.VentasFix.dto.UsuarioResponseDto;
import ipss.VentasFix.entity.Usuario;
import ipss.VentasFix.mapper.UsuarioMapper;
import ipss.VentasFix.repository.UsuarioRepository;
import ipss.VentasFix.service.ClienteService;
import ipss.VentasFix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImplement implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;

    @Override
    public List<UsuarioResponseDto> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponseDto)
                .toList();
    }

    @Override
    public UsuarioResponseDto obtenerPorId(Long id) {
        return UsuarioMapper.toResponseDto(buscarPorIdOLanzarError(id));
    }

    @Override
    public UsuarioResponseDto crear(UsuarioRequestDto dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese email");
        }
        if (usuarioRepository.existsByRut(dto.getRut())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese RUT");
        }

        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword())); // cifrado antes de guardar

        return UsuarioMapper.toResponseDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDto actualizar(Long id, UsuarioRequestDto dto) {
        Usuario usuario = buscarPorIdOLanzarError(id);

        if (!usuario.getEmail().equals(dto.getEmail()) && usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese email");
        }

        usuario.setRut(dto.getRut());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword())); // se re-cifra siempre

        return UsuarioMapper.toResponseDto(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.delete(buscarPorIdOLanzarError(id));
    }

    private Usuario buscarPorIdOLanzarError(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con id " + id));
    }
}
