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
	
	@Test
	void clienteExistenteException() {
		final SystemBaseException exception = new ClienteExistenteException();
		
		assertEquals("sgr.clienteExistente", exception.getCode()); 
		assertEquals("Cliente já cadastrado", exception.getMessage()); 
		assertEquals(400, exception.getHttpStatus()); 
	}
	
	@Test
	void clienteNaoEncontradoException() {
		final SystemBaseException exception = new ClienteNaoEncontradoException();
		
		assertEquals("sgr.clienteNaoEncontrado", exception.getCode()); 
		assertEquals("Cliente não foi encontrado", exception.getMessage()); 
		assertEquals(404, exception.getHttpStatus()); 
	}
	
	@Test
	void clienteValidacaoException() {
		final String anyExceptionMessage = DataBuilderBase.getRandomString();
		final SystemBaseException exception = new ClienteValidacaoException(anyExceptionMessage);
		
		assertEquals("sgr.clienteValidacao", exception.getCode()); 
		assertEquals(anyExceptionMessage, exception.getMessage()); 
		assertEquals(400, exception.getHttpStatus()); 
	}
	
	@Test
	void errorToAccessRepositoryException() {
		final SystemBaseException exception = new ErrorToAccessRepositoryException();
		
		assertEquals("sgr.errorToAccessRepository", exception.getCode()); 
		assertEquals("Erro ao acessar repositório de dados", exception.getMessage()); 
		assertEquals(400, exception.getHttpStatus()); 
	}
	
	@Test
	void exclusaoProdutoAssociadoPedidoException() {
		final SystemBaseException exception = new ExclusaoProdutoAssociadoPedidoException();
		
		assertEquals("sgr.exclusionProductAssociatedWithOrder", exception.getCode()); 
		assertEquals("O produto está associado a pedido(s)", exception.getMessage()); 
		assertEquals(422, exception.getHttpStatus()); 
	}
	
}
