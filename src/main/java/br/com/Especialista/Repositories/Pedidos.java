package br.com.Especialista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

}
