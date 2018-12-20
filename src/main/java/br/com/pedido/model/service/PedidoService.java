package br.com.pedido.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pedido.model.bean.Pedido;
import br.com.pedido.model.repository.PedidoRepository;

public class PedidoService {
	
	@Autowired
	private PedidoRepository repositorio;
	
	private ItemPedidoService itemService;
	
	public Pedido grava(Pedido pedido) {
		return this.repositorio.save(pedido);
	}

}
