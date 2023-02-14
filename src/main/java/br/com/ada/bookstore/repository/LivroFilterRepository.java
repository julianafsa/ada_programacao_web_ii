package br.com.ada.bookstore.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.ada.bookstore.model.entity.Livro;
import br.com.ada.bookstore.model.entity.QLivro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class LivroFilterRepository extends QuerydslRepositorySupport {
	
	@PersistenceContext
	private EntityManager em;
	
	public LivroFilterRepository() {
		super(Livro.class);
	}
	
	@Transactional
	public List<Livro> filtrarPorNomeOuIsbn(Livro filter) {
		QLivro livro = QLivro.livro;
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter.getNome() != null) {
			Predicate nomePredicate = livro.nome.likeIgnoreCase("%" + filter.getNome() + "%"); 
			predicates.add(nomePredicate);
		}
		
		if (filter.getIsbn() != null) {
			Predicate isbnPredicate = livro.isbn.equalsIgnoreCase(filter.getIsbn()); 
			predicates.add(isbnPredicate);
		}
		
		return new JPAQueryFactory(em)
				.selectFrom(livro)
				.where(
						predicates.toArray(new Predicate[0]))
				.fetch();
		
	}

}
