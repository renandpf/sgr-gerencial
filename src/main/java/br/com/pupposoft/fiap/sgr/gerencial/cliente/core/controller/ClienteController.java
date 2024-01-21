package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.controller;


import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.AlterarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.CriarClienteUsecase;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.usecase.ObterClienteUsecase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClienteController {
	
	private ObterClienteUsecase obterClienteUseCase;
	
	private CriarClienteUsecase criarClienteUseCase;

	private AlterarClienteUsecase alterarClienteUseCase;

	public ClienteDto obterPorCpf(String cpf) {
		return obterClienteUseCase.obterPorCpf(cpf);
	}

	public ClienteDto obterPorEmail(String email) {
		return this.obterClienteUseCase.obterPorEmail(email);
	}

	public Long criar(ClienteDto clienteDto) {
		CriarClienteReturnDto returnDto = this.criarClienteUseCase.criar(CriarClienteParamsDto.builder().cliente(clienteDto).build());
		return returnDto.getClienteId();
	}

	public void alterar(ClienteDto clienteDto){
		alterarClienteUseCase.alterar(AlterarClienteParamsDto.builder().cliente(clienteDto).build());
	}

	public ClienteDto obterById(Long clienteId) {
		return obterClienteUseCase.obterPorId(clienteId);
	}
}
