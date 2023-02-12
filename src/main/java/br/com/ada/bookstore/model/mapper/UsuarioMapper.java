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
	@Mapping(source = "perfil.id", target = "perfil")
	UsuarioLoginDTO parseDTO(Usuario entidade);
	
	@Mapping(source = "perfil.id", target = "perfil")
	UsuarioDTO parseUsuarioDTO(Usuario entidade);
	
	@Mapping(target="authorities", ignore = true)
	@Mapping(source = "perfil", target = "perfil.id")
	Usuario parseEntity(UsuarioLoginDTO entidadeDTO);
}
