class ProdutoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
	}
	
	listaProduto() {
		let urlListaProduto = "http://localhost:8080/api/produtos";
		return new Promise ( (resolve, reject) => {
			this.httpHelper.get(urlListaProduto).then (
					sucesso => {
						this.mapItens = new Map();
						sucesso.forEach(item => this.mapItens.set(item.id, item) );
						resolve( sucesso);
					},
					error => {
						reject("Erro ao listar produtos " + error);
					}
				);
			
		});
	}
	
	gravaItem(item) {
		let urlGrava = "http://localhost:8080/api/itens";
		return new Promise( (resolve, reject) => {
			
		});
	}
	
	getItemSelecionado(id) {
		return this.mapItens.get(id);
	}
	
	getMap() {
		return this.mapItens;
	}
	
	
}