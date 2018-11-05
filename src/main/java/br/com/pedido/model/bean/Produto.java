package br.com.pedido.model.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="pedido", name="produto")
@SequenceGenerator(schema="pedido", name="SEQ_ID_PRODUTO", sequenceName="seq_id_produto", 
		initialValue=1, allocationSize=1)
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_PRODUTO")
	private Integer id;
	
	private Integer codigo;
	
	private String nome;
	
	private String descricao;
	
	private String categoria;
	
	private boolean visivel;
	
//	@Column
//	@Lob
//	private byte [] imagem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isVisivel() {
		return visivel;
	}

	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}

//	public byte[] getImagem() {
//		return imagem;
//	}
//
//	public void setImagem(byte[] imagem) {
//		this.imagem = imagem;
//	}
	
}
