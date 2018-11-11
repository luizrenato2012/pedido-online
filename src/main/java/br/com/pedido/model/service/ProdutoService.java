package br.com.pedido.model.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.pedido.model.bean.Produto;
import br.com.pedido.model.repository.ProdutoRepository;

@Service
@Component
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<Map<Integer,Produto>> listaTodos() {
		List<Produto> lista = this.repository.findAll(new Sort("nome"));
		List<Map<Integer, Produto>> listaMap = new ArrayList<>();
		
		lista.forEach( produto ->  {
			Map<Integer,Produto> map = new LinkedHashMap<Integer, Produto>();
			map.put(produto.getId(), produto);
			listaMap.add(map);	
		});
		return listaMap;
	}
}
