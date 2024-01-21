package br.com.pupposoft.fiap.sgr.gerencial.produto.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.pupposoft.fiap.starter.exception.SystemBaseException;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

class ExceptionsUnitTest {

	@Test
	void ErrorToAccessRepositoryException() {
		final SystemBaseException exception = new ErrorToAccessRepositoryException();
		
		assertEquals("sgr.errorToAccessRepository", exception.getCode()); 
		assertEquals("Erro ao acessar repositório de dados", exception.getMessage()); 
		assertEquals(500, exception.getHttpStatus()); 
	}
	
	@Test
	void produtoNaoEncontradoException() {
		final SystemBaseException exception = new ProdutoNaoEncontradoException();
		
		assertEquals("sgr.produtoNotFound", exception.getCode()); 
		assertEquals("Produto não foi encontrado", exception.getMessage()); 
		assertEquals(404, exception.getHttpStatus()); 
	}
	
	@Test
	void produtoValidacaoException() {
		final String anyExceptionMessage = DataBuilderBase.getRandomString();
		final SystemBaseException exception = new ProdutoValidacaoException(anyExceptionMessage);
		
		assertEquals("sgr.produtoValidacao", exception.getCode()); 
		assertEquals(anyExceptionMessage, exception.getMessage()); 
		assertEquals(400, exception.getHttpStatus()); 
	}
}
