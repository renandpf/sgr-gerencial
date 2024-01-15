package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.pupposoft.fiap.starter.exception.SystemBaseException;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

class ExceptionsUnitTest {

	@Test
	void alteracaoStatusPagoPedidoException() {
		final String anyExceptionMessage = DataBuilderBase.getRandomString();
		final SystemBaseException exception = new AlteracaoStatusPagoPedidoException(anyExceptionMessage);
		
		assertEquals("sgr.alteracaoStatusPagoPedido", exception.getCode()); 
		assertEquals(anyExceptionMessage, exception.getMessage()); 
		assertEquals(422, exception.getHttpStatus()); 
	}
	
}
