package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.AlterarProdutoParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.flow.AlterarProdutoReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlterarProdutoUseCaseImpl implements AlterarProdutoUseCase {

	private ProdutoGateway produtoGateway;
	
	@Override
	public AlterarProdutoReturnDto alterar(AlterarProdutoParamsDto dto) {
        this.produtoGateway.alterar(dto.getProduto());
        return new AlterarProdutoReturnDto();
	}

}
