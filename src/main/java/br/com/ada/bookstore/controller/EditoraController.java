package br.com.ada.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.bookstore.model.dto.EditoraDTO;
import br.com.ada.bookstore.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController extends BaseController<EditoraDTO, EditoraService> {

	public EditoraController(EditoraService service) {
		super(service);
	}

}
