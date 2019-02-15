package br.com.pedido.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.bean.ItemPedido;
import br.com.pedido.model.bean.Pedido;
import br.com.pedido.model.service.ItemPedidoService;
import br.com.pedido.model.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins="http://localhost:8080")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<Map<String,Object>> adicionaItens( @RequestBody List<ItemVO> itensVO) {
		Pedido pedido = this.pedidoService.grava(itensVO);
		
//		BigDecimal totalCarrinho = pedido.getValorTotal();
		Map<String,Object> retorno = new HashMap<>();
		retorno.put("idPedido", pedido.getId());
		return new ResponseEntity(retorno, HttpStatus.ACCEPTED);
	}

}
