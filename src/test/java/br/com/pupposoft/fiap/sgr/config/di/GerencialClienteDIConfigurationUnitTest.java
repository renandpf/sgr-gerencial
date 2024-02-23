package br.com.pupposoft.fiap.sgr.config.di;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.controller.ClienteController;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.AlterarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.CriarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ExcluirClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ObterClienteUsecase;

@ExtendWith(MockitoExtension.class)
class GerencialClienteDIConfigurationUnitTest {

	@InjectMocks
	private GerencialClienteDIConfiguration gerencialClienteDIConfiguration;
	
	@Mock
	private ClienteGateway clienteGateway;
	
	@Test
	void shouldSucessOnObterClienteUsecase() {
		ObterClienteUsecase usecase = gerencialClienteDIConfiguration.obterClienteUsecase();
		assertEquals(clienteGateway, getField(usecase, "clienteGateway"));
	}
	
	@Test
	void shouldSucessOnCriarClienteUsecase() {
		CriarClienteUsecase usecase = gerencialClienteDIConfiguration.criarClienteUsecase();
		assertEquals(clienteGateway, getField(usecase, "clienteGateway"));
	}
	
	@Test
	void shouldSucessOnAlterarClienteUsecase() {
		AlterarClienteUsecase usecase = gerencialClienteDIConfiguration.alterarClienteUsecase();
		assertEquals(clienteGateway, getField(usecase, "clienteGateway"));
	}
	
	@Test
	void shouldSucessOnExcluirClienteUsecase() {
		ObterClienteUsecase obterClienteUsecase = Mockito.mock(ObterClienteUsecase.class);
		ExcluirClienteUsecase usecase = gerencialClienteDIConfiguration.excluirClienteUsecase(obterClienteUsecase);
		assertEquals(obterClienteUsecase, getField(usecase, "obterClienteUsecase"));
		assertEquals(clienteGateway, getField(usecase, "clienteGateway"));
	}
	
	@Test
	void shouldSucessOnClienteController() {
		ObterClienteUsecase obterClienteUsecase = Mockito.mock(ObterClienteUsecase.class);
		CriarClienteUsecase criarClienteUsecase = Mockito.mock(CriarClienteUsecase.class);
		AlterarClienteUsecase alterarClienteUsecase = Mockito.mock(AlterarClienteUsecase.class);
		
		ClienteController clienteController = 
				gerencialClienteDIConfiguration
					.clienteController(obterClienteUsecase, criarClienteUsecase, alterarClienteUsecase);
		
		assertEquals(obterClienteUsecase, getField(clienteController, "obterClienteUseCase"));
		assertEquals(criarClienteUsecase, getField(clienteController, "criarClienteUseCase"));
		assertEquals(alterarClienteUsecase, getField(clienteController, "alterarClienteUseCase"));
		
		
	}
}
