class ItemPedidoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
		this.itens = [];
	}
	
	listaItens() {
		let urlListaItens = "http://localhost:8080/api/itens/inicio";
		return new Promise ( (resolve, reject) => {
			this.httpHelper.get(urlListaItens).then (
					sucesso => {
						//this.mapItens = new Map();
					//	console.log('listaPedido ' + JSON.stringify(sucesso));
					     this.itens = sucesso;
						resolve(this.itens);
					},
					error => {
						reject("Erro ao listar produtos " + error);
					}
				);
			
		});
	}
	
	gravaItens() {
		let urlGrava = "http://localhost:8080/api/itens/itens";
		let produtos = this.getItensAlterados();
		if (produtos===undefined || produtos.length==0) {
			return new Promisse( (resolve, reject) => {
				reject("Lista vazia");
			});
		}
		
		return new Promise( (resolve, reject) => {
			this.httpHelper.post(urlGrava, produtos).then(
					sucesso => {
						console.log('resultado gravaItens: ' + sucesso);
						resolve(sucesso);
					},
					erro => {
						reject("Erro ao gravar itens:\n" + erro.status + " - " + erro.error);
					}
			);
		});
	}
	
	
	gravaItem(item) {
		let urlGravaItem = "http://localhost:8080/api/itens";
		return new Promise( (resolve, reject) => {
			this.httpHelper.post(urlGravaItem, item).then(
					sucesso => {
						//console.log('resultado gravaItem: ' + sucesso);
						resolve(sucesso);
					},
					erro => {
						reject("Erro ao gravar item:\n" + erro.status + " - " + erro.error);
					}
			);
		});
	}
	
	getItemSelecionado(id) {
		return this.itens.filter( item => item.idItem==id)[0];
	}
	
	/** itens que tiveram suas quantidade e valor total alterados*/
	getItensAlterados() {
		let alterados = this.itens.filter(item => ( item.valorTotal != undefined && item.valorTotal != 0 )); 
		let retorno = alterados.map( function (item) { 
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