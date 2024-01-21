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
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class AlterarClienteUsecaseImplUnitTest {

	@InjectMocks
	private AlterarClienteUsecase alterarClienteUsecase = new AlterarClienteUsecaseImpl(null);
	
	@Mock
	private ClienteGateway clienteRepositoryGateway;
	
	@Test
	void shouldSucessOnAlterar() {
		
		ClienteDto clientDto = ClienteDto.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		
		final AlterarClienteParamsDto paramsDto = AlterarClienteParamsDto.builder()
				.cliente(clientDto)
				.build();
		
		ClienteDto clientDtoExistent = ClienteDto.builder().build();
		doReturn(Optional.of(clientDtoExistent))
			.when(clienteRepositoryGateway).obterPorId(clientDto.getId());
		
		AlterarClienteReturnDto alterarClienteReturnDto = new AlterarClienteReturnDto();
		doReturn(alterarClienteReturnDto)
			.when(clienteRepositoryGateway).alterar(paramsDto);
		
		AlterarClienteReturnDto returnDto = alterarClienteUsecase.alterar(paramsDto);
		
		assertEquals(alterarClienteReturnDto, returnDto);
		
		verify(clienteRepositoryGateway).obterPorId(clientDto.getId());
		verify(clienteRepositoryGateway).alterar(paramsDto);
	}
	
	@Test
	void shouldClienteNaoEncontradoExceptionOnAlterar() {
		
		ClienteDto clientDto = ClienteDto.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.cpf(DataBuilderBase.getRandomString())
				.email(DataBuilderBase.getRandomString())
				.build();
		
		final AlterarClienteParamsDto paramsDto = AlterarClienteParamsDto.builder()
				.cliente(clientDto)
				.build();
		
		doReturn(Optional.empty())
			.when(clienteRepositoryGateway).obterPorId(clientDto.getId());
		
		
		assertThrows(ClienteNaoEncontradoException.class, () -> alterarClienteUsecase.alterar(paramsDto));
		
		verify(clienteRepositoryGateway).obterPorId(clientDto.getId());
		verify(clienteRepositoryGateway, never()).alterar(paramsDto);
	}
	
}
