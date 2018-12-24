package br.com.pedido.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedido.controller.ItemVO;
import br.com.pedido.model.bean.ItemPedido;
import br.com.pedido.model.bean.Pedido;
import br.com.pedido.model.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repositorio;
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	public Pedido grava(Pedido pedido) {
		return this.repositorio.save(pedido);
	}
	
	@Transactional
	public Pedido grava(List<ItemVO> itensVO) {
		Pedido pedido = new Pedido();
		pedido.setDataHora(LocalDateTime.now());
		pedido = this.grava(pedido);
		
		List<ItemPedido> itens = itemPedidoService.converteListaVO(itensVO);
		
		pedido.setItens(itens);
		BigDecimal totalCarrinho = this.itemPedidoService.totalizaItens(itensVO);
		pedido.setValorTotal(totalCarrinho);
		for (ItemPedido item: itens) {
			item.setPedido(pedido);
		}
		this.grava(pedido);
		return pedido;
	}

}
