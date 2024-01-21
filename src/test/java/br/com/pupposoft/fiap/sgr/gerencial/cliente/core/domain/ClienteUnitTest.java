package br.com.pupposoft.fiap.sgr.gerencial.cliente.core.domain;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteValidacaoException;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

class ClienteUnitTest {
	
	@Test
	void shouldSucessOnvalidar() {
		Cliente cliente = Cliente.builder().cpf(DataBuilderBase.getRandomString()).build();
		assertDoesNotThrow(() -> cliente.validar());
	}
	
	@Test
	void shouldClienteValidacaoExceptionOnvalidar() {
		Cliente cliente = Cliente.builder().build();
		assertThrows(ClienteValidacaoException.class, () -> cliente.validar());
	}
	
	@Test
	void shouldClienteValidacaoExceptionOnvalidarEmptyString() {
		Cliente cliente = Cliente.builder().cpf("").build();
		assertThrows(ClienteValidacaoException.class, () -> cliente.validar());
	}

	@Test
	void shouldClienteGetterOnClient() {
		var id = DataBuilderBase.getRandomLong();
		var nome = DataBuilderBase.getRandomString();
		var cpf = DataBuilderBase.getRandomString();
		var email = DataBuilderBase.getRandomString();
		
		Cliente cliente = Cliente.builder()
				.id(id)
				.nome(nome)
				.cpf(cpf)
				.email(email)
				.build();
		
		assertEquals(id, cliente.getId());
		assertEquals(nome, cliente.getNome());
		assertEquals(cpf, cliente.getCpf());
		assertEquals(email, cliente.getEmail());
		
		cliente.toString();
	}

	
}
