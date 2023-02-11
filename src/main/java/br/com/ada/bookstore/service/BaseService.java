package br.com.ada.bookstore.service;

import java.util.List;

public interface BaseService<T> {
	List<T> buscarTodos();
	T buscarUm(Long id);
	T criar(T entidadeDTO);
	T editar(Long id, T entidadeDTO);
	void excluir(Long id);
}
