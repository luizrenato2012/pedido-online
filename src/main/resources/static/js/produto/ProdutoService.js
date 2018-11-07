class ProdutoService {
	
	constructor() {
		this.httpHelper = new HttpHelper();
	}
	
	listaProduto() {
		let urlListaProduto = "http://localhost:8080/api/produtos";
		return this.httpHelper.get(urlListaProduto).then (
			sucesso => {
				return sucesso;
			},
			error => {
				throw new Error("Erro ao listar produtos " + error);
			}
		);
	}
}