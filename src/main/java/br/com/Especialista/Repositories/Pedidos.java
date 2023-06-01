package br.com.Especialista.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Entities.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByClientes(Cliente cliente);

}
