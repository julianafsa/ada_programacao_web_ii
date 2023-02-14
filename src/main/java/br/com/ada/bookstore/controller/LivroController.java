package br.com.ada.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.bookstore.model.dto.LivroDTO;
import br.com.ada.bookstore.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/livros")
@Slf4j
public class LivroController extends BaseController<LivroDTO, LivroService> {

	public LivroController(LivroService service) {
		super(service);
	}
	
	@PostMapping("/varios")
    public ResponseEntity<List<LivroDTO>> criarVarios(@RequestBody List<LivroDTO> entidades) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.criarVarios(entidades));
        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
	@PostMapping("/filtrar")
    public ResponseEntity<List<LivroDTO>> filtrarPorNomeOuIsbn(@RequestBody LivroDTO entidade) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.filtrarPorNomeOuIsbn(entidade));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
	@GetMapping("/filtrar/categoria/{idCategoria}")
    public ResponseEntity<List<LivroDTO>> filtrarPorIdCategoria(@PathVariable("idCategoria") Long idCategoria) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.buscarPorIdCategoria(idCategoria));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
	@GetMapping("/filtrar/editora/{idEditora}")
    public ResponseEntity<List<LivroDTO>> filtrarPorIdEditora(@PathVariable("idEditora") Long idEditora) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(service.buscarPorIdEditora(idEditora));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
	@GetMapping("/filtrar/{nome}")
    public ResponseEntity<List<LivroDTO>> buscarUm(@PathVariable("nome") String nome) {
        try {
        	return ResponseEntity.ok(service.buscarPorNome(nome));
        } catch(EntityNotFoundException ex) {
        	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
