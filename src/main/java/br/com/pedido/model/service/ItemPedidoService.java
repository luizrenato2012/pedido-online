package br.com.pedido.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedido.controller.ItemVO;
import br.com.pedido.controller.ProdutoVO;
import br.com.pedido.model.bean.ItemPedido;
import br.com.pedido.model.repository.ItemPedidoRepository;
import br.com.pedido.model.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public BigDecimal gravaItens(List<ItemVO> itensVO) {
		List<Integer> listaIds = buscaIdsItemPedido(itensVO);
		if(listaIds==null || listaIds.size()==0) {
			throw new RuntimeException("Lista de itens vazia");
		}
		
		this.itemRepository.deleteByIdIn(listaIds);
		
		List<ItemPedido> itens = this.converteListaVO(itensVO);
		this.itemRepository.save(itens);
		BigDecimal totalItens = itens.stream()
				.map(item -> item.getValorTotal())
				.reduce((valor1,valor2) -> valor1.add(valor2))
				.orElse(BigDecimal.ZERO);
		return totalItens;
	}
	
	@Transactional
	public BigDecimal gravaItem(ItemVO itemVO) {
		
		ItemPedido itemAtual = this.itemRepository.findOne(itemVO.getId());
		if (itemAtual==null) {
			this.itemRepository.save(itemAtual);
			itemVO.setId(itemAtual.getId());
		} else {
			itemAtual.setProduto(this.produtoRepository.findOne(itemVO.getIdProduto()));
			itemAtual.setNumero(itemVO.getNumero());
			itemAtual.setQuantidade(itemVO.getQuantidade());
			itemAtual.setValorUnitario(itemVO.getValorUnitario());
			itemAtual.setValorTotal(itemVO.getValorTotal());
		}
		this.itemRepository.save(itemAtual);
		
		List<ItemPedido> itens = this.itemRepository.findByIdPedido(itemVO.getIdPedido());
		
		BigDecimal totalItens = itens.stream()
				.map(item -> item.getValorTotal())
				.reduce((valor1,valor2) -> valor1.add(valor2))
				.orElse(BigDecimal.ZERO);
		return totalItens;
	}
	
	

	private List<Integer> buscaIdsItemPedido(List<ItemVO> itensVO) {
		List<Integer> listaIds = itensVO
					.stream()
					.map(item -> item.getId())
					.collect(Collectors.toList());
		return listaIds;
	}

	private List<ItemPedido> converteListaVO(List<ItemVO> itensVO) {
		List<ItemPedido> itens = itensVO.stream()
					.map(item -> this.converteVO(item) )
					.collect(Collectors.toList());
		return itens;
	}
	
	private ItemPedido converteVO(ItemVO itemVO) {
		ItemPedido item = new ItemPedido();
		item.setProduto(this.produtoRepository.findOne(itemVO.getId()));
//		item.setNumero(numero); TODO numero deve retornado pela VIEW oua query retornar
		item.setValorUnitario(itemVO.getValorUnitario());
		item.setQuantidade(itemVO.getQuantidade());
		item.setValorTotal(itemVO.getValorTotal());
		return item;
	}
	
	public ItemPedido save(ItemPedido item) {
		return this.itemRepository.save(item);
	}
	
	public List<ProdutoVO> listaItensIniciais() {
		return this.itemRepository.listaInicio();
	}
}
