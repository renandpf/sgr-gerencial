package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception;

import br.com.pupposoft.fiap.starter.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class ClienteValidacaoException extends SystemBaseException {
	private static final long serialVersionUID = 1004353093504455169L;
	
	private final String code = "sgr.clienteValidacao";//NOSONAR
	private String message = "";//NOSONAR
	private final Integer httpStatus = 400;//NOSONAR
	
	public ClienteValidacaoException(String message) {
		this.message = message;
	}

}
