package br.com.ada.bookstore.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioLoginDTO extends UsuarioDTO {
	private String username;
	private String password;
}
