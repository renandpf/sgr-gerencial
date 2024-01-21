package br.com.pupposoft.fiap.sgr.gerencial.component;

import static br.com.pupposoft.fiap.test.databuilder.DataBuilderBase.getRandomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.com.pupposoft.fiap.SgrGerencialService;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ClienteEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ClienteEntityRepository;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.web.ClienteApiController;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.adapter.web.json.ClienteJson;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SgrGerencialService.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class ClienteComponentTest {

	@Autowired
	private ClienteApiController clienteApiController;

	@Autowired
	private ClienteEntityRepository clienteEntityRepository;
	
	@Test
	void shouldSucessFoundOnObterPorCpf() {
		
		final String cpfParam = getRandomString();
		
		ClienteEntity clienteEntityExistent = ClienteEntity.builder()
				.nome(getRandomString())
				.cpf(cpfParam)
				.email(getRandomString())
				.build();
		
		ClienteEntity clienteEntityExistent2 = ClienteEntity.builder()
				.nome(getRandomString())
				.cpf(getRandomString())
				.email(getRandomString())
				.build();
		
		clienteEntityRepository.save(clienteEntityExistent);
		clienteEntityRepository.save(clienteEntityExistent2);
		
		ClienteJson clienteJson = clienteApiController.obterPorCpf(cpfParam);
		
		assertNotNull(clienteEntityExistent.getId());
		assertEquals(clienteEntityExistent.getNome(), clienteJson.getNome());
		assertEquals(clienteEntityExistent.getCpf(), clienteJson.getCpf());
		assertEquals(clienteEntityExistent.getEmail(), clienteJson.getEmail());
	}
}
