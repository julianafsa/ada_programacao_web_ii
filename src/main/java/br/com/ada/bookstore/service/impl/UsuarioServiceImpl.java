package br.com.ada.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ada.bookstore.controller.TokenDTO;
import br.com.ada.bookstore.model.dto.UsuarioDTO;
import br.com.ada.bookstore.model.dto.UsuarioLoginDTO;
import br.com.ada.bookstore.model.entity.Usuario;
import br.com.ada.bookstore.model.mapper.UsuarioMapper;
import br.com.ada.bookstore.repository.UsuarioRepository;
import br.com.ada.bookstore.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTServiceImpl jwtService;
	
	@Autowired
	private AuthServiceImpl authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public List<UsuarioLoginDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public UsuarioLoginDTO buscarUm(Long id) {
		Optional<Usuario> entidadeOp = repository.findById(id);
		if (entidadeOp.isPresent()) {
			Usuario usuario = entidadeOp.get();
			return mapper.parseDTO(usuario);
		}
		throw new EntityNotFoundException();
	}
	
	public UsuarioLoginDTO criar(UsuarioLoginDTO entidadeDTO) {
		Usuario usuario = mapper.parseEntity(entidadeDTO);
		usuario.setPassword(encoder.encode(usuario.getPassword())); // Salva a senha criptografada.
		usuario.setId(null);
		usuario = repository.save(usuario);
		return mapper.parseDTO(usuario);
	}
	
	public UsuarioLoginDTO editar(Long id, UsuarioLoginDTO entidadeDTO) {
		final Optional<Usuario> entidadeOp = repository.findById(id);
		if (entidadeOp.isPresent()) {
			Usuario usuario = entidadeOp.get();
			usuario.setId(id);
			usuario.setNome(entidadeDTO.getNome());
			usuario.setEmail(entidadeDTO.getEmail());
			usuario = repository.save(usuario);
			return mapper.parseDTO(usuario);
		}
		throw new EntityNotFoundException();
	}
	
	public void excluir(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		repository.deleteById(id);
	}
	
	public TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws AuthenticationException,UsernameNotFoundException {
		UsernamePasswordAuthenticationToken autentication = 
				new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(), usuarioLoginDTO.getPassword());
		authenticationManager.authenticate(autentication);
		final Usuario usuario = (Usuario) authService.loadUserByUsername(usuarioLoginDTO.getUsername());
		return buildTokenDTO(usuario.getUsername(), usuario);
	}
	
	public TokenDTO atualizarToken(String refreshToken) {
		if (jwtService.validRefreshToken(refreshToken)) {
			String username = jwtService.getUsernameByRefreshToken(refreshToken);
			final Usuario usuario = (Usuario) authService.loadUserByUsername(username);
			return buildTokenDTO(username, usuario);
		}
		throw new ExpiredJwtException(null, null, "Refresh token expirado.");
	}
	
	private TokenDTO buildTokenDTO(String username, Usuario usuario) {
		UsuarioDTO usuarioDTO = null;
		if (usuario != null) {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setNome(usuario.getNome());
			usuarioDTO.setEmail(usuario.getEmail());
			usuarioDTO.setPerfil(usuario.getPerfil().getId());
		}
		
		final String token = jwtService.generateToken(username);
		final String refreshToken = jwtService.generateRefreshToken(username);
		return TokenDTO.builder()
				.token(token)
				.refreshToken(refreshToken)
				.type("Bearer")
				.user(usuarioDTO)
				.build();
	}
	
}
