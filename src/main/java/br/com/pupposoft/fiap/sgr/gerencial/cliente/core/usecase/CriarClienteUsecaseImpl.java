package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase;

import java.util.Optional;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.StatusCadastro;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.domain.Cliente;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteExistenteException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CriarClienteUsecaseImpl implements CriarClienteUsecase {
	
	private ClienteGateway clienteGateway;

	@Override
	public CriarClienteReturnDto criar(CriarClienteParamsDto dto) {
        Cliente clienteReq = this.mapDtoToDomain(dto.getCliente());

        clienteReq.validar();

        Optional<ClienteDto> clienteOp = this.clienteGateway.obterPorCpf(clienteReq.getCpf());

        if (clienteOp.isPresent()) {
        	log.warn("Cliente ja cadastro no sistema com o CPF informado.");
            throw new ClienteExistenteException();
        }
        
        dto.getCliente().setStatusCadastro(StatusCadastro.ATIVO);
        
        return this.clienteGateway.criar(dto);
	}
	
    private Cliente mapDtoToDomain(ClienteDto dto)  {
        return Cliente.builder()
        		.id(dto.getId())
        		.nome(dto.getNome())
        		.cpf(dto.getCpf())
        		.email(dto.getEmail())
        		.endereco(dto.getEndereco())
        		.telefone(dto.getTelefone())
        		.statusCadastro(StatusCadastro.ATIVO)
        		.build();
    }
}
