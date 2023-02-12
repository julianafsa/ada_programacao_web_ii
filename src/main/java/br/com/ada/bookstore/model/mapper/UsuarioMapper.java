package br.com.ada.bookstore.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ada.bookstore.model.dto.UsuarioDTO;
import br.com.ada.bookstore.model.dto.UsuarioLoginDTO;
import br.com.ada.bookstore.model.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	List<UsuarioLoginDTO> parseListDTO(List<Usuario> entidades);
	List<Usuario> parseListEntity(List<UsuarioLoginDTO> entidadesDTO);
	@Mapping(target = "password", ignore = true)
	UsuarioLoginDTO parseDTO(Usuario entidade);
	UsuarioDTO parteUsuarioDTO(Usuario entidade);
	@Mapping(target="authorities", ignore = true)
	Usuario parseEntity(UsuarioLoginDTO entidadeDTO);
}
