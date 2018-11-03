package br.com.pedido.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.pedido.model.bean.Produto;

@Service
public interface ProdutoService extends JpaRepository<Produto, Integer>{

}
