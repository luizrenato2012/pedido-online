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

//	https://stackoverflow.com/questions/13012584/jpa-how-to-convert-a-native-query-result-set-to-pojo-class-collection/21487061
	@Query (
		value="select \r\n" + 
				"	it.id id_pedido,\r\n" + 
				"	row_number() over (order by descricao)  numero,\r\n" + 
				"	p.id  id_produto,\r\n" + 
				"	p.nome  nome,\r\n" + 
				"	p.descricao  descricao,\r\n" + 
				"	p.imagem  imagem,\r\n" + 
				"	p.preco valorUnitario  ,\r\n" + 
				"	case when  it.quantidade is not null then it.quantidade else 0 end  quantidade,\r\n" + 
				"	case when it.valor_total is not null then it.valor_total else 0 end  valor_total \r\n" + 
				"from pedido.produto p\r\n" + 
				"left join pedido.item_pedido it on it.id_produto=p.id \r\n" + 
				"order by p.nome\r\n",
				nativeQuery=true
	)
	public List<ProdutoVO> listaInicio();
}

