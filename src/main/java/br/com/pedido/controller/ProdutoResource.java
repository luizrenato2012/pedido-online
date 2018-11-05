package br.com.pedido.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.bean.Produto;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> busca(@PathVariable Integer id) {
		Produto produto = this.service.findOne(id);
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listaTodos() {
		List<Produto> produtos = this.service.findAll();
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/grava")
	public ResponseEntity<Produto> gravaImagens() {
		String PATH="C:\\Users\\luiz\\git\\pedido-online\\src\\main\\resources\\static\\img";
//		List<String> arquivos = Arrays.asList("vidro","vidro2","vidro3","vidro4","vidro5");
		Path path = Paths.get(PATH+"\\" + "vidro5.jpeg");
		try {
			byte[] bytes = Files.readAllBytes(path);
			Produto p = this.service.findOne(10);
			p.setImagem(bytes);
			this.service.save(p);
			return ResponseEntity.ok(p);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
