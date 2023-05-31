package br.com.Especialista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Repositories.Clientes;

@SpringBootApplication
public class EspecialistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EspecialistaApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			System.out.println("Salvando clientes");
			clientes.save(new Cliente("Joao"));
			clientes.save(new Cliente("Jose"));
			List<Cliente>  todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Atualizando clientes");
			todosClientes.forEach(c -> { 
				c.setNome(c.getNome()+"atualizado.");
				clientes.save(c);
				});
			
			todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Buscando clientes");
			clientes.findByNomeLike("Romer").forEach(System.out::println);
			
			System.out.println("deletando  clientes");
			clientes.findAll().forEach(c ->{
				clientes.delete(c);
			});
			
			todosClientes = clientes.findAll();
			if(todosClientes.isEmpty()) {
				System.out.println("Nenhum Cliente Encontrado.");
				System.out.println("Adicionando novos.");
				clientes.save(new Cliente("Joaquim"));
				clientes.save(new Cliente("Manuel"));
			}else {
				todosClientes.forEach(System.out::println);
			}
			
		};
	}
}
