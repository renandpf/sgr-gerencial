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
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ObterClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.controller.ProdutoController;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.AlterarProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.CriarProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.ExcluirProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.ObterProdutoUseCase;

@ExtendWith(MockitoExtension.class)
class GerencialProdutoDIConfigurationUnitTest {

	@InjectMocks
	private GerencialProdutoDIConfiguration gerencialProdutoDIConfiguration;
	
	@Mock
	private ProdutoGateway produtoGateway;
	
	@Test
	void shouldSucessOnObterProdutoUseCase() {
		ObterProdutoUseCase usecase = gerencialProdutoDIConfiguration.obterProdutoUseCase();
		assertEquals(produtoGateway, getField(usecase, "produtoGateway"));
	}
	
	@Test
	void shouldSucessoOnCriarProdutoUseCase() {
		CriarProdutoUseCase usecase = gerencialProdutoDIConfiguration.criarProdutoUseCase();
		assertEquals(produtoGateway, getField(usecase, "produtoGateway"));
	}
	
	@Test
	void shouldSucessOnAlterarProdutoUseCase() {
		AlterarProdutoUseCase usecase = gerencialProdutoDIConfiguration.alterarProdutoUseCase();
		assertEquals(produtoGateway, getField(usecase, "produtoGateway"));
	}
	
	@Test
	void shouldSucessOnExcluirProdutoUseCase() {
		ExcluirProdutoUseCase usecase = gerencialProdutoDIConfiguration.excluirProdutoUseCase();
		assertEquals(produtoGateway, getField(usecase, "produtoGateway"));
	}
	
	@Test
	void shouldSucessOnProdutoController() {
		ObterProdutoUseCase obterProdutoUseCase = Mockito.mock(ObterProdutoUseCase.class); 
		CriarProdutoUseCase criarProdutoUseCase = Mockito.mock(CriarProdutoUseCase.class);
		AlterarProdutoUseCase alterarProdutoUseCase = Mockito.mock(AlterarProdutoUseCase.class);
		ExcluirProdutoUseCase excluirProdutoUseCase =  Mockito.mock(ExcluirProdutoUseCase.class);
		
		ProdutoController produtoController = gerencialProdutoDIConfiguration.produtoController(
				obterProdutoUseCase,
				criarProdutoUseCase,
				alterarProdutoUseCase,
				excluirProdutoUseCase);
		assertEquals(obterProdutoUseCase, getField(produtoController, "obterProdutoUseCase"));
		assertEquals(criarProdutoUseCase, getField(produtoController, "criarProdutoUseCase"));
		assertEquals(alterarProdutoUseCase, getField(produtoController, "alterarProdutoUseCase"));
		assertEquals(excluirProdutoUseCase, getField(produtoController, "excluirProdutoUseCase"));
	}
	

}
