package br.com.ada.bookstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroDTO {
	private Long id;
	
	@NotBlank(message="O campo nome deve estar preenchido.")
	private String nome;
	
	@NotBlank(message="O campo isbn deve estar preenchido.")
	@Size(message="O campo isbn deve ter no máximo 13 caracteres.", max = 13)
	private String isbn;

	@NotNull(message="A categoria do livro deve ser informada.")
	private CategoriaDTO categoria;
	
	@NotNull(message="A editora do livro deve ser informada.")
	private EditoraDTO editora;
}
