package br.com.ada.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ada.bookstore.model.dto.LivroDTO;
import br.com.ada.bookstore.model.entity.Categoria;
import br.com.ada.bookstore.model.entity.Editora;
import br.com.ada.bookstore.model.entity.Livro;
import br.com.ada.bookstore.model.mapper.LivroMapper;
import br.com.ada.bookstore.repository.LivroFilterRepository;
import br.com.ada.bookstore.repository.LivroRepository;
import br.com.ada.bookstore.service.LivroService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private LivroFilterRepository filterRepository;
	
	@Autowired
	private LivroMapper mapper;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<LivroDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	@Override
	public LivroDTO buscarUm(Long id) {
		final Optional<Livro> entidadeOp = repository.findById(id);
		if (entidadeOp.isPresent()) {
			final Livro entidade = entidadeOp.get();
			return mapper.parseDTO(entidade);
		}
		throw new EntityNotFoundException();
	}
	
	public List<LivroDTO> buscarPorNome(String nome) {
		final List<Livro> entidades = repository.findByNome(nome);
		return mapper.parseListDTO(entidades);
	}
	
	@Override
	public List<LivroDTO> buscarPorCategoria(Categoria categoria) {
		final List<Livro> entidades = repository.findByCategoria(categoria);
		return mapper.parseListDTO(entidades);
	}
	
	@Override
	public List<LivroDTO> buscarPorEditora(Editora editora) {
		final List<Livro> entidades = repository.findByEditora(editora);
		return mapper.parseListDTO(entidades);
	}
	
	@Override
	public List<LivroDTO> filter(LivroDTO entityDTO) {
		final Livro entidade = mapper.parseEntity(entityDTO);
		final List<Livro> entidades = filterRepository.filtrar(entidade);
		return mapper.parseListDTO(entidades);
	}
	
	@Override
	@Transactional
	public LivroDTO criar(LivroDTO entidadeDTO) {
		Livro entidade = mapper.parseEntity(entidadeDTO);
		entidade.setId(null);
		repository.save(entidade);
		em.refresh(entidade);
		return mapper.parseDTO(entidade);
	}
	
	@Override
	@Transactional
	public List<LivroDTO> criarVarios(List<LivroDTO> entidadesDTO) {
		final List<Livro> entidades = mapper.parseListEntity(entidadesDTO);
		repository.saveAll(entidades);
		entidades.stream().forEach(entidade -> em.refresh(entidade));
		return mapper.parseListDTO(entidades);
	}
	
	@Override
	public LivroDTO editar(Long id, LivroDTO entidadeDTO) {
		if (repository.existsById(id)) {
			Livro entidade = mapper.parseEntity(entidadeDTO);
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
