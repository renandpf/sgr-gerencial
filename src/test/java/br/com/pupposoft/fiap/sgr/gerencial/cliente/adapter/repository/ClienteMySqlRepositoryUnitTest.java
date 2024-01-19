package br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ClienteEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ClienteEntityRepository;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ErrorToAccessRepositoryException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ClienteMySqlRepositoryUnitTest {

	@InjectMocks
	private ClienteGateway obterClienteUsecase = new ClienteMySqlRepository(null);
	
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
		
		Optional<ClienteDto> clientFoundOP = obterClienteUsecase.obterPorCpf(cpfParam);
		
		verify(clienteEntityRepository).findByCpf(cpfParam);
		
		ClienteDto clienteDto = clientFoundOP.get();
		
		assertEquals(clienteEntityExistente.getId(), clienteDto.getId());
		assertEquals(clienteEntityExistente.getNome(), clienteDto.getNome());
		assertEquals(clienteEntityExistente.getCpf(), clienteDto.getCpf());
		assertEquals(clienteEntityExistente.getEmail(), clienteDto.getEmail());
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnObterPorCpf() {
		final String cpfParam = DataBuilderBase.getRandomString();
		doThrow(new RuntimeException()).when(clienteEntityRepository).findByCpf(cpfParam);
		assertThrows(ErrorToAccessRepositoryException.class, () -> obterClienteUsecase.obterPorCpf(cpfParam));
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
		
		Optional<ClienteDto> clientFoundOP = obterClienteUsecase.obterPorEmail(emailParam);
		
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
		assertThrows(ErrorToAccessRepositoryException.class, () -> obterClienteUsecase.obterPorEmail(emailParam));
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
		
		Optional<ClienteDto> clientFoundOP = obterClienteUsecase.obterPorId(clientIdParam);
		
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
		assertThrows(ErrorToAccessRepositoryException.class, () -> obterClienteUsecase.obterPorId(clienteIdParam));
		verify(clienteEntityRepository).findById(clienteIdParam);
	}
}
