package br.com.pedido.model.repository;

import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pedido.controller.ProdutoVO;
import br.com.pedido.controller.ProdutoVOTeste;
import br.com.pedido.model.bean.ItemPedido;

@Repository
//https://stackoverflow.com/questions/29082749/spring-data-jpa-map-the-native-query-result-to-non-entity-pojo
//@SqlResultSetMapping(
//	name="produtoVOMapping",
//	classes= {
//		@ConstructorResult(
//			targetClass=ProdutoVO.class,
//			columns = {
//				@ColumnResult(name="id"),
//				@ColumnResult(name="numero"),
//				@ColumnResult(name="id_produto"),
//				@ColumnResult(name="nome"),
//				@ColumnResult(name="descricao"),
//				@ColumnResult(name="image"),
//				@ColumnResult(name="valor_unitario"),
//				@ColumnResult(name="quantidade"),
//				@ColumnResult(name="valor_total"),
//			}
//		)
//	}
//)
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
				"order by p.descricao\r\n",
				nativeQuery=true
	)
	public List<ProdutoVOTeste> listaInicio();
}

