class ProdutoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
	}
	
	listaProduto() {
		let urlListaProduto = "http://localhost:8080/api/produto";
		urlListaProduto.then (
			sucesso => {
				return sucesso;
			},
			error => {
				console.error("Erro ao listar produtos " + error);
			}
		);
	}
}