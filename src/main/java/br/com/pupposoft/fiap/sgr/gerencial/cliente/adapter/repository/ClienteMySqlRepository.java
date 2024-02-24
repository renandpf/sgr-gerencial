package br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ClienteEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.StatusCadastro;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ClienteEntityRepository;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ErrorToAccessRepositoryException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class ClienteMySqlRepository implements ClienteGateway {

	private ClienteEntityRepository clienteEntityRepository; 

	@Override
	public Optional<ClienteDto> obterPorCpf(String cpf) {
		try {
			Optional<ClienteEntity> clientEntityOp = clienteEntityRepository.findByCpf(cpf);
			return mapEntityOpToDtoOp(clientEntityOp);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToAccessRepositoryException();
		}
	}

	@Override
	public AlterarClienteReturnDto alterar(AlterarClienteParamsDto paramsDto) {
		try {
			this.clienteEntityRepository.save(mapDtoToEntity(paramsDto.getCliente()));
			return new AlterarClienteReturnDto();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToAccessRepositoryException();
		}
	}

	@Override
	public Optional<ClienteDto> obterPorEmail(String email) {
		try {
			Optional<ClienteEntity> clientEntityOp = this.clienteEntityRepository.findByEmail(email);
			return mapEntityOpToDtoOp(clientEntityOp);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToAccessRepositoryException();
		}
	}

	@Override
	public CriarClienteReturnDto criar(CriarClienteParamsDto paramsDto) {
		try {
			ClienteEntity clientEntity = this.clienteEntityRepository.save(mapDtoToEntity(paramsDto.getCliente()));
			return CriarClienteReturnDto.builder().clienteId(clientEntity.getId()).build();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToAccessRepositoryException();
		}
	}

	@Override
	public Optional<ClienteDto> obterPorId(Long id) {
		try {
			Optional<ClienteEntity> clientEntityOp = this.clienteEntityRepository.findById(id);
			return mapEntityOpToDtoOp(clientEntityOp);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToAccessRepositoryException();
		}	
	}

	@Override
	public void excluirPorId(Long id) {
		try {
			ClienteEntity crientToBeRemove = ClienteEntity.builder()
					.id(id)
					.statusCadastro(StatusCadastro.INATIVO)
					.build();

			clienteEntityRepository.save(crientToBeRemove);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToAccessRepositoryException();
		}	
	}
	
	private Optional<ClienteDto> mapEntityOpToDtoOp(Optional<ClienteEntity> clientEntityOp) {
		return clientEntityOp.isPresent() ? Optional.of(mapEntityToDto(clientEntityOp.get())) : Optional.empty();
	}
	
	private ClienteEntity mapDtoToEntity(ClienteDto dto) {
		
		return ClienteEntity.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.cpf(dto.getCpf())
				.email(dto.getEmail())
				.endereco(dto.getEndereco())
				.telefone(dto.getTelefone())
				.statusCadastro(dto.getStatusCadastro())
				.build();
	}
	
	private ClienteDto mapEntityToDto(ClienteEntity entity) {
		
		return ClienteDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.cpf(entity.getCpf())
				.email(entity.getEmail())
				.build();
	}
}
