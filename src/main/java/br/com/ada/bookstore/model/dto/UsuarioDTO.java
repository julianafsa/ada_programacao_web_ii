package br.com.ada.bookstore.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
	private Long id;
//	@NotBlank(message="O campo nome deve estar preenchido.")
	private String nome;
	
//	@NotBlank(message="O campo email deve estar preenchido.")
	private String email;
}
