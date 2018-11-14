class ProdutoController {
	
	constructor() {
		this.produtoService = new ProdutoService();
		
		this.itensSelecionados = [];
		this.itemSelecionado = {};
		
		this.precoTxt = document.querySelector("#lblPreco");
		this.quantidadeTxt = document.querySelector("#quant");
		this.valorItemTxt = document.querySelector("#lblValorTotal");
		
		this.nomeLbl = document.querySelector("#lblNome");
		this.descricaoLbl=  document.querySelector("#lblDescricao");
		this.imagemImg=  document.querySelector("#imgItem");
		this.totalCarrinhoTxt = document.querySelector("#txtTotalCarrinho");
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
	
	preencheSelecao (id) {
		this.itemSelecionado = this.produtoService.getItemSelecionado(id);
		//console.log('preenche Selecao ' + id);
		this.nomeLbl.innerHTML=this.itemSelecionado.nome;
		this.descricaoLbl.innerText=this.itemSelecionado.descricao;
		this.precoTxt.innerText= 'R$ ' + parseFloat(this.itemSelecionado.preco);
		this.imagemImg.src="data:image/png;base64, "+ this.itemSelecionado.imagem;
		
		let quant = this.quantidadeTxt.value;
		this.itemSelecionado.quantidade = parseFloat(this.quantidadeTxt.value);
		
		this.itemSelecionado.valorTotal = this.itemSelecionado.preco * this.itemSelecionado.quantidade;
		this.valorItemTxt.innerText= 'R$ ' + this.itemSelecionado.valorTotal;
	}
	
	calculaItem() {
		let quantidade = parseFloat(this.quantidadeTxt.value);
		console.log('alterada quantidade '+ quantidade);
		if (quantidade==0) {
			this.valorItemTxt.innerText="R$ 0,0";
			return;
		}
		
		let preco = parseFloat(this.precoTxt.innerText.substring(3));
		
	//	console.log(`preco ${preco} - quant. ${quantidade}`);
		
		let total = (preco * quantidade);
		this.itemSelecionado.valorTotal = total;
	//	console.log(`total ${total}`); 
		
		this.valorItemTxt.innerText ="R$ " +(total.toFixed(2))+"";
	}
	
	adicionaItem() {
		console.log(`adiciona item ${this.itemSelecionado.id} - total ${this.itemSelecionado.valorTotal}`);
		if (this.itensSelecionados.includes (this.itemSelecionado)) {
			let index = this.itensSelecionados.indexOf(this.itemSelecionado);
			let item = this.itensSelecionados[index];
			item.valorTotal = this.itemSelecionado.valorTotal;
//			this.itensSelecionados.push(this.itemSelecionado);
		} else {
			this.itensSelecionados.push(this.itemSelecionado);
		}
		this.totalizaProdutos();
		//this.itensSelecionados
	}
	
	totalizaProdutos() {
//		let total = this.itensSelecionados.reduce( (inicio, item) => {
//			return inicio +item.valorTotal;
//		}, 0);
		let total=0;
		let item = {};
		for(let i = 0 ; i < this.itensSelecionados.length; i++) {
			item=this.itensSelecionados[i];
			console.log(`>>> item: ${item.id} - valor: ${item.valorTotal}`);
			total+= item.valorTotal;
		}
		this.produtoService.gravaItem(item).then (
				itemNovo => {
					item.valorTotal = itemNovo.valorTotal;
					console.log(`>>> total do carrinhos ${total}`);
					this.totalCarrinhoTxt.innerHTML= "R$ " + total.toFixed(2) ;
				} , error => {
					console.error(error);
				}
		);
		
	}
	
}