package br.com.pupposoft.fiap.sgr.gerencial.produto.adapter.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ProdutoEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ProdutoEntityRepository;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ErrorToAccessRepositoryException;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain.Categoria;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import br.com.pupposoft.fiap.test.databuilder.DataBuilderBase;

@ExtendWith(MockitoExtension.class)
class ProdutoMySqlRepositoryUnitTest {

	@InjectMocks
	private ProdutoGateway produtoGateway = new ProdutoMySqlRepository(null);
	
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
	
	@Test
	void shouldSucessNotFoundOnObterPorId() {
		final Long produtoId = DataBuilderBase.getRandomLong();
		
		doReturn(Optional.empty()).when(produtoEntityRepository).findById(produtoId);
		
		Optional<ProdutoDto> produtoDtoExistentOP = produtoGateway.obterPorId(produtoId);
		
		assertTrue(produtoDtoExistentOP.isEmpty());
		
		verify(produtoEntityRepository).findById(produtoId);
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnObterPorId() {
		final Long produtoId = DataBuilderBase.getRandomLong();
		
		doThrow(new RuntimeException()).when(produtoEntityRepository).findById(produtoId);
		
		assertThrows(ErrorToAccessRepositoryException.class, () -> produtoGateway.obterPorId(produtoId));
		
		
		verify(produtoEntityRepository).findById(produtoId);
	}

	@Test
	void shouldSucessOnObterPorCategoria() {
		final Categoria categoria = Categoria.ACOMPANHAMENTO;
		
		ProdutoEntity produtoEntityExistent = ProdutoEntity.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.descricao(DataBuilderBase.getRandomString())
				.imagem("TESTE".getBytes())
				.valor(new BigDecimal("50"))
				.categoriaId(DataBuilderBase.getRandomLong())
				.build();
		doReturn(Arrays.asList(produtoEntityExistent))
			.when(produtoEntityRepository).findByCategoriaId(categoria.ordinal());
		
		List<ProdutoDto> produtoDtos = produtoGateway.obterPorCategoria(categoria);
		
		assertEquals(1, produtoDtos.size());
		
		ProdutoDto produtoDto = produtoDtos.get(0);

		assertEquals(produtoEntityExistent.getId(), produtoDto.getId());
		assertEquals(produtoEntityExistent.getNome(), produtoDto.getNome());
		assertEquals(produtoEntityExistent.getDescricao(), produtoDto.getDescricao());
		assertEquals(produtoEntityExistent.getImagem(), produtoDto.getImagem());
		assertEquals(produtoEntityExistent.getValor(), produtoDto.getValor());
		assertEquals(produtoEntityExistent.getCategoriaId(), produtoDto.getCategoriaId());
		
		verify(produtoEntityRepository).findByCategoriaId(categoria.ordinal());
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnObterPorCategoria() {
		final Categoria categoria = Categoria.ACOMPANHAMENTO;
		
		doThrow(new RuntimeException()).when(produtoEntityRepository).findByCategoriaId(categoria.ordinal());
		
		assertThrows(ErrorToAccessRepositoryException.class, () -> produtoGateway.obterPorCategoria(categoria));
		
		verify(produtoEntityRepository).findByCategoriaId(categoria.ordinal());
	}
	
	@Test
	void shouldSucessOnCriar() {
		ProdutoDto produtoDtoToSave = ProdutoDto.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.descricao(DataBuilderBase.getRandomString())
				.valor(BigDecimal.valueOf(DataBuilderBase.getRandomDouble()))
				.categoriaId(DataBuilderBase.getRandomLong())
				.imagem(DataBuilderBase.getRandomString().getBytes())
				.build();
		
		ProdutoEntity produtoEntitySaved = ProdutoEntity.builder()
				.id(DataBuilderBase.getRandomLong())
				.build();
		doReturn(produtoEntitySaved).when(produtoEntityRepository).save(any(ProdutoEntity.class));
		
		final Long produdoSavedId = produtoGateway.criar(produtoDtoToSave);
		
		assertEquals(produtoEntitySaved.getId(), produdoSavedId);
		
		ArgumentCaptor<ProdutoEntity> produtoEntitySavedAC = ArgumentCaptor.forClass(ProdutoEntity.class);
		
		verify(produtoEntityRepository).save(produtoEntitySavedAC.capture());
		
		ProdutoEntity produtoEntityCaptured = produtoEntitySavedAC.getValue();

		assertEquals(produtoDtoToSave.getId(), produtoEntityCaptured.getId());
		assertEquals(produtoDtoToSave.getNome(), produtoEntityCaptured.getNome());
		assertEquals(produtoDtoToSave.getDescricao(), produtoEntityCaptured.getDescricao());
		assertEquals(produtoDtoToSave.getImagem(), produtoEntityCaptured.getImagem());
		assertEquals(produtoDtoToSave.getValor(), produtoEntityCaptured.getValor());
		assertEquals(produtoDtoToSave.getCategoriaId(), produtoEntityCaptured.getCategoriaId());
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnCriar() {
		ProdutoDto produtoDtoToSave = ProdutoDto.builder().build();
		
		doThrow(new RuntimeException()).when(produtoEntityRepository).save(any(ProdutoEntity.class));
		
		assertThrows(ErrorToAccessRepositoryException.class, () -> produtoGateway.criar(produtoDtoToSave));
		
		verify(produtoEntityRepository).save(any(ProdutoEntity.class));
	}
	
	@Test
	void shouldSucessOnAlterar() {
		ProdutoDto produtoDtoToUpdate = ProdutoDto.builder()
				.id(DataBuilderBase.getRandomLong())
				.nome(DataBuilderBase.getRandomString())
				.descricao(DataBuilderBase.getRandomString())
				.valor(BigDecimal.valueOf(DataBuilderBase.getRandomDouble()))
				.categoriaId(DataBuilderBase.getRandomLong())
				.imagem(DataBuilderBase.getRandomString().getBytes())
				.build();
		
		produtoGateway.alterar(produtoDtoToUpdate);
		
		ArgumentCaptor<ProdutoEntity> produtoEntitySavedAC = ArgumentCaptor.forClass(ProdutoEntity.class);
		
		verify(produtoEntityRepository).save(produtoEntitySavedAC.capture());
		
		ProdutoEntity produtoEntityCaptured = produtoEntitySavedAC.getValue();

		assertEquals(produtoDtoToUpdate.getId(), produtoEntityCaptured.getId());
		assertEquals(produtoDtoToUpdate.getNome(), produtoEntityCaptured.getNome());
		assertEquals(produtoDtoToUpdate.getDescricao(), produtoEntityCaptured.getDescricao());
		assertEquals(produtoDtoToUpdate.getImagem(), produtoEntityCaptured.getImagem());
		assertEquals(produtoDtoToUpdate.getValor(), produtoEntityCaptured.getValor());
		assertEquals(produtoDtoToUpdate.getCategoriaId(), produtoEntityCaptured.getCategoriaId());
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionOnAlterar() {
		ProdutoDto produtoDtoToSave = ProdutoDto.builder().build();
		
		doThrow(new RuntimeException()).when(produtoEntityRepository).save(any(ProdutoEntity.class));
		
		assertThrows(ErrorToAccessRepositoryException.class, () -> produtoGateway.alterar(produtoDtoToSave));
		
		verify(produtoEntityRepository).save(any(ProdutoEntity.class));
	}
	
	@Test
	void shouldSucessoToAccessRepositoryExceptionOnExcluir() {
		final Long produtoId = DataBuilderBase.getRandomLong();
		produtoGateway.excluir(produtoId);
		verify(produtoEntityRepository).deleteById(produtoId);
	}
	
	@Test
	void shouldErrorToAccessRepositoryExceptionToOnExcluir() {
		final Long produtoId = DataBuilderBase.getRandomLong();
		
		doThrow(new RuntimeException()).when(produtoEntityRepository).deleteById(produtoId);
		assertThrows(ErrorToAccessRepositoryException.class, () -> produtoGateway.excluir(produtoId));
		verify(produtoEntityRepository).deleteById(produtoId);
	}
	
}
