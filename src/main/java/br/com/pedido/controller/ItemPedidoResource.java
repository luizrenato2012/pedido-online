package br.com.pedido.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.bean.ItemPedido;
import br.com.pedido.model.bean.Produto;
import br.com.pedido.model.repository.ItemPedidoRepository;
import br.com.pedido.model.repository.PedidoRepository;
import br.com.pedido.model.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/itens")
public class ItemPedidoResource {
	
	@Autowired
	private ProdutoRepository produtoReposiory;
	
	@Autowired
	private ItemPedidoRepository itemRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	private List<ItemPedido> listaTemp;
	
	@PostConstruct
	private void init() {
		this.listaTemp = new ArrayList<>();
	}
	
	@GetMapping("/teste")
	public ResponseEntity<ItemPedido> teste() {
	//	Pedido pedido = new Pedido();
	//	pedido.setNumero(1);
	//	pedido.setDataHora(LocalDateTime.now());
		
		Produto produto = this.produtoReposiory.findOne(1);
		
		
		//this.pedidoRepository.save(pedido);
		
		ItemPedido item = new ItemPedido();
	//	item.setPedido(pedido);
		item.setNumero(1);
		item.setProduto(produto);
		item.setQuantidade(1);
		item.setValorUnitario(produto.getPreco());
		item.setValorTotal(  item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
		this.itemRepository.save(item);
		
		//pedido.setItens(new ArrayList<>());
		//pedido.getItens().add(item);
		//pedido.setValorTotal(item.getValorTotal());
		//this.pedidoRepository.save(pedido);
		return ResponseEntity.ok(item);
	}
	
	@PostMapping
	//DESCONSIDERANDO O ID DO ITEM (DEVE-SE CHEGAR E TRATAR NA VIEW P/ SEPARAR ID DO ITEM E DO PRODUTO
	public ResponseEntity<ItemVO> adiciona( @RequestBody ItemVO itemVO) {
		ItemPedido item = this.itemRepository.findOne(itemVO.getId());
		HttpStatus status = null;
		if (item ==null) { // TODO DEVE CRIAR um PEDIDO NOVO
			item = new ItemPedido();
			status = HttpStatus.CREATED;
		}
		status = HttpStatus.ACCEPTED;
		// item.setIdPedido() TODO DEVE CRIAR UM PEDIDO NOVO NO CASO DE NAO HAVER PEDIDO
		//			item.setNumero(numero); TODO receber do item da View
		item.setQuantidade(itemVO.getQuantidade());
		item.setValorUnitario(itemVO.getValorUnitario());
		item.setValorTotal(itemVO.getValorTotal());
		
		this.itemRepository.save(item);
		
		return new ResponseEntity(item, status);
	}
}
