package br.com.pedido.model.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="pedido", name="item_pedido")
@SequenceGenerator(schema="pedido", name="SEQ_ID_ITEM_PEDIDO", sequenceName="seq_id_item_pedido", 
initialValue=1, allocationSize=1)
public class ItemPedido {

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_ITEM_PEDIDO")
	@Id
	private Integer id;
	
	private Integer numero;
	
	@Column(name="valor_unitario")
	private BigDecimal valorUnitario;
	
	private Integer quantidade;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal;

	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	@ManyToOne(targetEntity=Pedido.class)
	@JoinColumn(name="id_pedido'")
	private Pedido pedido;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
