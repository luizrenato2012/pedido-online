package br.com.pedido.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.pedido.model.repository.PedidoRepository;
import br.com.pedido.model.repository.ProdutoRepository;
import br.com.pedido.model.service.ItemPedidoService;

@RestController
@RequestMapping("/api/itens")
public class ItemPedidoResource {
	
	@Autowired
	private ProdutoRepository produtoReposiory;
	
	@Autowired
	private ItemPedidoService itemService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	private List<ItemPedido> listaTemp;
	
	@PostConstruct
	private void init() {
		this.listaTemp = new ArrayList<>();
	}
	
	@GetMapping("/teste")
	public ResponseEntity<ItemPedido> teste() {
		Produto produto = this.produtoReposiory.findOne(1);
		//this.pedidoRepository.save(pedido);
		
		ItemPedido item = new ItemPedido();
	//	item.setPedido(pedido);
		item.setNumero(1);
		item.setProduto(produto);
		item.setQuantidade(1);
		item.setValorUnitario(produto.getPreco());
		item.setValorTotal(  item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
		this.itemService.save(item);
		
		//pedido.setItens(new ArrayList<>());
		//pedido.getItens().add(item);
		//pedido.setValorTotal(item.getValorTotal());
		//this.pedidoRepository.save(pedido);
		return ResponseEntity.ok(item);
	}
	
	@GetMapping("/inicio")
	public ResponseEntity<List<ProdutoVO>> listaTodos() {
		List<ProdutoVO> lista = this.itemService.listaItensIniciais();
		return new ResponseEntity<List<ProdutoVO>>(lista, HttpStatus.OK);
	}
	
	@PostMapping("/itens")
	//DESCONSIDERANDO O ID DO ITEM (DEVE-SE CHEGAR E TRATAR NA VIEW P/ SEPARAR ID DO ITEM E DO PRODUTO
	public ResponseEntity<Map<String,Object>> adicionaItens( @RequestBody List<ItemVO> itensVO) {
		List<ItemVO>itens = this.itemService.gravaItens(itensVO);
		BigDecimal totalCarrinho = this.itemService.totalizaItens(itensVO);
		Map<String,Object> retorno = new HashMap<>();
		retorno.put("itens", itens);
		retorno.put("totalCarrinho", totalCarrinho);
		return new ResponseEntity(retorno, HttpStatus.ACCEPTED);
	}
	
//	@PostMapping
//	public ResponseEntity<Map<String,Object>> adicionaItem( @RequestBody ItemVO itemVO) {
//		if (itemVO.getQuantidade()==0 && itemVO.getId()!=null) {
//			this.itemService.delete(itemVO.getId());
//			return new ResponseEntity(itemVO,HttpStatus.ACCEPTED);
//		}
//		
//		BigDecimal totalCarrinho = this.itemService.gravaItem(itemVO);
//		Map<String,Object> retorno = new HashMap<>();
//		retorno.put("item", itemVO);
//		retorno.put("totalCarrinho", totalCarrinho);
//	}
	
}
