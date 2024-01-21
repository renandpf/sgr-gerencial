package br.com.pupposoft.fiap.sgr.config.database.gerencial.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ClienteEntity;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class ClienteEntityRepositoryIntTest {

	@Autowired
	private ClienteEntityRepository clienteEntityRepository;

    @Autowired
    private TestEntityManager entityManager;
	
    @Test
    void shouldSucessOnFindByCpf() {
    	ClienteEntity clienteEntityA = ClienteEntity.builder()
    			.cpf("000")
    			.build();
    	ClienteEntity clienteEntityB = ClienteEntity.builder()
    			.cpf("111")
    			.build();
    	
    	entityManager.persist(clienteEntityA);
    	entityManager.persist(clienteEntityB);
    	
    	Optional<ClienteEntity> clienteEntityAFoundOP = clienteEntityRepository.findByCpf("000");
    	assertTrue(clienteEntityAFoundOP.isPresent());
    	assertEquals(clienteEntityA.getId(), clienteEntityAFoundOP.get().getId());
    	
    	Optional<ClienteEntity> clienteEntityBFoundOP = clienteEntityRepository.findByCpf("111");
    	assertTrue(clienteEntityBFoundOP.isPresent());
    	assertEquals(clienteEntityB.getId(), clienteEntityBFoundOP.get().getId());
    	
    	Optional<ClienteEntity> clienteEntityCFoundOP = clienteEntityRepository.findByCpf("222");
    	assertTrue(clienteEntityCFoundOP.isEmpty());
    }
    
    @Test
    void shouldSucessOnFindByEmail() {
    	ClienteEntity clienteEntityA = ClienteEntity.builder()
    			.email("aaa")
    			.build();
    	ClienteEntity clienteEntityB = ClienteEntity.builder()
    			.email("bbb")
    			.build();
    	
    	entityManager.persist(clienteEntityA);
    	entityManager.persist(clienteEntityB);
    	
    	Optional<ClienteEntity> clienteEntityAFoundOP = clienteEntityRepository.findByEmail("aaa");
    	assertTrue(clienteEntityAFoundOP.isPresent());
    	assertEquals(clienteEntityA.getId(), clienteEntityAFoundOP.get().getId());
    	
    	Optional<ClienteEntity> clienteEntityBFoundOP = clienteEntityRepository.findByEmail("bbb");
    	assertTrue(clienteEntityBFoundOP.isPresent());
    	assertEquals(clienteEntityB.getId(), clienteEntityBFoundOP.get().getId());
    	
    	Optional<ClienteEntity> clienteEntityCFoundOP = clienteEntityRepository.findByEmail("ccc");
    	assertTrue(clienteEntityCFoundOP.isEmpty());
    }
}
