produtoController = new ProdutoController();
produtoController.listaProdutos();

window.addEventListener('load', function () {
	console.log('>> carregando a pagina');
});

seleciona  = (id) => {
	console.log(">>> selecionado " + id);
	this.produtoController.preencheSelecao(id);
}
