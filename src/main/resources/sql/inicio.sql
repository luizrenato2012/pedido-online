CREATE TABLE pedido.produto
(
  id integer NOT NULL,
  codigo integer,
  nome character varying(50),
  descricao character varying(45),
  categoria character varying(40),
  visivel boolean,
  imagem bytea,
  preco numeric(8,2),
  quantidade_estoque numeric(8,2)
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
( nextval('pedido.seq_id_produto'), 100, ' Produto 1' , 'breve descricao sobre o produto 1','sem categoria', true)

insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 200, ' Produto 2' , 'breve descricao sobre o produto 2','sem categoria', true)


insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 300, ' Produto 3' , 'breve descricao sobre o produto 3', 'sem categoria', true)

insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 400, ' Produto 4' , 'breve descricao sobre o produto 4', 'sem categoria',true)

insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 500, ' Produto 5' , 'breve descricao sobre o produto 5', 'sem categoria',true)

insert into pedido.produto (id, codigo,nome, descricao, categoria,visivel) values
( nextval('pedido.seq_id_produto'), 600, ' Produto 6' , 'breve descricao sobre o produto 6', 'sem categoria',true)


CREATE TABLE pedido.pedido
(
    id integer NOT NULL,
    numero integer,
    data_hora timestamp without time zone,
    valor numeric(8,2),
    CONSTRAINT pedido_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE pedido.pedido
    OWNER to admin;

GRANT ALL ON TABLE pedido.pedido TO admin;

GRANT ALL ON TABLE pedido.pedido TO "user";


CREATE SEQUENCE pedido.seq_id_item_pedido
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE pedido.seq_id_item_pedido
  OWNER TO "user";
GRANT ALL ON TABLE pedido.seq_id_item_pedido TO "user";
GRANT ALL ON TABLE pedido.seq_id_item_pedido TO public;

-- Sequence: pedido.seq_id_pedido

-- DROP SEQUENCE pedido.seq_id_pedido;

CREATE SEQUENCE pedido.seq_id_pedido
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE pedido.seq_id_pedido
  OWNER TO "user";
GRANT ALL ON TABLE pedido.seq_id_pedido TO "user";
GRANT ALL ON TABLE pedido.seq_id_pedido TO public;

CREATE TABLE pedido.item_pedido
(
  id integer NOT NULL,
  id_produto integer,
  numero integer,
  valor_unitario numeric(8,2),
  quantidade integer,
  valor_total numeric(8,2),
  id_pedido ,
  id_situacao integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pedido.item_pedido
  OWNER TO "user";

/**  query de lista de itens */
select 
	0  as id,
	row_number() over (order by descricao) as numero,
	p.id as idProduto,
	p.nome,
	p.descricao,
	p.imagem,
	p.preco valorUnitario,
	case when  it.quantidade is not null then it.quantidade else 0 end as quantidade,
	case when it.valor_total is not null then it.valor_total else 0 end as valorTotal
from pedido.produto p
left join pedido.item_pedido it on it.id_produto=p.id 
order by p.descricao
