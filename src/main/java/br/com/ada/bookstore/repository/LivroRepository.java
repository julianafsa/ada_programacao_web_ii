package br.com.ada.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ada.bookstore.model.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	@Query(value = "SELECT l FROM Livro l WHERE UPPER(l.nome) LIKE CONCAT('%',UPPER(:nome),'%')")
	List<Livro> findByNome(@Param("nome") String nome);
	
	@Query(value = "SELECT l FROM Livro l WHERE l.categoria.id = :idCategoria")
	List<Livro> findByIdCategoria(@Param("idCategoria") Long idCategoria);
	
	@Query(value = "SELECT l FROM Livro l WHERE l.editora.id = :idEditora")
	List<Livro> findByIdEditora(@Param("idEditora") Long idEditora);
}
