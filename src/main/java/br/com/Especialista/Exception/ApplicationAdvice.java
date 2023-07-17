package br.com.Especialista.Exception;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ApplicationAdvice {
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionDefault> tipoValorErrado() {
		ExceptionDefault ed = new ExceptionDefault("Tipo de Valor Errado",OffsetDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ed);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ExceptionDefault> valorNaoEncontrado(){
		ExceptionDefault ed = new ExceptionDefault("Valor não encontrado",OffsetDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ed);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	public ResponseEntity<ExceptionDefault> pedidoNaoEncontrado(){
		ExceptionDefault ed = new ExceptionDefault("Pedido não Encontrado",OffsetDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ed);
	}
	                  
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ExceptionDefault> handleMethodNotValidException(MethodArgumentNotValidException manve) {
		List<String> errors = manve
			.getBindingResult()
			.getAllErrors()
			.stream()
			.map(e-> e.getDefaultMessage())
			.collect(Collectors.toList());
		ExceptionDefault ed = new ExceptionDefault(errors.toString(),OffsetDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ed);
		
	}
	
}
