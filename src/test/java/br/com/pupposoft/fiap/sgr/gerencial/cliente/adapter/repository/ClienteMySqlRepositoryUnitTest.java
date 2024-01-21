package br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ClienteEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ClienteEntityRepository;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ErrorToAccessRepositoryException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ClienteMySqlRepositoryUnitTest {

	@InjectMocks
	private ClienteGateway clienteGateway = new ClienteMySqlRepository(null);
	
	@Mock
	private ClienteEntityRepository clienteEntityRepository;
	
	@Test
	void shouldSucessOnObterPorCpf() {
		final String cpfParam = DataBuilderBase.getRandomString();
		
		ClienteEntity clienteEntityExistente = ClienteEntity.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		doReturn(Optional.of(clienteEntityExistente))
			.when(clienteEntityRepository).findByCpf(cpfParam);
		
		Optional<ClienteDto> clientFoundOP = clienteGateway.obterPorCpf(cpfParam);
		
		verify(clienteEntityRepository).findByCpf(cpfParam);
		
		ClienteDto clienteDto = clientFoundOP.get();
		
		assertEquals(clienteEntityExistente.getId(), clienteDto.getId());
		assertEquals(clienteEntityExistente.getNome(), clienteDto.getNome());
		assertEquals(clienteEntityExistente.getCpf(), clienteDto.getCpf());
		assertEquals(clienteEntityExistente.getEmail(), clienteDto.getEmail());
	}
	
	@Test
	void shouldSucessButNotFounfOnObterPorCpf() {
		final String cpfParam = DataBuilderBase.getRandomString();
		
		doReturn(Optional.empty()).when(clienteEntityRepository).findByCpf(cpfParam);
		
		Optional<ClienteDto> clientFoundOP = clienteGateway.obterPorCpf(cpfParam);
		
		assertTrue(clientFoundOP.isEmpty());
		
		verify(clienteEntityRepository).findByCpf(cpfParam);
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnObterPorCpf() {
		final String cpfParam = DataBuilderBase.getRandomString();
		doThrow(new RuntimeException()).when(clienteEntityRepository).findByCpf(cpfParam);
		assertThrows(ErrorToAccessRepositoryException.class, () -> clienteGateway.obterPorCpf(cpfParam));
		verify(clienteEntityRepository).findByCpf(cpfParam);
	}
	
	@Test
	void shouldSucessOnobterPorEmail() {
		final String emailParam = DataBuilderBase.getRandomString();
		
		ClienteEntity clienteEntityExistente = ClienteEntity.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		doReturn(Optional.of(clienteEntityExistente))
			.when(clienteEntityRepository).findByEmail(emailParam);
		
		Optional<ClienteDto> clientFoundOP = clienteGateway.obterPorEmail(emailParam);
		
		verify(clienteEntityRepository).findByEmail(emailParam);
		
		ClienteDto clienteDto = clientFoundOP.get();
		
		assertEquals(clienteEntityExistente.getId(), clienteDto.getId());
		assertEquals(clienteEntityExistente.getNome(), clienteDto.getNome());
		assertEquals(clienteEntityExistente.getCpf(), clienteDto.getCpf());
		assertEquals(clienteEntityExistente.getEmail(), clienteDto.getEmail());
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnObterPorEmail() {
		final String emailParam = DataBuilderBase.getRandomString();
		doThrow(new RuntimeException()).when(clienteEntityRepository).findByEmail(emailParam);
		assertThrows(ErrorToAccessRepositoryException.class, () -> clienteGateway.obterPorEmail(emailParam));
		verify(clienteEntityRepository).findByEmail(emailParam);
	}
	
	@Test
	void shouldSucessOnObterPorId() {
		final Long clientIdParam = DataBuilderBase.getRandomLong();
		
		ClienteEntity clienteEntityExistente = ClienteEntity.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		doReturn(Optional.of(clienteEntityExistente))
			.when(clienteEntityRepository).findById(clientIdParam);
		
		Optional<ClienteDto> clientFoundOP = clienteGateway.obterPorId(clientIdParam);
		
		verify(clienteEntityRepository).findById(clientIdParam);
		
		ClienteDto clienteDto = clientFoundOP.get();
		
		assertEquals(clienteEntityExistente.getId(), clienteDto.getId());
		assertEquals(clienteEntityExistente.getNome(), clienteDto.getNome());
		assertEquals(clienteEntityExistente.getCpf(), clienteDto.getCpf());
		assertEquals(clienteEntityExistente.getEmail(), clienteDto.getEmail());
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnObterPorId() {
		final Long clienteIdParam = DataBuilderBase.getRandomLong();
		doThrow(new RuntimeException()).when(clienteEntityRepository).findById(clienteIdParam);
		assertThrows(ErrorToAccessRepositoryException.class, () -> clienteGateway.obterPorId(clienteIdParam));
		verify(clienteEntityRepository).findById(clienteIdParam);
	}
	
	@Test
	void shouldSucessOnCriar() {
		
		ClienteDto clienteToBeCreate = ClienteDto.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		
		CriarClienteParamsDto criarClienteParamsDto = CriarClienteParamsDto.builder()
				.cliente(clienteToBeCreate)
				.build();
		
		ClienteEntity clienteEntity = ClienteEntity.builder().id(clienteToBeCreate.getId()).build(); 
		doReturn(clienteEntity).when(clienteEntityRepository).save(any(ClienteEntity.class));
		
		CriarClienteReturnDto returnDto = clienteGateway.criar(criarClienteParamsDto);

		assertEquals(clienteToBeCreate.getId(), returnDto.getClienteId());
		
		ArgumentCaptor<ClienteEntity> clientEntityAC = ArgumentCaptor.forClass(ClienteEntity.class);
		
		verify(clienteEntityRepository).save(clientEntityAC.capture());
		
		ClienteEntity clientEntitySaved = clientEntityAC.getValue();
		
		assertEquals(clienteToBeCreate.getId(), clientEntitySaved.getId());
		assertEquals(clienteToBeCreate.getNome(), clientEntitySaved.getNome());
		assertEquals(clienteToBeCreate.getCpf(), clientEntitySaved.getCpf());
		assertEquals(clienteToBeCreate.getEmail(), clientEntitySaved.getEmail());
	}

	@Test
	void shouldErrorToCriar() {
		doThrow(new RuntimeException()).when(clienteEntityRepository).save(any(ClienteEntity.class));
		
		CriarClienteParamsDto criarClienteParamsDto = CriarClienteParamsDto.builder().cliente(ClienteDto.builder().build()).build();
		
		assertThrows(ErrorToAccessRepositoryException.class, () -> clienteGateway.criar(criarClienteParamsDto));
		verify(clienteEntityRepository).save(any(ClienteEntity.class));
	}

	@Test
	void shouldSucessOnAlterar() {
		
		ClienteDto clienteToBeUpdate = ClienteDto.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		
		AlterarClienteParamsDto alterarClienteParamsDto = AlterarClienteParamsDto.builder()
				.cliente(clienteToBeUpdate)
				.build();
		
		ClienteEntity clienteEntity = ClienteEntity.builder().id(clienteToBeUpdate.getId()).build(); 
		doReturn(clienteEntity).when(clienteEntityRepository).save(any(ClienteEntity.class));
		
		AlterarClienteReturnDto returnDto = clienteGateway.alterar(alterarClienteParamsDto);

		assertNotNull(returnDto);
		
		ArgumentCaptor<ClienteEntity> clientEntityAC = ArgumentCaptor.forClass(ClienteEntity.class);
		
		verify(clienteEntityRepository).save(clientEntityAC.capture());
		
		ClienteEntity clientEntitySaved = clientEntityAC.getValue();
		
		assertEquals(clienteToBeUpdate.getId(), clientEntitySaved.getId());
		assertEquals(clienteToBeUpdate.getNome(), clientEntitySaved.getNome());
		assertEquals(clienteToBeUpdate.getCpf(), clientEntitySaved.getCpf());
		assertEquals(clienteToBeUpdate.getEmail(), clientEntitySaved.getEmail());
	}
	
	@Test
	void shouldErrorToAlterar() {
		doThrow(new RuntimeException()).when(clienteEntityRepository).save(any(ClienteEntity.class));
		
		AlterarClienteParamsDto alterarClienteParamsDto = AlterarClienteParamsDto.builder().cliente(ClienteDto.builder().build()).build();
		
		assertThrows(ErrorToAccessRepositoryException.class, () -> clienteGateway.alterar(alterarClienteParamsDto));
		verify(clienteEntityRepository).save(any(ClienteEntity.class));
	}
	
}
