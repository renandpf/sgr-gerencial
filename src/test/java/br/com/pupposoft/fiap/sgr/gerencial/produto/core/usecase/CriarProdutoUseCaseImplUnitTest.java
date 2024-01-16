package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class CriarProdutoUseCaseImplUnitTest {

	@InjectMocks
	private CriarProdutoUseCase criarProdutoUseCase = new CriarProdutoUseCaseImpl(null);
	
	@Mock
	private ProdutoGateway produtoGateway;
	
	@Test
	void shouldSucessOnCriar() {
		ProdutoDto produtoDto = ProdutoDto.builder()
				.nome(DataBuilderBase.getRandomString())
				.valor(BigDecimal.valueOf(DataBuilderBase.getRandomDouble()))
				.categoriaId(DataBuilderBase.getRandomLong())
				.build();
		
		final Long produtoCreatedId = DataBuilderBase.getRandomLong();
		doReturn(produtoCreatedId).when(produtoGateway).criar(produtoDto);
		
		final CriarProdutoParamsDto paramsDto = CriarProdutoParamsDto.builder()
				.produto(produtoDto)
				.build();
		
		CriarProdutoReturnDto criarProdutoReturnDto = 
				criarProdutoUseCase.criar(paramsDto);
		
		assertEquals(produtoCreatedId, criarProdutoReturnDto.getId());
		
		verify(produtoGateway).criar(produtoDto);
	}
	
	//TODO: implementar demais métodos
	
}
