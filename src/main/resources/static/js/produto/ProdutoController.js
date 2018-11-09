class ProdutoController {
	
	constructor() {
		this.produtoService = new ProdutoService();
	}
	
	listaProdutos() {
		this.produtoService.listaProduto().then (
				produtos => {
					console.log(produtos);
					this.template(produtos);
				} , error => {
					console.error(error);
				}

			produtos => {
				console.log(produtos);
				
			} , error => {
				console.error(error);
			}

		);
	}
	
	template(produtos) {
		let content = 
			`<div class="list-group">
				${produtos.map ( produto => 
					`<a href="#" class="list-group-item" data-toggle="modal" data-target="#myModal">
					<table>
						<tbody>
							<tr>
								<td rowspan="3"><img src="data:image/png;base64, ${produto.imagem}" alt="sem imagem"	width=50 height=40></td>
								<td><font size=2> ${produto.nome} </font></td>
							</tr>
							<tr>
								<td><font size=1> ${produto.descricao} </font></td>
							</tr>
							<tr>
								<td><font size=1 color="red"> ${produto.preco} </font></td>
							</tr>
						</tbody>
					</table>
				</a> 
				
				`).join('')};
				</div>`;
		let divTable = document.querySelector('#table_produtos');
		divTable.innerHTML = content;
	}
}