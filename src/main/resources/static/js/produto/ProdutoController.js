class ProdutoController {
	
	constructor() {
		this.produtoService = new ProdutoService();
	}
	
	listaProdutos() {
		this.produtoService.listaProduto().then (
				produtos => {
					this.template(produtos);
				} , error => {
					console.error(error);
				}
		);
	}
	
	template(produtos) {
		let content = 
			`<div class="list-group">
				${produtos.map ( produto => 
					`<a href="#" class="list-group-item comando"  data-toggle="modal" data-target="#myModal" onclick="seleciona(${produto.id})">
			<input type="hidden" name="id_item" value="${produto.id}"/>
					<table>
						<tbody>
							<tr>
								<td rowspan="3"><img src="data:image/png;base64, ${produto.imagem}" alt="sem imagem no momento"	width=50 height=40></td>
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
	
	preencheSelecao (id) {
		let itemSelecionado = this.produtoService.getItemSelecionado(id);
		console.log('preenche Selecao ' + id);
		document.querySelector("#lblNome").innerHTML=itemSelecionado.nome;
		document.querySelector("#lblDescricao").innerText=itemSelecionado.descricao;
		document.querySelector("#lblPreco").innerText= 'R$ ' + parseFloat(itemSelecionado.preco);
		document.querySelector("#imgItem").src="data:image/png;base64, "+ itemSelecionado.imagem;
		let quant = document.querySelector("#quant").value;
		document.querySelector("#lblValorTotal").innerText= 'R$ ' + parseFloat(itemSelecionado.preco);
	}
	
	/*initLinks() {
		let links = document.querySelectorAll("a.comando");
		
		console.log('total de itens '+ links.length);
		
		links.forEach ( link => {
			console.log('iniciando links');
			link.addEventListener('click', () => {
				console.log('>>> Link clicado!!!');
			});
		});
	}*/
}