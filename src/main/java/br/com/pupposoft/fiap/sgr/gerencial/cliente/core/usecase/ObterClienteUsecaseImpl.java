package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase;

import java.util.Optional;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ObterClienteUsecaseImpl implements ObterClienteUsecase {

	private ClienteGateway clienteGateway;

	@Override
	public ClienteDto obterPorId(Long id) {
		Optional<ClienteDto> clienteOp = this.clienteGateway.obterPorId(id);
		return this.getClienteDto(clienteOp);
	}

	@Override
	public ClienteDto obterPorCpf(String cpf) {
		Optional<ClienteDto> clienteOp = this.clienteGateway.obterPorCpf(cpf);
		return this.getClienteDto(clienteOp);
	}

	@Override
	public ClienteDto obterPorEmail(String email) {
		Optional<ClienteDto> clienteOp = this.clienteGateway.obterPorEmail(email);
		return this.getClienteDto(clienteOp);
	}

    private ClienteDto getClienteDto(Optional<ClienteDto> clienteOp) {
        if (clienteOp.isEmpty()) {
        	log.warn("Cliente não encontrado");
            throw new ClienteNaoEncontradoException();
        }
        return clienteOp.get();
    }
}
