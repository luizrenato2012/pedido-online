package br.com.pedido.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedido.model.bean.Produto;

@Repository
public interface ProdutoService extends JpaRepository<Produto, Integer>{

}
