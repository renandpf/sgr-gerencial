package br.com.pupposoft.fiap.sgr.config.database.gerencial.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ProdutoEntity;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class ProdutoEntityRepositoryIntTest {

	@Autowired
	private ProdutoEntityRepository projectRepository;

    @Autowired
    private TestEntityManager entityManager;
	
    @Test
    void shouldSucessOnFindByCpf() {
    	ProdutoEntity entityA1 = ProdutoEntity.builder()
    			.categoriaId(1L)
    			.build();
    	ProdutoEntity entityA2 = ProdutoEntity.builder()
    			.categoriaId(1L)
    			.build();
    	ProdutoEntity entityC = ProdutoEntity.builder()
    			.categoriaId(2L)
    			.build();
    	
    	entityManager.persist(entityA1);
    	entityManager.persist(entityA2);
    	entityManager.persist(entityC);
    	
    	List<ProdutoEntity> entitysAFound = projectRepository.findByCategoriaId(1);
    	assertEquals(2, entitysAFound.size());
    	assertEquals(entityA1.getId(), entitysAFound.get(0).getId());
    	assertEquals(entityA2.getId(), entitysAFound.get(1).getId());
    	
    	List<ProdutoEntity> entitysCFound = projectRepository.findByCategoriaId(2);
    	assertEquals(1, entitysCFound.size());
    	assertEquals(entityC.getId(), entitysCFound.get(0).getId());
    }
}
