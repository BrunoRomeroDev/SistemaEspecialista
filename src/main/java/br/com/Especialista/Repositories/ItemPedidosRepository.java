package br.com.Especialista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.ItemPedido;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface ItemPedidosRepository extends JpaRepository<ItemPedido,Integer> {

}
