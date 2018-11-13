package br.com.pedido.model.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="pedido", name="pedido")
@SequenceGenerator(schema="pedido", name="SEQ_ID_PEDIDO", sequenceName="seq_id_pedido", 
		initialValue=1, allocationSize=1)
public class Pedido {

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_PEDIDO")
	@Id
	private Integer id;
	
	private Integer numero;
	
	@Column(name="data_hora")
	private LocalDateTime dataHora;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal;

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

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
