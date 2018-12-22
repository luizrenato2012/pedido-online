itemPedidoController = new ItemPedidoController();
itemPedidoController.iniciaItens();

window.addEventListener('load', function () {
	console.log('>> carregando a pagina');
});

seleciona  = (id) => {
	this.itemPedidoController.preencheSelecao(id);
}

document.querySelector('#btnAdiciona').addEventListener('click', itemPedidoController.adicionaItens.bind(itemPedidoController));
document.querySelector('#btnPlus').addEventListener('click', itemPedidoController.aumentaQuantidade.bind(itemPedidoController));
document.querySelector('#btnMinus').addEventListener('click', itemPedidoController.diminuiQuantidade.bind(itemPedidoController));

document.querySelector('#edtQuantidade').addEventListener('change', itemPedidoController.calculaItem.bind(itemPedidoController));
document.querySelector('#lnkCarrinho').addEventListener('click', itemPedidoController.vaiProCarrinho.bind(itemPedidoController));
