package br.com.Especialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.com.Especialista.Services.ProdutosService;

@SpringBootApplication
@EnableFeignClients
public class EspecialistaApplication implements CommandLineRunner {

	@Autowired
	private ProdutosService produtoservice;
	public static void main(String[] args) {
		SpringApplication.run(EspecialistaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------Filtr por preco----------");
		produtoservice.listarProdutosPorPreco();
		System.out.println("--------FIltr por descricao---------");
		produtoservice.listarProdutoPorDescricao();
		System.out.println("----------Filtra por descricao - Ordenado-------");
		produtoservice.listarProdutoPorDescricaoOrdenado();
		System.out.println("-------Filtra por descricao - paginado----------");
		produtoservice.listarProdutoPorDescricaoPaginado();
		System.out.println("--------Inicia com --------");
		produtoservice.listaProdutoIniciaCom();
		System.out.println("----------termina com-------");
		produtoservice.listaProdutoTerminaCom();
		System.out.println("--------Filtra por descricao - Specification---------");
		produtoservice.listarProdutosBydescricaoSpec();
		System.out.println("--------Produto maior que - Specification---------");
		produtoservice.listarProdutosMaiorQue();
		System.out.println("--------------Native query------------");
		produtoservice.buscaPorDescricao();
		
	}
	
}
