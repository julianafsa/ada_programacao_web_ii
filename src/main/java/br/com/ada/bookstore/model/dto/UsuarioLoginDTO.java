package br.com.ada.bookstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UsuarioLoginDTO extends UsuarioDTO {
	@NotBlank(message="O campo username deve estar preenchido.")
	private String username;
	
	@NotBlank(message="O campo password deve estar preenchido.")
	private String password;
}
