package ipss.VentasFix.mapper;

import ipss.VentasFix.dto.ClienteRequestDto;
import ipss.VentasFix.dto.ClienteResponseDto;
import ipss.VentasFix.entity.Cliente;

public class ClienteMapper {

    private ClienteMapper() {}

    public static Cliente toEntity(ClienteRequestDto dto) {
        Cliente cliente = new Cliente();
        cliente.setRutEmpresa(dto.getRutEmpresa());
        cliente.setRubro(dto.getRubro());
        cliente.setRazonSocial(dto.getRazonSocial());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setNombreContacto(dto.getNombreContacto());
        cliente.setEmailContacto(dto.getEmailContacto());
        return cliente;
    }

    public static ClienteResponseDto toResponseDto(Cliente cliente) {
        ClienteResponseDto dto = new ClienteResponseDto();
        dto.setId(cliente.getId());
        dto.setRutEmpresa(cliente.getRutEmpresa());
        dto.setRubro(cliente.getRubro());
        dto.setRazonSocial(cliente.getRazonSocial());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setNombreContacto(cliente.getNombreContacto());
        dto.setEmailContacto(cliente.getEmailContacto());
        return dto;
    }
}
