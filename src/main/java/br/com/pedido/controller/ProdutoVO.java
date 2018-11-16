package br.com.pedido.controller;

import java.math.BigDecimal;

public interface ProdutoVO {
	
	public int getIdItem();
	public int getNumero();
	public int getIdProduto();
	public String getNome();
	public String getDescricao();
	public byte[] getImagem();
	public BigDecimal getValorUnitario();
	public int getQuantidade();
	public BigDecimal getValorTotal();

}
