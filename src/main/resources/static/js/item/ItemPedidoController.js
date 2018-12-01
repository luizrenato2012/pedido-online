class ItemPedidoController {
	
	constructor() {
		this.itemPedidoService = new ItemPedidoService();
		
		this.itemSelecionado = {};
		
		this.precoTxt = document.querySelector("#lblPreco");
		this.quantidadeTxt = document.querySelector("#quant");
		this.valorItemTxt = document.querySelector("#lblValorTotal");
		
		this.nomeLbl = document.querySelector("#lblNome");
		this.descricaoLbl=  document.querySelector("#lblDescricao");
		this.imagemImg=  document.querySelector("#imgItem");
		this.totalCarrinhoTxt = document.querySelector("#txtTotalCarrinho");
	}
	
	iniciaItens() {
		this.itemPedidoService.listaItens().then (
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
				
				`).join('')};
				</div>`;
		let divTable = document.querySelector('#table_produtos');
		divTable.innerHTML = content;
	}
	
	preencheSelecao (idProduto) {
		this.itemSelecionado = this.itemPedidoService.getItemSelecionado(idProduto); //alterarado campo de chave p/ id produto
		console.log('ItemPedidoController preenche/selecao ' + this.itemSelecionado);
		this.nomeLbl.innerHTML=this.itemSelecionado.nome;
		this.descricaoLbl.innerText=this.itemSelecionado.descricao;
		this.imagemImg.src="data:image/png;base64, "+ this.itemSelecionado.imagem;
		
		this.precoTxt.innerText= 'R$ ' + parseFloat(this.itemSelecionado.valorUnitario);
		this.quantidadeTxt.value = this.itemSelecionado.quantidade;
		this.valorItemTxt.innerText= 'R$ ' + this.itemSelecionado.valorTotal;
		
//		let quant = this.quantidadeTxt.value;
//		this.itemSelecionado.quantidade = parseFloat(this.quantidadeTxt.value);
		
//		this.itemSelecionado.valorTotal = this.itemSelecionado.valorUnitario * this.itemSelecionado.quantidade;
	}
	
	calculaItem() {
		let quantidade = parseFloat(this.quantidadeTxt.value);
//		console.log('alterada quantidade '+ quantidade);
		if (quantidade==0) {
			this.valorItemTxt.innerText="R$ 0,0";
			return;
		}
		
		let valorUnitario = parseFloat(this.precoTxt.innerText.substring(3));
		
	//	console.log(`preco ${preco} - quant. ${quantidade}`);
		this.itemSelecionado.quantidade = quantidade;
		let total = (valorUnitario * quantidade);
		this.itemSelecionado.valorTotal = total;
	//	console.log(`total ${total}`); 
		
		this.valorItemTxt.innerText ="R$ " +(total.toFixed(2))+"";
	}
	
	adicionaItem() {
		console.log(`adiciona item ${this.itemSelecionado.id} - total ${this.itemSelecionado.valorTotal}`);
		//TODO troca para gravaItens
		this.itemPedidoService.gravaItens(this.itemSelecionado).then(
			resultado => {
				this.preencheValorTotalCarrinho(resultado.valorCarrinho);
			},
			erro => {
				console.log(erro);
				alert("Erro ao adicionar item");
			}
		);
	}
	
	preencheValorTotalCarrinho(valor) {
		console.log(`>>> total do carrinhos ${valor}`);
		this.totalCarrinhoTxt.innerHTML= "R$ " + valor.toFixed(2) ;
	}
	
}