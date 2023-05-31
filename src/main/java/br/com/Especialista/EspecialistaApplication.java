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
//			System.out.println("Salvando clientes");
//			clientes.save(new Cliente("Joao"));
//			clientes.save(new Cliente("Jose"));
//			List<Cliente>  todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
//			
//			System.out.println("Busca por @query");
//			List<Cliente> cli = clientes.encontrarPorNome("%Joao%");
//			System.out.println(cli);
//			System.out.println("Busca por native query");
//			List<Cliente> cli2 = clientes.encontrarPorNomeNative("Jose");
//			System.out.println(cli2);
			
			
			
		};
	}
}
