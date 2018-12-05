class CarrinhoController {
	
	
	iniciaItens() {
		this.itemPedidoService.listaItens().then (
				resposta => {
					this.template(resposta.itens);
					this.preencheValorTotalCarrinho(resposta.valorTotal);
				} , error => {
					console.error(error);
				}
		);
	}
	
	template() {
		let content = 
			`<div class="table-wrapper-scroll-y2">  <!--scroll -->
           <div class="list-group">
				${produtos.map ( item => 
					`<a href="#" class="list-group-item comando"  data-toggle="modal" data-target="#myModal" onclick="seleciona(${item.idProduto})">
			<input type="hidden" name="id_item" value="${item.idItem}"/>
					<table>
						<tbody>
							<tr>
								<td rowspan="3"><img src="data:image/png;base64, ${item.imagem}" alt="sem imagem"	width=50 height=40></td>
								<td><font size=2> ${item.nome} </font></td>
							</tr>
							<tr>
								<td><font size=1> ${item.descricao} </font></td>
							</tr>
							<tr>
								<td><font size=1 color="red"> ${item.valorUnitario} </font></td>
							</tr>
						</tbody>
					</table>
				</a> 
				
				`).join('')}
				</div>`;
		let divTable = document.querySelector('#tab_itens_carrinho');
		divTable.innerHTML = content;
	}
	
	preencheValorTotalCarrinho(valor) {
		console.log(`>>> total do carrinhos ${valor}`);
		this.totalCarrinhoTxt.innerHTML=valor.toLocaleString('pt-BR', {style:'currency', currency: 'BRL'}) ;
	}
}