package br.com.ada.bookstore.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.ada.bookstore.model.dto.PerfilDTO;
import br.com.ada.bookstore.model.entity.Perfil;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
	List<PerfilDTO> parseListDTO(List<Perfil> entidades);
	List<Perfil> parseListEntity(List<PerfilDTO> entidadesDTO);
	PerfilDTO parseDTO(Perfil entidade);
	Perfil parseEntity(PerfilDTO entidadeDTO);
}
