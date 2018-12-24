package br.com.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.service.ItemPedidoService;
import br.com.pedido.model.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins="http://localhost:8080")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ItemPedidoService itemService;
	

}
