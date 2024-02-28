package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.StatusCadastro;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ClienteDto {
	private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String telefone;
    
    @Setter
    private StatusCadastro statusCadastro;
    
}
