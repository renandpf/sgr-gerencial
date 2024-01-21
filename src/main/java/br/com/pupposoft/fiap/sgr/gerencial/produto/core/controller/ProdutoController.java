package br.com.pupposoft.fiap.sgr.gerencial.produto.core.controller;

import java.util.List;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain.Categoria;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.AlterarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.AlterarProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.CriarProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.ExcluirProdutoUseCase;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase.ObterProdutoUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProdutoController {

	private ObterProdutoUseCase obterProdutoUseCase;
	
	private CriarProdutoUseCase criarProdutoUseCase;
	
	private AlterarProdutoUseCase alterarProdutoUseCase; 
	
	private ExcluirProdutoUseCase excluirProdutoUseCase; 

	public List<ProdutoDto> obterPorCategoria(Categoria categoria) {
		return obterProdutoUseCase.obterPorCategoria(categoria);
	}

	public ProdutoDto obterById(Long id) {
		return obterProdutoUseCase.obterPorId(id);
	}

	public Long criar(ProdutoDto produtoDto) {
		CriarProdutoReturnDto returnDto = 
				criarProdutoUseCase.criar(CriarProdutoParamsDto.builder().produto(produtoDto).build());
		return returnDto.getId();
	}

	public void alterar(ProdutoDto produtoDto){
		alterarProdutoUseCase.alterar(AlterarProdutoParamsDto.builder().produto(produtoDto).build());
	}

	public void excluir(Long id) {
		excluirProdutoUseCase.excluir(id);
	}
}
