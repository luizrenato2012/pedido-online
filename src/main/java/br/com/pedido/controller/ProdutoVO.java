package br.com.pedido.controller;

import java.math.BigDecimal;

public class ProdutoVO {

	private int id;
	private int numero;
	private int idProduto;
	private String nome;
	private String descricao;
	private byte[] imagem;
	private BigDecimal valorUnitario;
	private Integer quantidade;
	private BigDecimal valorTotal;
	
	public ProdutoVO() {
		super();
	}
	
	public ProdutoVO(int id, int numero, int idProduto, String nome, String descricao, byte[] imagem,
			BigDecimal valorUnitario, Integer quantidade, BigDecimal valorTotal) {
		super();
		this.id = id;
		this.numero = numero;
		this.idProduto = idProduto;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
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
