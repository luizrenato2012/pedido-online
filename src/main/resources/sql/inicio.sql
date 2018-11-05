CREATE TABLE pedido.produto
(
  id integer NOT NULL,
  codigo integer,
  nome character varying(50),
  descricao character varying(45),
  categoria character varying(40),
  visivel boolean,
  imagem bytea,
  CONSTRAINT pk_produto PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pedido.produto
  OWNER TO "user";
GRANT ALL ON TABLE pedido.produto TO "user";
GRANT SELECT, UPDATE, INSERT ON TABLE pedido.produto TO public;


insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 200, ' Produto 2' , 'breve descricao sobre o produto 2','sem categoria', true)


insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 300, ' Produto 3' , 'breve descricao sobre o produto 3', 'sem categoria', true)

insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 400, ' Produto 4' , 'breve descricao sobre o produto 4', 'sem categoria',true)

insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 500, ' Produto 5' , 'breve descricao sobre o produto 5', 'sem categoria',true)