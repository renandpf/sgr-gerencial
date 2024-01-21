package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteExistenteException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class CriarClienteUsecaseImplUnitTest {

	@InjectMocks
	private CriarClienteUsecase criarClienteUsecase = new CriarClienteUsecaseImpl(null);
	
	@Mock
	private ClienteGateway clienteRepositoryGateway;
	
	@Test
	void shouldSucessOnAlterar() {
		
		ClienteDto clientDto = ClienteDto.builder()
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		
		final CriarClienteParamsDto paramsDto = CriarClienteParamsDto.builder()
				.cliente(clientDto)
				.build();
		
		doReturn(Optional.empty()).when(clienteRepositoryGateway).obterPorCpf(clientDto.getCpf());
		
		CriarClienteReturnDto criarClienteReturnDto = 
				CriarClienteReturnDto.builder().clienteId(DataBuilderBase.getRandomLong()).build();
		doReturn(criarClienteReturnDto)
			.when(clienteRepositoryGateway).criar(paramsDto);
		
		CriarClienteReturnDto returnDto = criarClienteUsecase.criar(paramsDto);
		
		assertEquals(criarClienteReturnDto, returnDto);
		
		verify(clienteRepositoryGateway).obterPorCpf(clientDto.getCpf());
		verify(clienteRepositoryGateway).criar(paramsDto);
	}
	
	@Test
	void shouldClienteExistenteExceptionOnAlterar() {
		
		ClienteDto clientDto = ClienteDto.builder()
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		
		final CriarClienteParamsDto paramsDto = CriarClienteParamsDto.builder()
				.cliente(clientDto)
				.build();
		
		ClienteDto clientDtoExistent = ClienteDto.builder().build();
		doReturn(Optional.of(clientDtoExistent)).when(clienteRepositoryGateway).obterPorCpf(clientDto.getCpf());
		
		assertThrows(ClienteExistenteException.class, () -> criarClienteUsecase.criar(paramsDto));
		
		verify(clienteRepositoryGateway).obterPorCpf(clientDto.getCpf());
		verify(clienteRepositoryGateway, never()).criar(paramsDto);
	}
	
}
