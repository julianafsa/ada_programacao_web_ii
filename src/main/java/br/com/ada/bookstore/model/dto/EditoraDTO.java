package br.com.ada.bookstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditoraDTO {
	private Long id;
	
	@NotBlank(message="O campo nome deve estar preenchido.")
	@Size(message="O campo nome deve ter no máximo 255 caracteres.", max = 255)
	private String nome;
	
	private String descricao;
}
