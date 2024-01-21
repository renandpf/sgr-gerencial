package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase;

import java.util.Optional;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.domain.Cliente;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AlterarClienteUsecaseImpl implements AlterarClienteUsecase {
	
	private ClienteGateway clienteGateway;

	public AlterarClienteReturnDto alterar(AlterarClienteParamsDto paramsDto) {
        Cliente cliente = mapDtoToDomain(paramsDto.getCliente());

        cliente.validar();

        Optional<ClienteDto> clienteOp = this.clienteGateway.obterPorId(cliente.getId());
        
        if (clienteOp.isEmpty()) {
        	log.warn("Cliente não encontrado: cpf={}", paramsDto.getCliente().getCpf());
            throw new ClienteNaoEncontradoException();
        }
        
        return this.clienteGateway.alterar(paramsDto);
	}
	
    private Cliente mapDtoToDomain(ClienteDto dto)  {
        return Cliente.builder()
        		.id(dto.getId())
        		.nome(dto.getNome())
        		.cpf(dto.getCpf())
        		.email(dto.getEmail())
        		.build();
    }

	
}
