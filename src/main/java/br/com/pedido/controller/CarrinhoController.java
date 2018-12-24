package br.com.pedido.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pedido.model.bean.Pedido;
import br.com.pedido.model.service.PedidoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	public String vaiProCarrinho(@RequestParam List<ItemVO> itensVO) {
		Pedido pedido = this.pedidoService.grava(itensVO);
		
		Map<String,Object> retorno = new HashMap<>();
		retorno.put("pedido", pedido);
		return "/carrinho2.html";
	}

}
