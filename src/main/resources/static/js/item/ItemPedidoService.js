class ItemPedidoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
		this.produtosVO = [];
	}
	
	listaItens() {
		let urlListaItens = "http://localhost:8080/api/itens/inicio";
		return new Promise ( (resolve, reject) => {
			this.httpHelper.get(urlListaItens).then (
					sucesso => {
					     this.produtosVO = sucesso.itens;
						resolve(sucesso);
					},
					error => {
						reject("Erro ao listar produtos " + error);
					}
				);
			
		});
	}
	
	totalizaItens() {
		let urlGrava = "http://localhost:8080/api/itens/total";
		let itensAnterados = this.getItensAlterados();
		
		if (itensAnterados===undefined || itensAnterados.length==0) {
			return new Promisse( (resolve, reject) => {
				reject("Lista vazia");
			});
		}
		
		return new Promise( (resolve, reject) => {
			this.httpHelper.post(urlGrava, itensAnterados).then(
					sucesso => {
						console.log('resultado gravaItens: ' + sucesso);
						// TODO tratar lista de produtos VO pra que tenham os ID vindos da lista retornada pelo servidor
//						this.trataItensVO(sucesso.itens);
						resolve(sucesso); 
					},
					erro => {
						reject("Erro ao gravar itens:\n" + erro.status + " - " + erro.error);
					}
			);
		});
	}
	
	/** itera por todos os itensVO e seta idItem daqueles que tem o mesmo idProduto */
	trataItensVO(itensVO) {
		//lista dos idProduto da lista de produtos da tela
		let idsProduto = this.produtosVO.map (item => item.idProduto);
		let item = null;
		let produto = null;
		let produtoVOs = null;
		//para cada item
		for( var i = 0 ; i < itensVO.length; i++ ) {
			item = itensVO[i];
			console.log(`item ${item.id}` );
//			// se id produto do item atual estiver na lista de id´s de produto
			if( idsProduto.includes (item.idProduto)){
				console.log(`id Produto ${item.idProduto}`)
//				//busca o produtoVO da tela
				produtoVOs = this.produtosVO.filter(produto => produto.idProduto== item.idProduto);
//				//seta idItem e valor total
				produto = produtoVOs[0];
				produto.idItem = item.id;
				produtoVOs[0].valorTotal = item.valorTotal;
			}
		}
		console.log('Fim trataItensVO');
	}
	
	getItemSelecionado(idProduto) {
		return this.produtosVO.filter( item => item.idProduto==idProduto)[0];
	}
	
	/** itens que tiveram suas quantidade e valor total alterados*/
	getItensAlterados() {
		let alterados = this.produtosVO.filter(item => ( item.valorTotal != undefined && item.valorTotal != 0  ||
				(item.idItem!= undefined ||  item.idItem!=null) )); 
		let retorno = alterados.map( (item) => { 
			return { 
				id: item.idItem,
				idProduto : item.idProduto,
				numero: item.numero,
				valorUnitario: item.valorUnitario, 
				quantidade: item.quantidade, 
				valorTotal: item.valorTotal
			}  
		});
		return retorno;
	}
	
	vaiProCarrinho() {
		let alterados = getItensAlterados();
		let urlGravaCarrinho = "http://localhost:8080/api/pedido";
		let itensAnterados = this.getItensAlterados();
		
		if (itensAnterados===undefined || itensAnterados.length==0) {
			return new Promisse( (resolve, reject) => {
				reject("Lista vazia");
			});
		}
		
		return new Promise( (resolve, reject) => {
			this.httpHelper.post(urlGrava, itensAnterados).then(
					sucesso => {
						console.log('resultado gravaItens: ' + sucesso);
						// TODO tratar lista de produtos VO pra que tenham os ID vindos da lista retornada pelo servidor
						this.trataItensVO(sucesso.itens);
						this.teste();
						resolve(sucesso); 
					},
					erro => {
						reject("Erro ao gravar itens:\n" + erro.status + " - " + erro.error);
					}
			);
		});
		
	}
	
	
}