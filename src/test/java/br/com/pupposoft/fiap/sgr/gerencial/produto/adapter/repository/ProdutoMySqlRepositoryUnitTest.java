package br.com.pupposoft.fiap.sgr.gerencial.produto.adapter.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ClienteEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ProdutoEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ClienteEntityRepository;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ProdutoEntityRepository;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.ClienteDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.AlterarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteParamsDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.dto.flows.CriarClienteReturnDto;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ErrorToAccessRepositoryException;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.gateway.ClienteGateway;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ProdutoMySqlRepositoryUnitTest {

	@InjectMocks
	private ProdutoGateway produtoGateway = new ProdutoMySqlRepository();
	
	@Mock
	private ProdutoEntityRepository produtoEntityRepository;
	
	@Test
	void shouldSucessOnObterPorId() {
		final Long produtoId = DataBuilderBase.getRandomLong();
		
		ProdutoEntity produtoEntityExistent = ProdutoEntity.builder()
				.id(produtoId)
				.nome(DataBuilderBase.getRandomString())
				.descricao(DataBuilderBase.getRandomString())
				.imagem("TESTE".getBytes())
				.valor(new BigDecimal("50"))
				.categoriaId(DataBuilderBase.getRandomLong())
				.build();
		doReturn(Optional.of(produtoEntityExistent)).when(produtoEntityRepository).findById(produtoId);
		
		Optional<ProdutoDto> produtoDtoExistentOP = produtoGateway.obterPorId(produtoId);

		
		ProdutoDto produtoDto = produtoDtoExistentOP.get();
		
		assertEquals(produtoEntityExistent.getId(), produtoDto.getId());
		assertEquals(produtoEntityExistent.getNome(), produtoDto.getNome());
		assertEquals(produtoEntityExistent.getDescricao(), produtoDto.getDescricao());
		assertEquals(produtoEntityExistent.getImagem(), produtoDto.getImagem());
		assertEquals(produtoEntityExistent.getValor(), produtoDto.getValor());
		assertEquals(produtoEntityExistent.getCategoriaId(), produtoDto.getCategoriaId());
		
		verify(produtoEntityRepository).findById(produtoId);
	}
	
}
