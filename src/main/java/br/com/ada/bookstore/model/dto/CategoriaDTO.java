package br.com.ada.bookstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDTO {
	private Long id;
	
	@NotBlank(message="O campo nome deve estar preenchido.")
	@Size(message="O campo nome deve ter no máximo 100 caracteres.", max = 100)
	private String nome;
}
