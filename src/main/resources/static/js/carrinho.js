carrinhoController = new CarrinhoController();
carrinhoController.iniciaItens();

seleciona  = (id) => {
	this.carrinhoController.seleciona(id);
}

exclui = (id) => {
	this.carrinhoController.excluiItem(id);
}

exibeExclusao = (id) => {
	this.carrinhoController.exibeExclusao(id);
}

aumentaQuantidade =(quant) => {
	let quantTxt = document.getElementById("quant");
    var value = parseInt(quantTxt.value);
    value+=quant;
    if(value < 1){
    	quantTxt.value = 0;
    } else{
    	quantTxt.value = value;
    }
    carrinhoController.calculaItem();
}

document.querySelector('#btnPlus').addEventListener('click', (evt) => {
	carrinhoController.aumentaQuantidade.bind(carrinhoController)(1);
});

document.querySelector('#btnMinus').addEventListener('click', (evt) => {
	carrinhoController.aumentaQuantidade.bind(carrinhoController)(-1);
});


document.querySelector('#btn_confirma_exclusao').addEventListener('click', carrinhoController.excluiItem.bind(carrinhoController));
document.querySelector('#btn_cancela_exclusao').addEventListener('click', carrinhoController.fechaExclusao.bind(carrinhoController));


$('#mdlConfirmaExclusao').on('hidden.bs.modal', e =>{ carrinhoController.setExibeDialog(true); });