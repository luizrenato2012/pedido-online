class ProdutoController {
	
	constructor() {
		this.produtoService = new ProdutoService();
		this.listaProdutos();
	}
	
	listaProdutos() {
		const xhr = new XMLHttpRequest();
		xhr.open('GET', 'http://localhost:8080/api/produto');
		xhr.onreadystatechange = () => {
			if (xhr.readyState == 4 ) {
				if(xhr.status == 200) {
					console.log('Obtendo a lista de produtos');
					let lista = JSON.parse(xhr.responseText);
					console.log('lista: '+ lista);
				} else {
					console.log('xhr status:  '+ xhr.readyState );
					console.log(xhr.responseText);
				}
			} else {
				console.log('readState: '+ xhr.readyState);
			}
		}
		xhr.send();
	}
}