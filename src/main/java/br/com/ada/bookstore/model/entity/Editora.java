package br.com.ada.bookstore.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="editoras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editora {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="descricao", nullable=true)
	private String descricao;
	
	@OneToMany(mappedBy = "editora")
	private List<Livro> livros;
}
