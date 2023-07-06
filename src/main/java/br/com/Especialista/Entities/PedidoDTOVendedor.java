package br.com.Especialista.Entities;

import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class PedidoDTOVendedor {
	
	private Integer cliente;
	private List<ItemPedidoDTO> items;
	private Integer vend;
	
	public PedidoDTOVendedor() {
		
	}
	
	public PedidoDTOVendedor(Pedido pedido) {
		BeanUtils.copyProperties(pedido, this);
	}
	

}
