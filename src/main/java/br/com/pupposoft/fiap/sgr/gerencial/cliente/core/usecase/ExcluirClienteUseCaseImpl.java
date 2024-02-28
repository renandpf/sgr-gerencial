package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcluirClienteUseCaseImpl implements ExcluirClienteUsecase {

	private ObterClienteUsecase obterClienteUsecase;

	private ClienteGateway clienteGateway;
	
	@Override
	public void excluir(Long userId) {
		obterClienteUsecase.obterPorId(userId);//Verifica se o cliente existe
		clienteGateway.excluirPorId(userId);
	}

}
