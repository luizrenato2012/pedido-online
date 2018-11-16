package br.com.pedido.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pedido.controller.ProdutoVO;
import br.com.pedido.model.bean.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
	
	public void deleteByIdIn(List<Integer> lista);

	@Query (
		value="select \r\n" + 
				"	0  as id,\r\n" + 
				"	row_number() over (order by descricao) as numero,\r\n" + 
				"	p.id as idProduto,\r\n" + 
				"	p.nome,\r\n" + 
				"	p.descricao,\r\n" + 
				"	p.imagem,\r\n" + 
				"	p.preco valorUnitario,\r\n" + 
				"	case when  it.quantidade is not null then it.quantidade else 0 end as quantidade,\r\n" + 
				"	case when it.valor_total is not null then it.valor_total else 0 end as valorTotal\r\n" + 
				"from pedido.produto p\r\n" + 
				"left join pedido.item_pedido it on it.id_produto=p.id \r\n" + 
				"order by p.descricao\r\n",
		nativeQuery=true		
		
	)
	public List<ProdutoVO> listaInicio();
}

