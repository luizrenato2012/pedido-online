itemPedidoController = new ItemPedidoController();
itemPedidoController.iniciaItens();

window.addEventListener('load', function () {
	console.log('>> carregando a pagina');
});

seleciona  = (id) => {
	this.itemPedidoController.preencheSelecao(id);
}

//adicionaItem = (id) => {
//	itemPedidoController.adicionaItem.bind(id);
//}

document.querySelector('#quant').addEventListener('change', itemPedidoController.calculaItem.bind(itemPedidoController));
document.querySelector('#btnAdiciona').addEventListener('click', itemPedidoController.adicionaItem.bind(itemPedidoController));
		
aumentaQuantidade = (quant) => {
	let quantTxt = document.getElementById("quant");
    var value = parseInt(quantTxt.value);
    value+=quant;
    if(value < 1){
    	quantTxt.value = 0;
    } else{
    	quantTxt.value = value;
    }
    itemPedidoController.calculaItem();
}
