package br.com.ada.bookstore.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PerfilDTO {
	private Long id;
	private String nome;
	private String descricao;
}
