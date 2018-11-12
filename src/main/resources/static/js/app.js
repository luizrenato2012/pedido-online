produtoController = new ProdutoController();
produtoController.listaProdutos();

window.addEventListener('load', function () {
	console.log('>> carregando a pagina');
});

seleciona  = (id) => {
	console.log(">>> selecionado " + id);
	this.produtoController.preencheSelecao(id);
}

//adicionaItem = (id) => {
//	produtoController.adicionaItem.bind(id);
//}

document.querySelector('#quant').addEventListener('change', produtoController.calculaItem.bind(produtoController));
document.querySelector('#btnAdiciona').addEventListener('click', produtoController.adicionaItem.bind(produtoController));
		
process = (quant) => {
	let quantTxt = document.getElementById("quant");
    var value = parseInt(quantTxt.value);
    value+=quant;
    if(value < 1){
    	quantTxt.value = 0;
    } else{
    	quantTxt.value = value;
    }
    produtoController.calculaItem();
}
