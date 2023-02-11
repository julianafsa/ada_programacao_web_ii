package br.com.ada.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ada.bookstore.model.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
