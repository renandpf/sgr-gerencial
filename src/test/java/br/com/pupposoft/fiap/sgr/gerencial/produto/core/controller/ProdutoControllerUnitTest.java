package br.com.pupposoft.fiap.sgr.gerencial.produto.core.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain.Categoria;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.AlterarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.AlterarProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.CriarProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.ExcluirProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.ObterProdutoUseCase;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerUnitTest {

	@InjectMocks
	private ProdutoController produtoController;

	@Mock
	private ObterProdutoUseCase obterProdutoUseCase;
	
	@Mock
	private CriarProdutoUseCase criarProdutoUseCase;
	
	@Mock
	private AlterarProdutoUseCase alterarProdutoUseCase; 
	
	@Mock
	private ExcluirProdutoUseCase excluirProdutoUseCase; 

	@Test
	void shouldSuccessOnObterPorCategoria() {
		Categoria categoriaParam = Categoria.ACOMPANHAMENTO;
		
		ProdutoDto produtoDtoExistent = ProdutoDto.builder().build();
		doReturn(Arrays.asList(produtoDtoExistent)).when(obterProdutoUseCase).obterPorCategoria(categoriaParam);
		List<ProdutoDto> produtosResponse = produtoController.obterPorCategoria(categoriaParam);

		assertEquals(1, produtosResponse.size());
		assertEquals(produtosResponse.get(0), produtoDtoExistent);
		
		verify(obterProdutoUseCase).obterPorCategoria(categoriaParam);
	}
	
	@Test
	void shouldSuccessOnById() {
		final Long produtoId = DataBuilderBase.getRandomLong();
		
		ProdutoDto produtoDtoExistent = ProdutoDto.builder().build();
		doReturn(produtoDtoExistent).when(obterProdutoUseCase).obterPorId(produtoId);
		ProdutoDto produtoResponse = produtoController.obterById(produtoId);
		
		assertEquals(produtoResponse, produtoDtoExistent);
		
		verify(obterProdutoUseCase).obterPorId(produtoId);
	}
	
	@Test
	void shouldSuccessOnCriar() {
		ProdutoDto produtoDtoToCreate = ProdutoDto.builder().build();
		final Long produtoCreatedId = DataBuilderBase.getRandomLong();
		
		CriarProdutoReturnDto criarProdutoReturnDto = CriarProdutoReturnDto.builder().id(produtoCreatedId).build();
		doReturn(criarProdutoReturnDto).when(criarProdutoUseCase).criar(any(CriarProdutoParamsDto.class));
		Long produtoIdreturned = produtoController.criar(produtoDtoToCreate);
		
		assertEquals(produtoCreatedId, produtoIdreturned);
		
		ArgumentCaptor<CriarProdutoParamsDto> criarProdutoParamsDtoAC = ArgumentCaptor.forClass(CriarProdutoParamsDto.class);
		
		verify(criarProdutoUseCase).criar(criarProdutoParamsDtoAC.capture());
		
		CriarProdutoParamsDto criarProdutoParamsDto = criarProdutoParamsDtoAC.getValue();
		
		assertEquals(produtoDtoToCreate, criarProdutoParamsDto.getProduto());
	}
	
	@Test
	void shouldSuccessOnAlterar() {
		ProdutoDto produtoDtoToUpdate = ProdutoDto.builder().build();
		
		produtoController.alterar(produtoDtoToUpdate);
		
		ArgumentCaptor<AlterarProdutoParamsDto> alterarProdutoParamsDtoAC = ArgumentCaptor.forClass(AlterarProdutoParamsDto.class);
		
		verify(alterarProdutoUseCase).alterar(alterarProdutoParamsDtoAC.capture());
		
		AlterarProdutoParamsDto alterarProdutoParamsDto = alterarProdutoParamsDtoAC.getValue();
		
		assertEquals(produtoDtoToUpdate, alterarProdutoParamsDto.getProduto());
	}
	
	@Test
	void shouldSuccessOnExcluir() {
		final Long produtoId = DataBuilderBase.getRandomLong();
		produtoController.excluir(produtoId);
		verify(excluirProdutoUseCase).excluir(produtoId);
	}

}
