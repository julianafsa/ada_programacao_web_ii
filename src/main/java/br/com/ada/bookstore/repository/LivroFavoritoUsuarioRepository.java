package br.com.ada.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ada.bookstore.model.entity.LivroFavoritoUsuario;

@Repository
public interface LivroFavoritoUsuarioRepository extends JpaRepository<LivroFavoritoUsuario, Long> {
	
	@Query(value = "SELECT l FROM LivroFavoritoUsuario l WHERE l.usuario.id = :usuarioId")
	List<LivroFavoritoUsuario> buscarTodosLivrosFavoritosDoUsuario(@Param("usuarioId") Long usuarioId);
	
	@Query(value = "SELECT l FROM LivroFavoritoUsuario l "
			+ "WHERE l.usuario.id = :usuarioId "
			+ "AND "
			+ "l.livro.id = :livroId")
	List<LivroFavoritoUsuario> findByIdLivro(@Param("usuarioId") Long usuarioId, 
			@Param("livroId") Long livroId);

}
