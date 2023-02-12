package br.com.ada.bookstore.service;

import br.com.ada.bookstore.controller.TokenDTO;
import br.com.ada.bookstore.model.dto.UsuarioLoginDTO;

public interface UsuarioService extends BaseService<UsuarioLoginDTO> {
	TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws Exception;
	TokenDTO atualizarToken(String refreshToken);
}
