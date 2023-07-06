package br.com.Especialista.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PedidoNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	

}
