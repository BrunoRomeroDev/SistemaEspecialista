package br.com.Especialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.Especialista.Services.ProdutosService;

@SpringBootApplication
public class EspecialistaApplication implements CommandLineRunner {

	@Autowired
	private ProdutosService produtoservice;
	public static void main(String[] args) {
		SpringApplication.run(EspecialistaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		produtoservice.listarProdutosBydescricaoSpec();
		
	}
	
}
