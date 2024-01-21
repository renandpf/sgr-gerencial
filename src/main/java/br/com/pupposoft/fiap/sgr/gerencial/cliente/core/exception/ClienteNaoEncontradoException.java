package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception;

import br.com.pupposoft.fiap.starter.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class ClienteNaoEncontradoException extends SystemBaseException {
	private static final long serialVersionUID = 9019941471361126385L;
	
	private final String code = "sgr.clienteNaoEncontrado";//NOSONAR
	private final String message = "Cliente não foi encontrado";//NOSONAR
	private final Integer httpStatus = 404;//NOSONAR
}
