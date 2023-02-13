package br.com.ada.bookstore.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ada.bookstore.model.dto.EditoraDTO;
import br.com.ada.bookstore.model.entity.Editora;

@Mapper(componentModel = "spring")
public interface EditoraMapper {
	List<EditoraDTO> parseListDTO(List<Editora> entidades);
	List<Editora> parseListEntity(List<EditoraDTO> entidadesDTO);
	EditoraDTO parseDTO(Editora entidade);
	@Mapping(target = "livros", ignore = true)
	Editora parseEntity(EditoraDTO entidadeDTO);
}
