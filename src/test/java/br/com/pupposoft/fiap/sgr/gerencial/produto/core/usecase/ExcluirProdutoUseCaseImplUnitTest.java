package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ExcluirProdutoUseCaseImplUnitTest {

	@InjectMocks
	private ExcluirProdutoUseCase excluirProdutoUseCase = new ExcluirProdutoUseCaseImpl(null);
	
	@Mock
	private ProdutoGateway produtoGateway;
	
	@Test
	void shouldSucessOnExcluir() {
		final Long produtoToDeleteId = DataBuilderBase.getRandomLong();
		excluirProdutoUseCase.excluir(produtoToDeleteId);
		verify(produtoGateway).excluir(produtoToDeleteId);
	}
}
