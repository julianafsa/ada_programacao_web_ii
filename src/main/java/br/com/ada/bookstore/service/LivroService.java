package br.com.ada.bookstore.service;

import java.util.List;

import br.com.ada.bookstore.model.dto.LivroDTO;

public interface LivroService extends BaseService<LivroDTO> {
	List<LivroDTO> buscarPorNome(String nome);
	List<LivroDTO> criarVarios(List<LivroDTO> entidadesDTO);
	List<LivroDTO> filter(LivroDTO entidadeDTO);
}
