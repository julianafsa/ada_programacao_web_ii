package br.com.ada.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ada.bookstore.model.dto.EditoraDTO;
import br.com.ada.bookstore.model.entity.Editora;
import br.com.ada.bookstore.model.mapper.EditoraMapper;
import br.com.ada.bookstore.repository.EditoraRepository;
import br.com.ada.bookstore.service.EditoraService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EditoraServiceImpl implements EditoraService {

	@Autowired
	private EditoraRepository repository;
	
	@Autowired
	private EditoraMapper mapper;
	
	public List<EditoraDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public EditoraDTO buscarUm(Long id) {
		final Optional<Editora> entidadeOp = repository.findById(id);
		if (entidadeOp.isPresent()) {
			final Editora entidade = entidadeOp.get();
			return mapper.parseDTO(entidade);
		}
		throw new EntityNotFoundException();
	}
	
	public EditoraDTO criar(EditoraDTO entidadeDTO) {
		final Editora entidade = mapper.parseEntity(entidadeDTO);
		entidade.setId(null);
		repository.save(entidade);
		return mapper.parseDTO(entidade);
	}
	
	public EditoraDTO editar(Long id, EditoraDTO entidadeDTO) {
		if (repository.existsById(id)) {
			Editora entidade = mapper.parseEntity(entidadeDTO);
			entidade.setId(id);
			entidade = repository.save(entidade);
			return mapper.parseDTO(entidade);
		}
		throw new EntityNotFoundException();
	}
	
	public void excluir(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		repository.deleteById(id);
	}

}
