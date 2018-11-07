class ProdutoController {
	
	constructor() {
		this.produtoService = new ProdutoService();
	}
	
	listaProdutos() {
		this.produtoService.listaProduto().then (
			produtos => {
				console.log(produtos);
				
			} , error => {
				console.error(error);
			}
		);
	}
}