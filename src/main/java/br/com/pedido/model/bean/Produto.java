package br.com.pedido.model.bean;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="pedido", name="pedido")
@SequenceGenerator(schema="pedido", name="seq.id_produto", sequenceName="SEQ_ID_PRODUTO", 
		initialValue=1, allocationSize=1)
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_PRODUTO")
	private Integer id;
	
	@Column
	private Integer codigo;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String categoria;
	
	@Column
	private boolean visivel;
	
	@Column
	@Lob
	private byte [] imagem;

}
