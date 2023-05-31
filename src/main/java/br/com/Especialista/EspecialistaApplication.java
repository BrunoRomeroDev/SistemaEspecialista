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
			clientes.salvar(new Cliente("Joao"));
			clientes.salvar(new Cliente("Jose"));
			List<Cliente>  todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Atualizando clientes");
			todosClientes.forEach(c -> { 
				c.setNome(c.getNome()+"atualizado.");
				clientes.atualizar(c);
				});
			
			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
			System.out.println("Buscando clientes");
			clientes.buscarPorNome("Romer").forEach(System.out::println);
			
			System.out.println("deletando  clientes");
			clientes.obterTodos().forEach(c ->{
				clientes.deletar(c);
			});
			
			todosClientes = clientes.obterTodos();
			if(todosClientes.isEmpty()) {
				System.out.println("Nenhum Cliente Encontrado.");
				System.out.println("Adicionando novos.");
				clientes.salvar(new Cliente("Joaquim"));
				clientes.salvar(new Cliente("Manuel"));
			}else {
				todosClientes.forEach(System.out::println);
			}
			
		};
	}
}
