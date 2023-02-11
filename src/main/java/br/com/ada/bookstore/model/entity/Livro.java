package br.com.ada.bookstore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="isbn", nullable=false, unique = true)
	private String isbn;
	
	@JoinColumn(name = "categoria_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)	
	private Categoria categoria;
	
	@JoinColumn(name = "editora_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)	
	private Editora editora;
}
