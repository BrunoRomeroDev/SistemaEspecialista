package br.com.Especialista.Entities;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemPedidoDTO {
	
	private Integer id;
	private Integer quantidade;
	
	public ItemPedidoDTO(ItemPedido itempedido) {
		BeanUtils.copyProperties(itempedido, this);
	}

}
