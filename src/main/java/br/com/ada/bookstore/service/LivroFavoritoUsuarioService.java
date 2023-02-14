package br.com.ada.bookstore.service;

import java.util.List;

import br.com.ada.bookstore.model.dto.LivroFavoritoUsuarioDTO;
import br.com.ada.bookstore.model.dto.LivroFavoritoUsuarioOutputDTO;

public interface LivroFavoritoUsuarioService {
	List<LivroFavoritoUsuarioOutputDTO> buscarTodosLivrosFavoritosDoUsuario();
	LivroFavoritoUsuarioOutputDTO salvarLivroFavorito(LivroFavoritoUsuarioDTO entidadeDTO);
	void excluir(Long id);

}
