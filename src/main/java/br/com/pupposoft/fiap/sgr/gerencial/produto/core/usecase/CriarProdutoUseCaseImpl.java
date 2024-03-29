package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.CriarProdutoReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.exception.ProdutoValidacaoException;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CriarProdutoUseCaseImpl implements CriarProdutoUseCase {

	private ProdutoGateway produtoGateway;
	
	@Override
	public CriarProdutoReturnDto criar(CriarProdutoParamsDto dto) {
        this.validar(dto.getProduto());
        Long id = this.produtoGateway.criar(dto.getProduto());
        return CriarProdutoReturnDto.builder().id(id).build();
	}

    private void validar(ProdutoDto produtoDto) {
        if(produtoDto.getNome() == null){
            log.warn("Nome é obrigatório");
            throw new ProdutoValidacaoException("Nome é obrigatório");
        }else if(produtoDto.getValor() == null){
            log.warn("Valor é obrigatório");
            throw new ProdutoValidacaoException("Valor é obrigatório");
        }
        else if(produtoDto.getCategoriaId() == null){
            log.warn("Categoria é obrigatória");
            throw new ProdutoValidacaoException("Categoria é obrigatória");
        }
    }
}
