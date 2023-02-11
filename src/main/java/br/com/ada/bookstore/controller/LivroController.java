package br.com.ada.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.bookstore.model.dto.LivroDTO;
import br.com.ada.bookstore.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController extends BaseController<LivroDTO, LivroService> {

	public LivroController(LivroService service) {
		super(service);
	}

}
