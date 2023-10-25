package br.com.Especialista.Entities;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

	
	private Integer id;	
	private String cep;	
	private String uf;	
	private String localidade;	
	private String bairro;	
	private String logradouro;	
	private String complemento;

	
	public EnderecoDTO(Endereco end) {
		BeanUtils.copyProperties(end, this);
		
	}
	

}
