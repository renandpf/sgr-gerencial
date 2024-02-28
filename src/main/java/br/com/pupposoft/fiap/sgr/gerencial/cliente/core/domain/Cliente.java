package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.domain;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.StatusCadastro;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteValidacaoException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Cliente {
	private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String telefone;
    private StatusCadastro statusCadastro;
	
    public void validar() {
    	if (cpf == null || cpf.isBlank()) {
    		throw new ClienteValidacaoException("CPF é obrigatório");
        }
	}
}
