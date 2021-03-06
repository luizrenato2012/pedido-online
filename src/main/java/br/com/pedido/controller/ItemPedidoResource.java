package br.com.pedido.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.repository.ProdutoRepository;
import br.com.pedido.model.service.ItemPedidoService;

@RestController
@RequestMapping("/api/itens")
@CrossOrigin
public class ItemPedidoResource {
	
	@Autowired
	private ProdutoRepository produtoReposiory;
	
	@Autowired
	private ItemPedidoService itemService;
	
	private Logger log = Logger.getLogger(this.getClass().getSimpleName());
	
//	@GetMapping("/teste")
//	public ResponseEntity<ItemPedido> teste() {
//		Produto produto = this.produtoReposiory.findOne(1);
//		
//		ItemPedido item = new ItemPedido();
//		item.setNumero(1);
//		item.setProduto(produto);
//		item.setQuantidade(1);
//		item.setValorUnitario(produto.getPreco());
//		item.setValorTotal(  item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
//		this.itemService.save(item);
//		
//		return ResponseEntity.ok(item);
//	}
	
	@GetMapping("/inicio")
	public ResponseEntity<Map<String,Object>> listaTodos() {
		List<ProdutoVO> listaProdutosVO = this.itemService.listaItensIniciais();
		BigDecimal totalProdutos = this.itemService.totalizaProdutos(listaProdutosVO);
		Map<String,Object> map = new HashMap<>();
		map.put("produtos", listaProdutosVO);
		map.put("valorTotal", totalProdutos);
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	@GetMapping("/carrinho")
	public ResponseEntity<Map<String,Object>> listaProdutosCarrinho() {
		List<ProdutoVO> listaProdutosVO = this.itemService.listaItensCarrinho();
		BigDecimal totalProdutos = this.itemService.totalizaProdutos(listaProdutosVO);
		Map<String,Object> map = new HashMap<>();
		map.put("itens", listaProdutosVO);
		map.put("valorTotal", totalProdutos);
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/total_item")
	/** DESCONSIDERANDO O ID DO ITEM (DEVE-SE CHEGAR E TRATAR NA VIEW P/ SEPARAR ID DO ITEM E DO PRODUTO */
	public ResponseEntity<Map<String,Object>> totaliza( @RequestBody List<ItemVO> itensVO) {
		BigDecimal totalItem =this.itemService.totalizaItemAtual(itensVO);
		BigDecimal totalCarrinho = this.itemService.totalizaItens(itensVO);
		Map<String,Object> retorno = new HashMap<>();
		retorno.put("valorTotalItem", totalItem);
		retorno.put("valorTotal", totalCarrinho);
		return new ResponseEntity(retorno, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping
	@RequestMapping(value="/{id}")
	public ResponseEntity<Map<String,Object>> exclui( @PathVariable Integer id) {
		this.log.info("Excluindo item ["+ id +"]");
		this.itemService.delete(id);
		BigDecimal totalCarrinho = this.itemService.totalizaItens();
		List<ProdutoVO> itens = this.itemService.listaItensCarrinho();
		Map<String,Object> retorno = new HashMap<>();
		retorno.put("itens", itens);
		retorno.put("valorCarrinho", totalCarrinho);
		return new ResponseEntity(retorno, HttpStatus.ACCEPTED);
	}
	
	
}
