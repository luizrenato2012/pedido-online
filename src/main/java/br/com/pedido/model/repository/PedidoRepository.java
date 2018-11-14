package br.com.pedido.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedido.model.bean.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
