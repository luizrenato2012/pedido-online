package br.com.pedido.controller;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ItemVO {
	
	public ItemVO() {
		super();
	}
	
	public ItemVO(int id, BigDecimal valorUnitario, int quantidade, BigDecimal valorTotal) {
		super();
		this.id = id;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}
	
	
	private int id;
	@JsonInclude(content=Include.NON_NULL)
	private int idPedido;
	
	private BigDecimal valorUnitario;
	
	private int quantidade;
	
	private BigDecimal valorTotal;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return  this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	

}
