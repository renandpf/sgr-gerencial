package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain.Categoria;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.exception.ProdutoNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ObterProdutoUseCaseImplUnitTest {

	@InjectMocks
	private ObterProdutoUseCase obterProdutoUseCase = new ObterProdutoUseCaseImpl(null);
	
	@Mock
	private ProdutoGateway produtoGateway;
	
	@Test
	void shouldSucessOnObterPorId() {
		final Long produtoToGetId = DataBuilderBase.getRandomLong();
		
		ProdutoDto produtoDtoExistent = ProdutoDto.builder().build();
		doReturn(Optional.of(produtoDtoExistent)).when(produtoGateway).obterPorId(produtoToGetId);
		
		ProdutoDto produtoFound = obterProdutoUseCase.obterPorId(produtoToGetId);
		verify(produtoGateway).obterPorId(produtoToGetId);
		
		assertEquals(produtoDtoExistent, produtoFound);
	}
	
	@Test
	void shouldProdutoNaoEncontradoExceptionOnObterPorId() {
		final Long produtoToGetId = DataBuilderBase.getRandomLong();
		
		doReturn(Optional.empty()).when(produtoGateway).obterPorId(produtoToGetId);
		
		assertThrows(ProdutoNaoEncontradoException.class, () -> obterProdutoUseCase.obterPorId(produtoToGetId));
		verify(produtoGateway).obterPorId(produtoToGetId);
	}
	
	@Test
	void shouldSucessOnObterPorCategoria() {
		final Categoria categoriaParam = Categoria.ACOMPANHAMENTO;
		
		ProdutoDto produtoDtoExistent = ProdutoDto.builder().build();
		doReturn(Arrays.asList((produtoDtoExistent))).when(produtoGateway).obterPorCategoria(categoriaParam);
		
		List<ProdutoDto> produtosFound = obterProdutoUseCase.obterPorCategoria(categoriaParam);
		verify(produtoGateway).obterPorCategoria(categoriaParam);
		
		assertEquals(1, produtosFound.size());
		assertEquals(produtoDtoExistent, produtosFound.get(0));
	}
}
