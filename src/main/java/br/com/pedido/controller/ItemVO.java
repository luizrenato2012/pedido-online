package br.com.pedido.controller;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * VO com dados de itens que serao gravados ao incluir 
 * usado na troca de itens a serem gravados durante a gravacao de iteins individuais
 * @author Luiz Renato
 *
 */
public class ItemVO {
	
	@JsonInclude(content=Include.NON_NULL)
	private Integer id;
	
	@JsonInclude(content=Include.NON_NULL)
	private int idPedido;
	
	private BigDecimal valorUnitario;
	
	private int quantidade;
	
	@JsonInclude(content=Include.NON_NULL)
	private BigDecimal valorTotal;
	
	@JsonInclude(content=Include.NON_NULL)
	private Integer idProduto;
	
	@JsonInclude(content=Include.NON_NULL)
	private Integer numero;
	
	@JsonInclude(content=Include.NON_NULL)
	private boolean atual;
	
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
	
	public Integer getId() {
		return this.id;
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

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public boolean isAtual() {
		return atual;
	}

	public void setAtual(boolean atual) {
		this.atual = atual;
	}
	
	
}
