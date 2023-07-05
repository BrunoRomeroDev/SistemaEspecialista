package br.com.Especialista.Entities;

import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class PedidoDTO {
	
	private Integer cliente;
	private List<ItemPedidoDTO> items;
	
	public PedidoDTO() {
		
	}
	
	public PedidoDTO(Pedido pedido) {
		BeanUtils.copyProperties(pedido, this);
	}
	

}
