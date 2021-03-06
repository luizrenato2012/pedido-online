package br.com.pedido.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.model.bean.Produto;
import br.com.pedido.model.repository.ProdutoRepository;
import br.com.pedido.model.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins="http://localhost:8080")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
		Produto produto = this.produtoRepository.findOne(id);
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> listaTodos() { 
		return ResponseEntity.ok(this.produtoRepository.findAll(new Sort("nome")));
	}
	
	@GetMapping("/grava")
	public ResponseEntity<List<Produto>> gravaImagens() {
		String PATH="/home/teste-user/git/pedido-online/src/main/resources/static/img";
		List<Produto> produtos = new ArrayList<>();
//		List<String> arquivos = Arrays.asList("vidro","vidro2","vidro3","vidro4","vidro5");
//		List<Integer> ids = Arrays.asList(5, 6, 7, 8, 9 );
		Map<Integer, String> map = new HashMap<>();
		map.put(5, "vidro");
		map.put(6, "vidro2");
		map.put(7, "vidro3");
		map.put(8, "vidro4");
		map.put(9, "vidro5");
		map.put(10, "vidro6");
		
		map.keySet().forEach( id -> {
			try {
				Path path = Paths.get(PATH+"/" + map.get(id)   + ".jpeg");
				byte[] bytes = Files.readAllBytes(path);
				
				Produto produto = this.produtoRepository.findOne(id);
				produto.setImagem(bytes);
				this.produtoRepository.save(produto);
				
				produtos.add(produto);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
			
		return ResponseEntity.ok(produtos);
	}
	
	@PostMapping
	public ResponseEntity<Produto> cria(@Valid @RequestBody Produto produto) {
		Produto produtoGravado = this.produtoRepository.save(produto);
		return new ResponseEntity(produtoGravado, HttpStatus.CREATED);
	}

}
