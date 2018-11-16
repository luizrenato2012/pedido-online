class ProdutoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
		this.itens = [];
	}
	
	listaProduto() {
		let urlListaProduto = "http://localhost:8080/api/produtos";
		return new Promise ( (resolve, reject) => {
			this.httpHelper.get(urlListaProduto).then (
					sucesso => {
						//this.mapItens = new Map();
						//sucesso.forEach(item => this.mapItens.set(item.id, item) );
					     this.itens = sucesso;
						resolve( this.itens);
					},
					error => {
						reject("Erro ao listar produtos " + error);
					}
				);
			
		});
	}
	
	gravaItens() {
		let urlGrava = "http://localhost:8080/api/itens";
		let produtos = this.getItensAlterados();
		return new Promise( (resolve, reject) => {
			this.httpHelper.post(urlGrava, produtos).then(
					sucesso => {
						console.log('resultado graItens: ' + sucesso);
						resolve(resultado);
					},
					erro => {
						reject("Erro ao gravar itens:\n" + erro.status + " - " + erro.error);
					}
			);
		});
	}
	
	getItemSelecionado(id) {
		return this.itens.filter( item => item.id==id)[0];
	}
	
	/** itens que tiveram suas quantidade e valor total alterados*/
	getItensAlterados() {
		let alterados = this.itens.filter(item => ( item.valorTotal != undefined && item.valorTotal != 0 )); 
		let retorno = alterados.map( function (item) { 
			return { 
				id: item.id, 
				valorUnitario: item.preco, 
				quantidade: item.quantidade, 
				valorTotal: item.valorTotal
			}  
		});
		return retorno;
	}
	
	
}