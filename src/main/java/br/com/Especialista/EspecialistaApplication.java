package br.com.Especialista;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Entities.Pedido;
import br.com.Especialista.Repositories.Clientes;
import br.com.Especialista.Repositories.Pedidos;

@SpringBootApplication
public class EspecialistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EspecialistaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			System.out.println("Salvando Clientes");
			Cliente cli1 = new Cliente("Rodrigo");
			clientes.save(cli1);
			
			Pedido p = new Pedido();
			p.setClientes(cli1);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidos.save(p);
			

		};
	}
	
}
