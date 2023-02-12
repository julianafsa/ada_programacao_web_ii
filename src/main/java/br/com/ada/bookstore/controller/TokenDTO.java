package br.com.ada.bookstore.controller;

import br.com.ada.bookstore.model.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {
	private UsuarioDTO user;
	private String type; // Tipo de token. Ex.: Bearer token.
	private String token;
	private String refreshToken;
}
