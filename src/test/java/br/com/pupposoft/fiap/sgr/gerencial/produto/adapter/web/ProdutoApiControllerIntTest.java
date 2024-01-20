package br.com.pupposoft.fiap.sgr.gerencial.produto.adapter.web;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.web.json.ClienteJson;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.controller.ClienteController;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ClienteNaoEncontradoException;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.controller.ProdutoController;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain.Categoria;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@WebMvcTest(ProdutoApiController.class)
class ProdutoApiControllerIntTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProdutoController produtoController;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void shouldSucessOnObterPorCategoria() throws Exception {
		final Categoria categoria = Categoria.LANCHE;
		final String param = categoria.name();
		
		ProdutoDto produtoDtoExistent = ProdutoDto.builder()
				.id(1L)
				.nome("cliente nome")
				.descricao(param)
				.valor(new BigDecimal("3.55"))
				.categoriaId(0L)
				.build();
		doReturn(Arrays.asList(produtoDtoExistent)).when(produtoController).obterPorCategoria(categoria);
		
		this.mockMvc.perform(get("/sgr/gerencial/categorias/"+ param +"/produtos"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("[{\"id\":1,\"nome\":\"cliente nome\",\"descricao\":\"LANCHE\",\"valor\":3.55,\"categoria\":\"LANCHE\",\"imagem\":null}]"));
		
		verify(produtoController).obterPorCategoria(categoria);
	}
	
	@Test
	void shouldSucessOnObterPorId() throws Exception {
		final Long param = 15L;
		
		ProdutoDto produtoDtoExistent = ProdutoDto.builder()
				.id(1L)
				.nome("cliente nome")
				.descricao("any descrição")
				.valor(new BigDecimal("3.55"))
				.categoriaId(0L)
				.build();
		doReturn(produtoDtoExistent).when(produtoController).obterById(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/produtos/"+ param))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{\"id\":1,\"nome\":\"cliente nome\",\"descricao\":\"any descrição\",\"valor\":3.55,\"categoria\":\"LANCHE\",\"imagem\":null}"));
		
		verify(produtoController).obterById(param);
	}
	
	//TODO: continuar
	
}
