package br.com.pupposoft.fiap.sgr.gerencial.produto.core.usecase;

import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluirProdutoUseCaseImpl implements ExcluirProdutoUseCase {

	private ProdutoGateway produtoGateway;
	
	@Override
	public void excluir(Long id) {
        this.verificaSeProdutoEstaAssociadoItem(id);
        this.produtoGateway.excluir(id);
	}

    //TODO: DEBITO TÉCNICO este método deve chamar o service de Pedido
	private void verificaSeProdutoEstaAssociadoItem(Long id) {
        // const existePedido = await this.produtoRepositoryGateway.existePedidoByProdutoId(id);
        // if (existePedido) {
        //     this.logger.warn("Product is associated with at least 1 order");
        //     throw new ExclusaoProdutoAssociadoPedidoException();
        // }
    }
	
}
