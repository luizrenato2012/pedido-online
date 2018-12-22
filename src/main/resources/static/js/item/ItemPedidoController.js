class ItemPedidoController {
	
	constructor() {
		this.itemPedidoService = new ItemPedidoService();
		
		this.itemSelecionado = {};
		
		this.precoTxt = document.querySelector("#lblPreco");
		this.quantidadeTxt = document.querySelector("#edtQuantidade");
		this.valorItemTxt = document.querySelector("#lblValorTotal");
		
		this.nomeLbl = document.querySelector("#lblNome");
		this.descricaoLbl=  document.querySelector("#lblDescricao");
		this.imagemImg=  document.querySelector("#imgItem");
		
		this.totalCarrinhoTxt = document.querySelector("#txtTotalCarrinho");
	}
	
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
				
				`).join('')}
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
		
	}
	
	calculaItem() {
		let quantidade = parseFloat(this.quantidadeTxt.value);
		this.itemSelecionado.quantidade = quantidade;
		
		let valorUnitario = parseFloat(this.precoTxt.innerText.substring(3));
		
		let total = (valorUnitario * quantidade);
		this.itemSelecionado.valorTotal = total;
		
		this.valorItemTxt.innerText ="R$ " + (total==0  ? "0,00" : total.toFixed(2)+"");
	}
	
	adicionaItens() {
		console.log(`adiciona item ${this.itemSelecionado.id} - total ${this.itemSelecionado.valorTotal}`);
		
		this.itemPedidoService.totalizaItens().then(
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
	
	aumentaQuantidade () {
		this.mudaQuantidade(1);
	}
	
	diminuiQuantidade() {
		this.mudaQuantidade(-1);
	}
	
	mudaQuantidade (quantidade) {
	    let value = parseInt(this.quantidadeTxt.value);
	    value+=quantidade;
	    this.quantidadeTxt.value = value < 1 ? 0 : value;
//	    if(value < 1){
//	    	quantTxt.value = 0;
//	    } else{
//	    	quantTxt.value = value;
//	    }
	    itemPedidoController.calculaItem();
	}
	
	vaiProCarrinho () {
		this.adicionaItens();
		console.log('vai pro carrinho');
	}
}