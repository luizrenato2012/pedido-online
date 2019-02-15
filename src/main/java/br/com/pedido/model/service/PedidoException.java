package br.com.pedido.model.service;

public class PedidoException extends RuntimeException {

	private static final long serialVersionUID = -3409263732403236422L;

	public PedidoException(Exception e) {
		super(e);
	}
	
	public PedidoException (String msg) {
		super(msg);
	}

}
