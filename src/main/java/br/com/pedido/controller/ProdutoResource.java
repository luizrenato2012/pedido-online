package br.com.pedido.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/teste")
	public ResponseEntity<Map<String,String>> teste() {
		System.out.println(">>>teste");
		Map<String,String> mapResponse = new HashMap<>();
		mapResponse.put("codigo", "200");
		mapResponse.put("descricao", "Teste OK!");
		return ResponseEntity.status(HttpStatus.OK).body(mapResponse);
	}

}
