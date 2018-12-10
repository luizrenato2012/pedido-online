class CarrinhoController {
	
	constructor() {
		this.carrinhoService = new CarrinhoService();
		this.totalCarrinhoTxt = document.querySelector("#txtTotalCarrinho");
		this.itemSelecionado = {};
		
		this.precoTxt = document.querySelector("#lblPreco");
		this.quantidadeTxt = document.querySelector("#quant");
		this.valorItemTxt = document.querySelector("#lblValorTotal");
		
		this.nomeLbl = document.querySelector("#lblNome");
		this.descricaoLbl=  document.querySelector("#lblDescricao");
		this.imagemImg=  document.querySelector("#imgItem");
		
		this.exibeDialog = true;
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
					`<a href="#" class="list-group-item comando"  onclick="seleciona(${item.idProduto})"> 
					<table>
						<tbody>
							<tr>
								<td id="cel2"> <font size = 1 >  ${item.numero}  </font>   </td>
								<td id="cel3"> <font size = 1 >  ${item.descricao}  </font> </td>
								<td id="cel3"> <font size = 1 color = "red">  ${item.valorTotal}  </font>  </td>
								<td id="cel2"> 
                      				<font size = 1 > 
                        			<button class="btn" size="1" onclick="exibeExclusao(${item.idProduto})"><img src="img/lixeira.png" alt="some text" width=20 height=20></button> 
                      			</font>
                    			</td>
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
	
	seleciona (idProduto) {
		if (this.exibeDialog==false) {
			return;
		}
		this.itemSelecionado = this.carrinhoService.getItemSelecionado(idProduto); //alterarado campo de chave p/ id produto
//		console.log('ItemPedidoController preenche/selecao ' + this.itemSelecionado);
		this.nomeLbl.innerHTML=this.itemSelecionado.nome;
		this.descricaoLbl.innerText=this.itemSelecionado.descricao;
		this.imagemImg.src="data:image/png;base64, "+ this.itemSelecionado.imagem;
		
		this.precoTxt.innerText= 'R$ ' + parseFloat(this.itemSelecionado.valorUnitario);
		this.quantidadeTxt.value = this.itemSelecionado.quantidade;
		this.valorItemTxt.innerText= 'R$ ' + this.itemSelecionado.valorTotal;
		$("#mdlDetalhe").modal("show");
		
	}
	
	calculaItem() {
		let quantidade = parseFloat(this.quantidadeTxt.value);
		this.itemSelecionado.quantidade = quantidade;
		
		let valorUnitario = parseFloat(this.precoTxt.innerText.substring(3));
		
		let total = (valorUnitario * quantidade);
		this.itemSelecionado.valorTotal = total;
		
		this.valorItemTxt.innerText ="R$ " + (total==0  ? "0,00" : total.toFixed(2)+"");
	}
	
	exibeExclusao(idProduto) {
		this.exibeDialog=false;
		$("#mdlConfirmaExclusao").modal("show");
		this.itemSelecionado = this.carrinhoService.getItemSelecionado(idProduto); 
	}
	
	excluiItem() {
		this.carrinhoService.excluiItem(this.itemSelecionado.idItem).then(
				resultado => {
					$("#mdlConfirmaExclusao").modal("hide");
					this.template(resultado.itens);
					this.preencheValorTotalCarrinho(resultado.valorCarrinho);
				},
				error => {
					$("#mdlConfirmaExclusao").modal("hide");
					console.log(error);
					alert(error);
				}
		);
	}
	
	fechaExclusao() {
		this.exibeDialog=true;
		$("#mdlConfirmaExclusao").modal("hide");
	}
	
	setExibeDialog(flag) {
		this.exibeDialog = flag;
	}
	
	
}