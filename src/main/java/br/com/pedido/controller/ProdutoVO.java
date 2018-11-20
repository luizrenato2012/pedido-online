package br.com.pedido.controller;

import java.math.BigDecimal;

public interface ProdutoVO {
	
	public Integer getIdPedido();
	public Integer getIdItem();
	public Integer getNumero();
	public Integer getIdProduto();
	public String getNome();
	public String getDescricao();
	public byte[] getImagem();
	public BigDecimal getValorUnitario();
	public Integer getQuantidade();
	public BigDecimal getValorTotal();

}
