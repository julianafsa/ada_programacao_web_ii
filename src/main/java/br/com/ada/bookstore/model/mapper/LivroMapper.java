package br.com.ada.bookstore.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ada.bookstore.model.dto.LivroDTO;
import br.com.ada.bookstore.model.entity.Livro;

@Mapper(componentModel = "spring")
public interface LivroMapper {
	List<LivroDTO> parseListDTO(List<Livro> entidades);
	List<Livro> parseListEntity(List<LivroDTO> entidadesDTO);
	LivroDTO parseDTO(Livro entidade);
	@Mapping(target = "categoria.livros", ignore = true)
	@Mapping(target = "editora.livros", ignore = true)
	Livro parseEntity(LivroDTO entidadeDTO);
}
