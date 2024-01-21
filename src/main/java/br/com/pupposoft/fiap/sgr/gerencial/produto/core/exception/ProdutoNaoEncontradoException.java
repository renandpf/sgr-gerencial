package br.com.pupposoft.fiap.sgr.gerencial.produto.core.exception;

import br.com.pupposoft.fiap.starter.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class ProdutoNaoEncontradoException extends SystemBaseException {
	private static final long serialVersionUID = -9028673366453394544L;
	private final String code = "sgr.produtoNotFound";//NOSONAR
	private final String message = "Produto não foi encontrado";//NOSONAR
	private final Integer httpStatus = 404;//NOSONAR
}
