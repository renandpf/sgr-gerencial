package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ObterClienteUsecaseImplUnitTest {

	@InjectMocks
	private ObterClienteUsecase obterClienteUsecase = new ObterClienteUsecaseImpl(null);
	
	@Mock
	private ClienteGateway clienteRepositoryGateway;
	
	@Test
	void shouldSucessOnObterPorId() {
		final Long clienteId = DataBuilderBase.getRandomLong();
		
		ClienteDto clienteDtoExistent = ClienteDto.builder().build();
		doReturn(Optional.of(clienteDtoExistent)).when(clienteRepositoryGateway).obterPorId(clienteId);
		
		ClienteDto clienteDtoReturned = obterClienteUsecase.obterPorId(clienteId);
		
		assertEquals(clienteDtoExistent, clienteDtoReturned);
		verify(clienteRepositoryGateway).obterPorId(clienteId);
	}
	
	@Test
	void shouldClienteNaoEncontradoExceptionOnObterPorId() {
		final Long clienteId = DataBuilderBase.getRandomLong();
		
		doReturn(Optional.empty()).when(clienteRepositoryGateway).obterPorId(clienteId);
		
		assertThrows(ClienteNaoEncontradoException.class, () -> obterClienteUsecase.obterPorId(clienteId));
		verify(clienteRepositoryGateway).obterPorId(clienteId);
	}
	
	@Test
	void shouldSucessOnObterPorCpf() {
		final String clienteCpf = DataBuilderBase.getRandomString();
		
		ClienteDto clienteDtoExistent = ClienteDto.builder().build();
		doReturn(Optional.of(clienteDtoExistent)).when(clienteRepositoryGateway).obterPorCpf(clienteCpf);
		
		ClienteDto clienteDtoReturned = obterClienteUsecase.obterPorCpf(clienteCpf);
		
		assertEquals(clienteDtoExistent, clienteDtoReturned);
		verify(clienteRepositoryGateway).obterPorCpf(clienteCpf);
	}
	
	@Test
	void shouldClienteNaoEncontradoExceptionOnObterPorCpf() {
		final String clienteCpf = DataBuilderBase.getRandomString();
		
		doReturn(Optional.empty()).when(clienteRepositoryGateway).obterPorCpf(clienteCpf);
		
		assertThrows(ClienteNaoEncontradoException.class, () -> obterClienteUsecase.obterPorCpf(clienteCpf));
		verify(clienteRepositoryGateway).obterPorCpf(clienteCpf);
	}

	@Test
	void shouldSucessOnObterPorEmail() {
		final String clienteEmail = DataBuilderBase.getRandomString();
		
		ClienteDto clienteDtoExistent = ClienteDto.builder().build();
		doReturn(Optional.of(clienteDtoExistent)).when(clienteRepositoryGateway).obterPorEmail(clienteEmail);
		
		ClienteDto clienteDtoReturned = obterClienteUsecase.obterPorEmail(clienteEmail);
		
		assertEquals(clienteDtoExistent, clienteDtoReturned);
		verify(clienteRepositoryGateway).obterPorEmail(clienteEmail);
	}
	
	@Test
	void shouldClienteNaoEncontradoExceptionOnObterPorEmail() {
		final String clienteEmail = DataBuilderBase.getRandomString();
		
		doReturn(Optional.empty()).when(clienteRepositoryGateway).obterPorEmail(clienteEmail);
		
		assertThrows(ClienteNaoEncontradoException.class, () -> obterClienteUsecase.obterPorEmail(clienteEmail));
		verify(clienteRepositoryGateway).obterPorEmail(clienteEmail);
	}
}
