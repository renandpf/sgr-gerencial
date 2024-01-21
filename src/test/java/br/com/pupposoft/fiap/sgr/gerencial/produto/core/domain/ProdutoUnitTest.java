package br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

class ProdutoUnitTest {
	
	@Test
	void shouldClienteGetterOnClient() {
		var id = DataBuilderBase.getRandomLong();
		var nome = DataBuilderBase.getRandomString();
		var descricao = DataBuilderBase.getRandomString();
		var valor = BigDecimal.valueOf(DataBuilderBase.getRandomDouble());
		var categoria = Categoria.ACOMPANHAMENTO;
		var imagem = DataBuilderBase.getRandomString().getBytes();
		
		Produto produto = Produto.builder()
				.id(id)
				.nome(nome)
				.descricao(descricao)
				.valor(valor)
				.categoria(categoria)
				.imagem(imagem)
				.build();
		
		assertEquals(id, produto.getId());
		assertEquals(nome, produto.getNome());
		assertEquals(descricao, produto.getDescricao());
		assertEquals(valor, produto.getValor());
		assertEquals(categoria, produto.getCategoria());
		assertEquals(imagem, produto.getImagem());
		
		produto.toString();
	}

	
}
