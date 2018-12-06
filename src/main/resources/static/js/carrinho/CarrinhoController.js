class CarrinhoController {
	
	constructor() {
		this.carrinhoService = new CarrinhoService();
		this.totalCarrinhoTxt = document.querySelector("#txtTotalCarrinho");
	}
	
	iniciaItens() {
		this.carrinhoService.listaItensCarrinho().then (
				resposta => {
					this.template(resposta.itens);
					this.preencheValorTotalCarrinho(resposta.valorTotal);
				} , error => {
					alert('Erro ao pesquisar itens do carrinho!');
					console.error(error);
				}
		);
	}
	
	template(itens) {
		//TODO validar se realmene é necessario link p/ alterar as quantidades dos itens
		let content = 
			`<div class="table-wrapper-scroll-y2">  <!--scroll -->
           <div class="list-group">
				${itens.map ( item => 
					`<!-- <a href="#" class="list-group-item comando"  data-toggle="modal" data-target="#myModal" onclick="seleciona(${item.idProduto})"> -->
					<table>
						<tbody>
							<tr>
								<td id="cel2">  <font size = 1 >  ${item.numero}  </font>   </td>
								<td id="cel3"> <font size = 1 >  ${item.descricao}  </font> </td>
								<td id="cel3"> <font size = 1 color = "red" >  ${item.valorUnitario}  </font>  </td>
								<td id="cel3"> <font size = 1 color = "red" >  ${item.quantidade}  </font>  </td>
								<td id="cel3"> <font size = 1 color = "red" >  ${item.valorTotal}  </font>  </td>
								<td id="cel2"> 
                      				<font size = 1 > 
                        			<button class="btn" size="1"><img src="img/lixeira.png" alt="some text" width=20 height=20 ></button> 
                      			</font>
                    			</td>
							</tr>
						</tbody>
					</table>
				<!-- </a> -->
				
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