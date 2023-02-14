package br.com.ada.bookstore.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroFavoritoUsuarioDTO {
	@NotNull(message="O campo livro deve estar preenchido.")
	private LivroFavoritoInputDTO livro;
}
