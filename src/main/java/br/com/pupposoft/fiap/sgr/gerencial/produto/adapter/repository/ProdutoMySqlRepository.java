package br.com.pupposoft.fiap.sgr.gerencial.produto.adapter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.pupposoft.fiap.sgr.config.database.gerencial.entity.ProdutoEntity;
import br.com.pupposoft.fiap.sgr.config.database.gerencial.repository.ProdutoEntityRepository;
import br.com.pupposoft.fiap.sgr.gerencial.cliente.core.exception.ErrorToAccessRepositoryException;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.domain.Categoria;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.dto.ProdutoDto;
import br.com.pupposoft.fiap.sgr.gerencial.produto.core.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ProdutoMySqlRepository implements ProdutoGateway {

	private ProdutoEntityRepository produtoEntityRepository;

	@Override
	public Optional<ProdutoDto> obterPorId(Long produtoId) {
		try {
			Optional<ProdutoEntity> produtoEntityOp = produtoEntityRepository.findById(produtoId);

			return produtoEntityOp.isEmpty() ? 
							Optional.empty(): Optional.of(mapEntityToDto(produtoEntityOp.get()));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorToAccessRepositoryException();
		}	
	}

	@Override
	public List<ProdutoDto> obterPorCategoria(Categoria categoria) {
        try {
            List<ProdutoEntity> produtosEntities = produtoEntityRepository.findByCategoriaId(categoria.ordinal());
            return produtosEntities.stream().map(this::mapEntityToDto).toList();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorToAccessRepositoryException();
        }
	}

	@Override
	public Long criar(ProdutoDto produtoDto) {
        try {
            ProdutoEntity produtoSavedEntity = produtoEntityRepository.save(mapDtoToEntity(produtoDto));
            return produtoSavedEntity.getId();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorToAccessRepositoryException();
        }
	}

	@Override
	public void alterar(ProdutoDto produtoDto) {
        try {
            produtoEntityRepository.save(mapDtoToEntity(produtoDto));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorToAccessRepositoryException();
        }
	}

	@Override
	public void excluir(Long produtoId) {
        try {
            produtoEntityRepository.deleteById(produtoId);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ErrorToAccessRepositoryException();
        }
	}
	
	private ProdutoDto mapEntityToDto(ProdutoEntity entity) {
		return ProdutoDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.descricao(entity.getDescricao())
				.valor(entity.getValor())
				.categoriaId( entity.getCategoriaId())
				.imagem(entity.getImagem())
				.build();
	}
	
	private ProdutoEntity mapDtoToEntity(ProdutoDto dto) {
		return ProdutoEntity.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.descricao(dto.getDescricao())
				.valor(dto.getValor())
				.categoriaId( dto.getCategoriaId())
				.imagem(dto.getImagem())
				.build();
	}	
}
