package br.com.pupposoft.fiap.sgr.config.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.controller.ClienteController;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.AlterarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.AlterarClienteUsecaseImpl;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.CriarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.CriarClienteUsecaseImpl;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ExcluirClienteUseCaseImpl;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ExcluirClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ObterClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ObterClienteUsecaseImpl;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class GerencialClienteDIConfiguration {

	private ClienteGateway clienteGateway;
	
	@Bean
	public ObterClienteUsecase obterClienteUsecase() {
		return new ObterClienteUsecaseImpl(clienteGateway);
	}
	
	@Bean
	public CriarClienteUsecase criarClienteUsecase() {
		return new CriarClienteUsecaseImpl(clienteGateway);
	}

	@Bean
	public AlterarClienteUsecase alterarClienteUsecase() {
		return new AlterarClienteUsecaseImpl(clienteGateway);
	}
	
	@Bean
	@DependsOn({"obterClienteUsecase"})
	@Autowired
	public ExcluirClienteUsecase excluirClienteUsecase(ObterClienteUsecase obterClienteUsecase) {
		return new ExcluirClienteUseCaseImpl(obterClienteUsecase, clienteGateway);
	}
	
	@Bean
	@Autowired
	@DependsOn({"obterClienteUsecase", "criarClienteUsecase", "alterarClienteUsecase", "excluirClienteUsecase"})
	public ClienteController clienteController(ObterClienteUsecase obterClienteUsecase, CriarClienteUsecase criarClienteUsecase, AlterarClienteUsecase alterarClienteUsecase, ExcluirClienteUsecase excluirClienteUsecase) {
		return new ClienteController(obterClienteUsecase, criarClienteUsecase, alterarClienteUsecase, excluirClienteUsecase);
	}
}
