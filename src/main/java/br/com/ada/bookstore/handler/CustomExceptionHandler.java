package br.com.ada.bookstore.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ada.bookstore.model.dto.error.ErroDTO;
import br.com.ada.bookstore.model.dto.error.ErroDeFormularioDTO;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDTO> handle(final MethodArgumentNotValidException exception) {
		
		final List<ErroDeFormularioDTO> dto = new ArrayList<>();
		final List<FieldError> camposErrados = exception.getBindingResult().getFieldErrors();
		camposErrados.forEach(e -> {
			final String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			final ErroDeFormularioDTO erro = new ErroDeFormularioDTO(new ErroDTO(e.getField(), mensagem));
			dto.add(erro);
		});
		return dto;
		
	}

}
