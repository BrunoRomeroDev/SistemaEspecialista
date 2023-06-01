package br.com.Especialista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.ItemPedido;

public interface ItemPedidos extends JpaRepository<ItemPedido,Integer> {

}
