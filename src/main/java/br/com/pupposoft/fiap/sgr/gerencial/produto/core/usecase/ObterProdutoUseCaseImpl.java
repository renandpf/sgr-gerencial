package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import java.util.List;
import java.util.Optional;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain.Categoria;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.exception.ProdutoNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ObterProdutoUseCaseImpl implements ObterProdutoUseCase {

	private ProdutoGateway produtoGateway;
	
	@Override
	public ProdutoDto obterPorId(Long id) {
        Optional<ProdutoDto> produtoFoundOp = produtoGateway.obterPorId(id);
        if (produtoFoundOp.isEmpty()) {
            log.warn("Produto not found: {}", id);
            throw new ProdutoNaoEncontradoException();
        }

        return produtoFoundOp.get();
	}

	@Override
	public List<ProdutoDto> obterPorCategoria(Categoria categoria) {
        return this.produtoGateway.obterPorCategoria(categoria);
	}
}
