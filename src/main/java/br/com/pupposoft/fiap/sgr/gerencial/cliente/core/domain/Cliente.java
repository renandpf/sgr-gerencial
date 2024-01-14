package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.domain;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteValidacaoException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cliente {
	private Long id;
    private String nome;
    private String cpf;
    private String email;
	
    public void validar() {
    	if (cpf == null || cpf.isBlank()) {
    		throw new ClienteValidacaoException("CPF é obrigatório");
        }
	}
}
