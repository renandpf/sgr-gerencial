package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.AlterarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.CriarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ExcluirClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ObterClienteUsecase;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ClienteControllerUnitTest {

	@InjectMocks
	private ClienteController clienteController;

	@Mock
	private ObterClienteUsecase obterClienteUseCase;
	
	@Mock
	private CriarClienteUsecase criarClienteUseCase;

	@Mock
	private AlterarClienteUsecase alterarClienteUseCase;
	
	@Mock
	private ExcluirClienteUsecase excluirClienteUseCase;

	@Test
	void shouldSuccessOnObterPorCpf() {

		final String requestedCpf = DataBuilderBase.getRandomString(); 
		
		ClienteDto clientDtoExistent = ClienteDto.builder().build();
		
		doReturn(clientDtoExistent).when(obterClienteUseCase).obterPorCpf(requestedCpf);
		ClienteDto clientDtoResponse = clienteController.obterPorCpf(requestedCpf);

		assertEquals(clientDtoExistent, clientDtoResponse);
		
		verify(obterClienteUseCase).obterPorCpf(requestedCpf);
	}
	
	@Test
	void shouldSuccessOnObterPorEmail() {
		
		final String requestedEmail = DataBuilderBase.getRandomString(); 
		
		ClienteDto clientDtoExistent = ClienteDto.builder().build();
		
		doReturn(clientDtoExistent).when(obterClienteUseCase).obterPorEmail(requestedEmail);
		ClienteDto clientDtoResponse = clienteController.obterPorEmail(requestedEmail);
		
		assertEquals(clientDtoExistent, clientDtoResponse);
		
		verify(obterClienteUseCase).obterPorEmail(requestedEmail);
	}
	
	@Test
	void shouldSuccessOnCriar() {
		ClienteDto clientDtoToCreate = ClienteDto.builder().build();
		
		final Long newClientId = DataBuilderBase.getRandomLong();
		doReturn(CriarClienteReturnDto.builder().clienteId(newClientId).build()).when(criarClienteUseCase).criar(any(CriarClienteParamsDto.class));
		Long clientIdReturned = clienteController.criar(clientDtoToCreate);

		assertEquals(newClientId, clientIdReturned);
		
		ArgumentCaptor<CriarClienteParamsDto> criarClienteParamsDtoAC = ArgumentCaptor.forClass(CriarClienteParamsDto.class);
		verify(criarClienteUseCase).criar(criarClienteParamsDtoAC.capture());
		
		CriarClienteParamsDto criarClienteParamsDtoCaptured = criarClienteParamsDtoAC.getValue();

		assertEquals(clientDtoToCreate, criarClienteParamsDtoCaptured.getCliente());
	}
	
	@Test
	void shouldSuccessOnAlterar() {
		ClienteDto clientDtoToAlterar = ClienteDto.builder().build();
		
		clienteController.alterar(clientDtoToAlterar);

		ArgumentCaptor<AlterarClienteParamsDto> clienteParamsDtoAC = ArgumentCaptor.forClass(AlterarClienteParamsDto.class);
		verify(alterarClienteUseCase).alterar(clienteParamsDtoAC.capture());
		
		AlterarClienteParamsDto alterarClienteParamsDtoCaptured = clienteParamsDtoAC.getValue();

		assertEquals(clientDtoToAlterar, alterarClienteParamsDtoCaptured.getCliente());
	}
	
	@Test
	void shouldSuccessOnObterPorId() {
		final Long clienteId = DataBuilderBase.getRandomLong();
		
		ClienteDto clienteDtoExistent = ClienteDto.builder().build();
		doReturn(clienteDtoExistent).when(obterClienteUseCase).obterPorId(clienteId);
		ClienteDto clienteDtoReturned = clienteController.obterById(clienteId);
		
		assertEquals(clienteDtoExistent, clienteDtoReturned);
		
		verify(obterClienteUseCase).obterPorId(clienteId);
	}
	
	@Test
	void shouldSuccessOnExcluir() {
		final Long clienteId = DataBuilderBase.getRandomLong();
		
		clienteController.excluir(clienteId);
		
		verify(excluirClienteUseCase).excluir(clienteId);
	}
}
