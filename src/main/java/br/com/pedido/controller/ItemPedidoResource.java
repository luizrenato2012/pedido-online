package br.com.pedido.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.bean.ItemPedido;
import br.com.pedido.model.bean.Pedido;
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
	
	@GetMapping("/teste")
	public ResponseEntity<ItemPedido> teste() {
		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setDataHora(LocalDateTime.now());
		
		Produto produto = this.produtoReposiory.findOne(1);
		
		
		this.pedidoRepository.save(pedido);
		
		ItemPedido item = new ItemPedido();
		item.setPedido(pedido);
		item.setNumero(1);
		item.setProduto(produto);
		item.setQuantidade(1);
		item.setValorUnitario(produto.getPreco());
		item.setValorTotal(  item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
		this.itemRepository.save(item);
		
		pedido.setItens(new ArrayList<>());
		pedido.getItens().add(item);
		pedido.setValorTotal(item.getValorTotal());
		this.pedidoRepository.save(pedido);
		return ResponseEntity.ok(item);
	}
}
