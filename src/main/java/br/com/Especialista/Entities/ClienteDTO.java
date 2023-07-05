package br.com.Especialista.Entities;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class ClienteDTO {
	
	
	private Integer id;
	private String nome;
	
	public ClienteDTO(Cliente cliente) {
		BeanUtils.copyProperties(cliente, this);
	}

}
