package br.com.pedido.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
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
	
	private Logger log = Logger.getLogger(ItemPedidoService.class);
	
	@Transactional
	public List<ItemVO> gravaItens(List<ItemVO> itensVO) {
		this.excluiItensExistentes(itensVO);
		
		List<ItemPedido> itens = this.converteListaVO(itensVO);
		this.itemRepository.save(itens);
		itensVO = this.converteListaItens(itens);
		return itensVO;
	}
	
	public BigDecimal totalizaItens(List<ItemVO> itens) {
		BigDecimal totalItens = itens.stream()
				.map(item -> item.getValorTotal())
				.reduce((valor1,valor2) -> valor1.add(valor2))
				.orElse(BigDecimal.ZERO);
		return totalItens;
	}
	
	public BigDecimal totalizaProdutos(List<ProdutoVO> listaProdutosVO) {
		return listaProdutosVO.stream()
			.map(produtoVO -> produtoVO.getValorTotal())
			.reduce( (valor1, valor2) -> valor1.add(valor2))
			.orElse(BigDecimal.ZERO);
	}

	private void excluiItensExistentes(List<ItemVO> itensVO) {
		List<Integer> listaIds = this.buscaIdsItemPedido(itensVO);
		if(listaIds==null || listaIds.size()==0) {
			this.log.info("Lista de itens vazia");
			return;
		}
		this.itemRepository.deleteByIdIn(listaIds);
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
					.filter(id -> id!=null && id != 0)
					.collect(Collectors.toList());
		return listaIds;
	}

	private List<ItemPedido> converteListaVO(List<ItemVO> itensVO) {
		List<ItemPedido> itens = itensVO.stream()
					.filter(item -> item.getQuantidade() > 0)
					.map(item -> this.converteVO(item) )
					.collect(Collectors.toList());
		return itens;
	}
	
	private ItemPedido converteVO(ItemVO itemVO) {
		ItemPedido item = new ItemPedido();
		item.setProduto(this.produtoRepository.findOne(itemVO.getIdProduto()));
		item.setNumero(itemVO.getNumero()); // TODO numero deve retornado pela VIEW oua query retornar
		item.setValorUnitario(itemVO.getValorUnitario()); //TODO setar Pedido
		item.setQuantidade(itemVO.getQuantidade());
		item.setValorTotal(itemVO.getValorTotal());
		return item;
	}
	
	private List<ItemVO> converteListaItens(List<ItemPedido> itens) {
		return itens.stream()
				.map(item -> this.converteItem(item))
				.collect(Collectors.toList());
	}
	
	private ItemVO converteItem(ItemPedido item) {
		ItemVO itemVO = new ItemVO();
		itemVO.setId(item.getId());
		itemVO.setIdProduto(item.getProduto().getId());
		itemVO.setNumero(item.getNumero());
		itemVO.setQuantidade(item.getQuantidade());
		itemVO.setValorTotal(item.getValorTotal());
		itemVO.setValorUnitario(item.getValorUnitario());
		return itemVO;
	}
	public ItemPedido save(ItemPedido item) {
		return this.itemRepository.save(item);
	}
	
	public List<ProdutoVO> listaItensIniciais() {
		return this.itemRepository.listaItensInicio();
	}
	
	public List<ProdutoVO> listaItensCarrinho() {
		return this.itemRepository.listaItensCarrinho();
	}

	public void delete(Integer id) {
		this.itemRepository.delete(id);
	}

}
