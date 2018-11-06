class Produto {
	
	constructor (id, nome, descricao, preco, imagem) {
		Object.assign( this, {id, nome, descricao, preco, imagem })
	}
	
	// metodos get permite usar objeto.nome p/ obter valor ms evitar objeto.nome=valor
	get id() {
		return this.id;
	}
	
	get nome() {
		return this.nome;
	}
	
	get descricao() {
		return this.descricao;
	}
	
	get preco() {
		return this.preco;
	}
	
	get imagem() {
		return this.imagem;
	}
}