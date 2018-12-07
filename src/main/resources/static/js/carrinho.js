carrinhoController = new CarrinhoController();
carrinhoController.iniciaItens();

seleciona  = (id) => {
	this.carrinhoController.preencheSelecao(id);
}

exclui = (id) => {
	this.carrinhoController.excluiItem(id);
}

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

document.querySelector('#quant').addEventListener('change', carrinhoController.calculaItem.bind(carrinhoController));
//document.querySelector('#btnExclui').addEventListener('click', carrinhoController.excluiItem.bind(carrinhoController));
