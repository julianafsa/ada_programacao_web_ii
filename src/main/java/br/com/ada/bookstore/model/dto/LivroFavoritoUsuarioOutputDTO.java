package br.com.ada.bookstore.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroFavoritoUsuarioOutputDTO { // Somente para saída.
	private Long id;
	private String nome;
	private String isbn;
	private CategoriaDTO categoria;
	private EditoraDTO editora;
}
