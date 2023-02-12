package br.com.ada.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.bookstore.model.dto.UsuarioLoginDTO;
import br.com.ada.bookstore.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController extends BaseController<UsuarioLoginDTO, UsuarioService>  {
	
	public UsuarioController(UsuarioService service) {
		super(service);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> logar(@RequestBody @Valid UsuarioLoginDTO entidade) {
		try {
            return ResponseEntity.ok(service.logar(entidade));
        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}
	
}
