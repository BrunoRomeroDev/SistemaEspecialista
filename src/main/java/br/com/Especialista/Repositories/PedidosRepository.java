package br.com.Especialista.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Entities.Pedido;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByClientes(Cliente cliente);

}
