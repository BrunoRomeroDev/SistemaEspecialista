package br.com.Especialista.Entities;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProdutoDTO {
	
	@NotEmpty(message="{campo.descricao}")
	private String descricao;

}
