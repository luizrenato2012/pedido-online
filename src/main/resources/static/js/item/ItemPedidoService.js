class ItemPedidoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
		this.produtosVO = [];
	}
	
	listaItens() {
		let urlListaItens = "http://localhost:8080/api/itens/inicio";
		return new Promise ( (resolve, reject) => {
			let r1  =resolve;
			let r2 = reject;
			this.httpHelper.get(urlListaItens).then (
					sucesso => {
					//	console.log('listaPedido ' + JSON.stringify(sucesso));
					     this.produtosVO = sucesso;
						resolve(this.produtosVO);
					},
					error => {
						reject("Erro ao listar produtos " + error);
					}
				);
			
		});
	}
	
	gravaItens() {
		let urlGrava = "http://localhost:8080/api/itens";
		let itensAnterados = this.getItensAlterados();
		
		if (itensAnterados===undefined || itensAnterados.length==0) {
			return new Promisse( (resolve, reject) => {
				reject("Lista vazia");
			});
		}
		
		return new Promise( (resolve, reject) => {
			let resolve2 = resolve;
			let reject2 = reject;
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
	
	teste () {
		console.log('Teste antes de resolve');
	}
	
	/** itera por todos os itensVO e seta idItem daqueles que tem o mesmo idProduto */
	trataItensVO(itensVO) {
		//lista dos idProduto da lista de produtos da tela
		let idsProduto = this.produtosVO.map (item => item.idProduto);
		
		//para cada item
//		itensVO.forEach( item => {
//			// se id produto do item atual estiver na lista de id´s de produto
//			if( idsProduto.includes (item.idProduto)){
//				//busca o produtoVO da tela
//				let produtoVO = this.produtosVO.filter(produto => produto.idProduto== item.idProduto);
//				//seta idItem e valor total
//				produtoVo.idItem = item.id;
//				produtoVo.valorTotal = item.valorTotal;
//			}
//		});
		console.log('Fim trataItensVO');
	}
	
	
//	gravaItem(item) {
//		let urlGravaItem = "http://localhost:8080/api/itens";
//		return new Promise( (resolve, reject) => {
//			this.httpHelper.post(urlGravaItem, item).then(
//					sucesso => {
//						//console.log('resultado gravaItem: ' + sucesso);
//						resolve(sucesso);
//					},
//					erro => {
//						reject("Erro ao gravar item:\n" + erro.status + " - " + erro.error);
//					}
//			);
//		});
//	}
	
	getItemSelecionado(idProduto) {
		return this.produtosVO.filter( item => item.idProduto==idProduto)[0];
	}
	
	/** itens que tiveram suas quantidade e valor total alterados*/
	getItensAlterados() {
		let alterados = this.produtosVO.filter(item => ( item.valorTotal != undefined && item.valorTotal != 0 )); 
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
	
	
}