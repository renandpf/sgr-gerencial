package br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.web;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.controller.ClienteController;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;

@WebMvcTest(ClienteApiController.class)
class ClienteApiControllerIntTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteController clienteController;
	
	@Test
	void shouldSucessOnObterPorCpf() throws Exception {
		final String cpfParam = "000";
		
		ClienteDto clienteDtoExistent = ClienteDto.builder()
				.id(1L)
				.nome("cliente nome")
				.cpf(cpfParam)
				.email("cliente@mail.com")
				.build();
		doReturn(clienteDtoExistent).when(clienteController).obterPorCpf(cpfParam);
		
		this.mockMvc.perform(get("/sgr/gerencial/clientes/cpf/" + cpfParam))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("{\"id\":1,\"nome\":\"cliente nome\",\"cpf\":\"000\",\"email\":\"cliente@mail.com\"}"));
	}
}
