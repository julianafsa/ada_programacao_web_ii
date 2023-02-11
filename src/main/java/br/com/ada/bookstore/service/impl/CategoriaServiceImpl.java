package br.com.ada.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ada.bookstore.model.dto.CategoriaDTO;
import br.com.ada.bookstore.model.entity.Categoria;
import br.com.ada.bookstore.model.mapper.CategoriaMapper;
import br.com.ada.bookstore.repository.CategoriaRepository;
import br.com.ada.bookstore.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private CategoriaMapper mapper;
	
	@Override
	public List<CategoriaDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	@Override
	public CategoriaDTO buscarUm(Long id) {
		final Optional<Categoria> entidadeOp = repository.findById(id);
		if (entidadeOp.isPresent()) {
			final Categoria entidade = entidadeOp.get();
			return mapper.parseDTO(entidade);
		}
		throw new EntityNotFoundException();
	}
	
	@Override
	public CategoriaDTO criar(CategoriaDTO entidadeDTO) {
		final Categoria entidade = mapper.parseEntity(entidadeDTO);
		entidade.setId(null);
		repository.save(entidade);
		return mapper.parseDTO(entidade);
	}
	
	@Override
	public CategoriaDTO editar(Long id, CategoriaDTO entidadeDTO) {
		if (repository.existsById(id)) {
			Categoria entidade = mapper.parseEntity(entidadeDTO);
			entidade.setId(id);
			entidade = repository.save(entidade);
			return mapper.parseDTO(entidade);
		}
		throw new EntityNotFoundException();
	}
	
	@Override
	public void excluir(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		repository.deleteById(id);
	}

}
