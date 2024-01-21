package br.com.pupposoft.fiap.sgr.gerencial.produto.core.exception;

import br.com.pupposoft.fiap.starter.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class ProdutoValidacaoException extends SystemBaseException {
	private static final long serialVersionUID = -2758617174295132221L;

	private final String code = "sgr.produtoValidacao";//NOSONAR
	private String message = "";//NOSONAR
	private final Integer httpStatus = 400;//NOSONAR
	
	public ProdutoValidacaoException(String message) {
		this.message = message;
	}
}
