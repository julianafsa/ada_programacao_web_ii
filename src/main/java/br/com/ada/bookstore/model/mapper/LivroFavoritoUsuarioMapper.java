package br.com.ada.bookstore.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ada.bookstore.model.dto.LivroFavoritoUsuarioOutputDTO;
import br.com.ada.bookstore.model.entity.LivroFavoritoUsuario;

@Mapper(componentModel = "spring")
public interface LivroFavoritoUsuarioMapper {
	@Mapping(source = "livro.id", target = "id")
	@Mapping(source = "livro.nome", target = "nome")
	@Mapping(source = "livro.isbn", target = "isbn")
	@Mapping(source = "categoria.id", target = "categoria.id")
	@Mapping(source = "categoria.nome", target = "categoria.nome")
	@Mapping(source = "editora.id", target = "editora.id")
	@Mapping(source = "editora.nome", target = "editora.nome")
	@Mapping(source = "editora.descricao", target = "editora.descricao")
	List<LivroFavoritoUsuarioOutputDTO> parseListDTO(List<LivroFavoritoUsuario> entidades);
	
	@Mapping(source = "livro.id", target = "id")
	@Mapping(source = "livro.nome", target = "nome")
	@Mapping(source = "livro.isbn", target = "isbn")
	@Mapping(source = "livro.categoria.id", target = "categoria.id")
	@Mapping(source = "livro.categoria.nome", target = "categoria.nome")
	@Mapping(source = "livro.editora.id", target = "editora.id")
	@Mapping(source = "livro.editora.nome", target = "editora.nome")
	@Mapping(source = "livro.editora.descricao", target = "editora.descricao")
	LivroFavoritoUsuarioOutputDTO parseDTO(LivroFavoritoUsuario entidade);
}
