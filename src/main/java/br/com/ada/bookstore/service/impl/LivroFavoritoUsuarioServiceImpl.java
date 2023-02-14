package br.com.ada.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ada.bookstore.model.dto.LivroDTO;
import br.com.ada.bookstore.model.dto.LivroFavoritoUsuarioDTO;
import br.com.ada.bookstore.model.dto.LivroFavoritoUsuarioOutputDTO;
import br.com.ada.bookstore.model.entity.LivroFavoritoUsuario;
import br.com.ada.bookstore.model.entity.Usuario;
import br.com.ada.bookstore.model.mapper.LivroFavoritoUsuarioMapper;
import br.com.ada.bookstore.model.mapper.LivroMapper;
import br.com.ada.bookstore.repository.LivroFavoritoUsuarioRepository;
import br.com.ada.bookstore.repository.UsuarioRepository;
import br.com.ada.bookstore.service.LivroFavoritoUsuarioService;
import br.com.ada.bookstore.service.LivroService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class LivroFavoritoUsuarioServiceImpl implements LivroFavoritoUsuarioService {
	
	@Autowired
	private LivroFavoritoUsuarioRepository repository;
	
	@Autowired
	private LivroFavoritoUsuarioMapper mapper;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private LivroMapper livroMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<LivroFavoritoUsuarioOutputDTO> buscarTodosLivrosFavoritosDoUsuario() {
		final Usuario usuario = this.buscarUsuarioLogado();
		return mapper.parseListDTO(
				repository.buscarTodosLivrosFavoritosDoUsuario(usuario.getId()));
	}
	
	@Override
	@Transactional
	public LivroFavoritoUsuarioOutputDTO salvarLivroFavorito(@RequestBody LivroFavoritoUsuarioDTO entidadeDTO) {
		final Usuario usuario = this.buscarUsuarioLogado();
		
		final Long livroId = entidadeDTO.getLivro().getId();
		LivroDTO livroDTO = null;
		if (livroId != null) {
			livroDTO = livroService.buscarUm(livroId);
			if (livroDTO == null) {
				throw new EntityNotFoundException("Livro não encontrado.");
			}
		}
		
		final Long usuarioId = usuario.getId();
		final List<LivroFavoritoUsuario> livros = repository.findByIdLivro(usuarioId, livroId);
		if (!livros.isEmpty()) {
			throw new EntityNotFoundException("Livro já favoritado.");
		}
		
		final LivroFavoritoUsuario entidade = new LivroFavoritoUsuario();
		entidade.setLivro(livroMapper.parseEntity(livroDTO));
		entidade.setUsuario(usuario);
		
		entidade.setId(null);
		repository.save(entidade);
		em.refresh(entidade);
		return mapper.parseDTO(entidade);
	}
	
	@Override
	public void excluir(Long id) {
		final Usuario usuario = this.buscarUsuarioLogado();
		
		final Long usuarioId = usuario.getId();
		final List<LivroFavoritoUsuario> livros = repository.findByIdLivro(usuarioId, id);
		if (livros.isEmpty()) {
			throw new EntityNotFoundException("Livro favorito não encontrado.");
		}
		
		repository.deleteById(livros.get(0).getId()); // Somente 1 livro é retornado, pois não é permitido favoritar um livro já favoritado.
	}
	
	private Usuario buscarUsuarioLogado() {
		final String username = String.valueOf(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		final Optional<Usuario> usuarioOp = usuarioRepository.findByUsername(username);
		if (!usuarioOp.isPresent()) {
			throw new EntityNotFoundException("Usuário não encontrado.");
		}
		return usuarioOp.get();
	}
	


}
