package br.com.ada.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.bookstore.model.dto.CategoriaDTO;
import br.com.ada.bookstore.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<CategoriaDTO, CategoriaService> {

	public CategoriaController(CategoriaService service) {
		super(service);
	}

}
