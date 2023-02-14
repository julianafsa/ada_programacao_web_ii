package br.com.ada.bookstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroFavoritoInputDTO {
	@NotBlank(message="O campo id do livro deve estar preenchido.")
	private Long id;
}
