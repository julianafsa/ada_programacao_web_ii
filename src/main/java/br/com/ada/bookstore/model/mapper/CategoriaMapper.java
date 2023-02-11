package br.com.ada.bookstore.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.ada.bookstore.model.dto.CategoriaDTO;
import br.com.ada.bookstore.model.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
	List<CategoriaDTO> parseListDTO(List<Categoria> entidades);
	List<Categoria> parseListEntity(List<CategoriaDTO> entidadesDTO);
	CategoriaDTO parseDTO(Categoria entidade);
	Categoria parseEntity(CategoriaDTO entidadeDTO);
}
