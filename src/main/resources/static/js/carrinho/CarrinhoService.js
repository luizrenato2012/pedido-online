class CarrinhoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
		this.produtosVO = [];
	}
	
	listaItensCarrinho() {
		let urlListaItens = "http://localhost:8080/api/itens/carrinho";
		return new Promise ( (resolve, reject) => {
			this.httpHelper.get(urlListaItens).then (
					sucesso => {
					     this.produtosVO = sucesso.itens;
						resolve(sucesso);
					},
					error => {
						reject("Erro ao listar itens do carrinho " + error);
					}
				);
		});
	}
	
	getItemSelecionado(idProduto) {
		return this.produtosVO.filter( item => item.idProduto==idProduto)[0];
	}
	
}