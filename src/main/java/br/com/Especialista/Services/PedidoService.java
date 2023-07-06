package br.com.Especialista.Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Entities.ItemPedido;
import br.com.Especialista.Entities.ItemPedidoDTO;
import br.com.Especialista.Entities.Pedido;
import br.com.Especialista.Entities.PedidoDTO;
import br.com.Especialista.Entities.PedidoDTOVendedor;
import br.com.Especialista.Entities.Produto;
import br.com.Especialista.Entities.StatusPedido;
import br.com.Especialista.Exception.PedidoNaoEncontradoException;
import br.com.Especialista.Repositories.ClientesRepository;
import br.com.Especialista.Repositories.ItemPedidosRepository;
import br.com.Especialista.Repositories.PedidosRepository;
import br.com.Especialista.Repositories.ProdutosRepository;
import br.com.Especialista.Repositories.VendedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	@Autowired
	private ProdutosRepository produtorepository;
	@Autowired
	private ClientesRepository clienterepository;
	@Autowired
	private PedidosRepository pedidorepository;
	@Autowired
	private ItemPedidosRepository itemspedidorepository;
	@Autowired
	private VendedorRepository vendedorrepository;
	
	@Transactional
	public Pedido salvarpedido(PedidoDTO pedidodto) {

		Pedido pedido = new Pedido() ;
		pedido.setClientes(clienterepository.findById(pedidodto.getCliente()).get());
		pedido.setDataPedido( LocalDate.now());
		pedido.setStatuspedido(StatusPedido.REALIZADO);
		List<ItemPedido> itenspedido = converteItems(pedido, pedidodto.getItems());
				
		pedido.setItens(itenspedido);
		pedido.setTotal(new BigDecimal(itenspedido.size()));
		
		pedidorepository.save(pedido);
		itemspedidorepository.saveAll(itenspedido);
		
		return pedidorepository.findById(pedido.getId()).get();
	}
	
	@Transactional
	public Pedido salvarpedidoFull(PedidoDTOVendedor pedidodto) {
		Optional<Cliente> cliente = clienterepository.findById(pedidodto.getCliente());
		cliente.stream().map(p -> { p.setEndereco("Rua x");
		return p.getEndereco();}) ;
		Pedido pedido = new Pedido() ;
		pedido.setClientes(cliente.get());
		pedido.setDataPedido( LocalDate.now());
		pedido.setVendedor(vendedorrepository.findById(pedidodto.getVend()).get());
		pedido.setStatuspedido(StatusPedido.REALIZADO);
		List<ItemPedido> itenspedido = converteItems(pedido, pedidodto.getItems());
				
		pedido.setItens(itenspedido);
		pedido.setTotal(new BigDecimal(itenspedido.size()));
		
		
		pedidorepository.save(pedido);
		itemspedidorepository.saveAll(itenspedido);
		
		return pedidorepository.findById(pedido.getId()).get();
	}
	
	public Optional<Pedido> buscaPedidoId(Integer id) {
		return pedidorepository.findById(id);
	}
	
	private List<ItemPedido> converteItems(Pedido pedido, List<ItemPedidoDTO> items){
		if(items.isEmpty()) {
			throw new NullPointerException();
		}
		
		items.stream().forEach(d -> System.out.println(produtorepository.findById(d.getId()))); 
		return items
				.stream()
				.map( dto -> {
					Integer idProduto = dto.getId();
					Produto produto = produtorepository
												.findById(idProduto)
												.orElseThrow(() -> new NullPointerException());
					ItemPedido itempedido = new ItemPedido();
					itempedido.setQtd(dto.getQuantidade());
					itempedido.setPedido(pedido);
					itempedido.setProduto(produto);
					return itempedido;
				}).collect(Collectors.toList());
	}
	@Transactional
	public void updateStatus(Integer id, StatusPedido pdto) {
		pedidorepository
			.findById(id)
			.map(pd -> {
				pd.setStatuspedido(pdto);
				return pedidorepository.save(pd);
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
	}
	
	
	
}
