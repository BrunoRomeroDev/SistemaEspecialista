package br.com.Especialista.Exception;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class NotAuthorizedException extends Throwable{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private OffsetDateTime data;
	
	
	public NotAuthorizedException() {
		this.message = "Não Autorizado.";
		this.data = OffsetDateTime.now();
	}
	
	

}
