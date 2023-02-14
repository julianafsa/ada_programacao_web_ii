package br.com.ada.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.bookstore.model.dto.LivroFavoritoUsuarioDTO;
import br.com.ada.bookstore.model.dto.LivroFavoritoUsuarioOutputDTO;
import br.com.ada.bookstore.service.LivroFavoritoUsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/favoritos")
@Slf4j
public class LivroFavoritoUsuarioController {
	
	private LivroFavoritoUsuarioService service;

	public LivroFavoritoUsuarioController(LivroFavoritoUsuarioService service) {
		this.service = service;
	}
	
    @GetMapping
    public ResponseEntity<List<LivroFavoritoUsuarioOutputDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }
	
	@PostMapping
	public ResponseEntity<LivroFavoritoUsuarioOutputDTO> criar(@RequestBody @Valid LivroFavoritoUsuarioDTO entidade) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.criar(entidade));
        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remover(@PathVariable("id") Long id) {
        try {
        	service.excluir(id);
        	return ResponseEntity.status(HttpStatus.OK).build();
        	
        } catch(EntityNotFoundException ex) {	
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
}
