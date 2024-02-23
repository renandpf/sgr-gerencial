package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase;

import static br.com.pupposoft.fiap.test.databuilder.DataBuilderBase.getRandomLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;

@ExtendWith(MockitoExtension.class)
class ExcluirClienteImplUnitTest {

	@InjectMocks
	private ExcluirClienteUsecase excluirClienteUsecase = new ExcluirClienteUseCaseImpl(null, null);
	
	@Mock
	private ObterClienteUsecase obterClienteUsecase;
	
	@Mock
	private ClienteGateway clienteRepositoryGateway;
	
	@Test
	void shouldSucessOnExcluir() {
		final Long clienteId = getRandomLong();
		
		ClienteDto clienteDtoExistent = ClienteDto.builder().id(clienteId).build();
		doReturn(clienteDtoExistent).when(obterClienteUsecase).obterPorId(clienteId);
		
		excluirClienteUsecase.excluir(clienteId);
		
		verify(obterClienteUsecase).obterPorId(clienteId);
		verify(clienteRepositoryGateway).excluirPorId(clienteId);
	}
	
}
