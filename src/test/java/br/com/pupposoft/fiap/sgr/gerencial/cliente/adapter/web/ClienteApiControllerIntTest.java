package br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.web;


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
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@WebMvcTest(ClienteApiController.class)
class ClienteApiControllerIntTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteController clienteController;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void shouldSucessOnObterPorCpf() throws Exception {
		final String param = "000";
		
		ClienteDto clienteDtoExistent = ClienteDto.builder()
				.id(1L)
				.nome("cliente nome")
				.cpf(param)
				.email("cliente@mail.com")
				.build();
		doReturn(clienteDtoExistent).when(clienteController).obterPorCpf(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/cpf/" + param))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{\"id\":1,\"nome\":\"cliente nome\",\"cpf\":\"000\",\"email\":\"cliente@mail.com\"}"));
		
		verify(clienteController).obterPorCpf(param);
	}
	
	@Test
	void shouldBussinesErrorOnObterPorCpf() throws Exception {
		final String param = "000";
		
		doThrow(new ClienteNaoEncontradoException()).when(clienteController).obterPorCpf(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/cpf/" + param))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(content().json("{\"code\":\"sgr.clienteNaoEncontrado\",\"message\":\"Cliente não foi encontrado\"}"));
		
		verify(clienteController).obterPorCpf(param);
	}
	
	@Test
	void shouldUnexpectedErrorOnObterPorCpf() throws Exception {
		final String param = "000";
		
		doThrow(new RuntimeException("any message error")).when(clienteController).obterPorCpf(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/cpf/" + param))
			.andDo(print())
			.andExpect(status().isInternalServerError())
			.andExpect(content().json("{\"code\":\"INTERNAL_SERVER_ERROR\",\"message\":\"Internal Server Error\"}"));
		
		verify(clienteController).obterPorCpf(param);
	}
	
	@Test
	void shouldSucessOnObterPorEmail() throws Exception {
		final String param = "cliente@mail.com";
		
		ClienteDto clienteDtoExistent = ClienteDto.builder()
				.id(1L)
				.nome("cliente nome")
				.cpf("000")
				.email(param)
				.build();
		doReturn(clienteDtoExistent).when(clienteController).obterPorEmail(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/email/" + param))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{\"id\":1,\"nome\":\"cliente nome\",\"cpf\":\"000\",\"email\":\"cliente@mail.com\"}"));
	}
	
	@Test
	void shouldBussinesErrorOnObterPorEmail() throws Exception {
		final String param = "000";
		
		doThrow(new ClienteNaoEncontradoException()).when(clienteController).obterPorEmail(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/email/" + param))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(content().json("{\"code\":\"sgr.clienteNaoEncontrado\",\"message\":\"Cliente não foi encontrado\"}"));
		
		verify(clienteController).obterPorEmail(param);
	}

	@Test
	void shouldUnexpectedErrorOnObterPorEmail() throws Exception {
		final String param = "000";
		
		doThrow(new RuntimeException("any message error")).when(clienteController).obterPorEmail(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/email/" + param))
			.andDo(print())
			.andExpect(status().isInternalServerError())
			.andExpect(content().json("{\"code\":\"INTERNAL_SERVER_ERROR\",\"message\":\"Internal Server Error\"}"));
		
		verify(clienteController).obterPorEmail(param);
	}

	@Test
	void shouldSucessOnCriarCliente() throws Exception {
		final ClienteJson requestBody = ClienteJson.builder()
				.id(1L)
				.nome("cliente nome")
				.cpf("000")
				.email("cliente@mail.com")
				.build();
		
		final Long newClienteId = DataBuilderBase.getRandomLong();
		doReturn(newClienteId).when(clienteController).criar(any(ClienteDto.class));
		
		this.mockMvc.perform(
				post("/sgr/gerencial/clientes")
					.content(objectMapper.writeValueAsString(requestBody))
					.contentType(MediaType.APPLICATION_JSON))
					
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().string(newClienteId+""));
		
		ArgumentCaptor<ClienteDto> clienteDtoAC = ArgumentCaptor.forClass(ClienteDto.class);
		
		verify(clienteController).criar(clienteDtoAC.capture());
		
		ClienteDto sentToCreate = clienteDtoAC.getValue();
		
		assertNull(sentToCreate.getId());
		assertEquals(requestBody.getNome(), sentToCreate.getNome());
		assertEquals(requestBody.getCpf(), sentToCreate.getCpf());
		assertEquals(requestBody.getEmail(), sentToCreate.getEmail());
	}
	
	@Test
	void shouldSucessOnAlterarCliente() throws Exception {
		final Long updateClienteId = DataBuilderBase.getRandomLong();
		final ClienteJson requestBody = ClienteJson.builder()
				.id(15L)//Outro id
				.nome("cliente nome")
				.cpf("000")
				.email("cliente@mail.com")
				.build();
		
		this.mockMvc.perform(
				put("/sgr/gerencial/clientes/" + updateClienteId)
					.content(objectMapper.writeValueAsString(requestBody))
					.contentType(MediaType.APPLICATION_JSON))
					
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(""));
		
		ArgumentCaptor<ClienteDto> clienteDtoAC = ArgumentCaptor.forClass(ClienteDto.class);
		
		verify(clienteController).alterar(clienteDtoAC.capture());
		
		ClienteDto sentToUpdate = clienteDtoAC.getValue();
		
		assertEquals(updateClienteId, sentToUpdate.getId());
		assertEquals(requestBody.getNome(), sentToUpdate.getNome());
		assertEquals(requestBody.getCpf(), sentToUpdate.getCpf());
		assertEquals(requestBody.getEmail(), sentToUpdate.getEmail());
	}
	
	@Test
	void shouldSucessOnObterPorId() throws Exception {
		final Long param = 1L;
		
		ClienteDto clienteDtoExistent = ClienteDto.builder()
				.id(1L)
				.nome("cliente nome")
				.cpf("000")
				.email("cliente@mail.com")
				.build();
		doReturn(clienteDtoExistent).when(clienteController).obterById(param);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/" + param))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{\"id\":1,\"nome\":\"cliente nome\",\"cpf\":\"000\",\"email\":\"cliente@mail.com\"}"));
		
		verify(clienteController).obterById(param);
	}
}
