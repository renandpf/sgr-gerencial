package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.AlterarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;

@ExtendWith(MockitoExtension.class)
class AlterarProdutoUseCaseImplUnitTest {

	@InjectMocks
	private AlterarProdutoUseCase alterarClienteUsecase = new AlterarProdutoUseCaseImpl(null);
	
	@Mock
	private ProdutoGateway produtoGateway;
	
	@Test
	void shouldSucessOnAlterar() {
		
		ProdutoDto produtoDto = ProdutoDto.builder().build();
		
		final AlterarProdutoParamsDto paramsDto = AlterarProdutoParamsDto.builder()
				.produto(produtoDto)
				.build();
		
		alterarClienteUsecase.alterar(paramsDto);
		
		verify(produtoGateway).alterar(produtoDto);
	}
	
}
