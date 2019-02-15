package br.com.pedido.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pedido.model.service.PedidoException;

@ControllerAdvice
public class PedidoExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PedidoException.class)
	public ResponseEntity handleEPedidoException (Exception ex ,WebRequest request) {
		return this.handleExceptionInternal(ex, this.criaListaErros(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_GATEWAY, request);
	}
	
	private List<Erro> criaListaErros(String ...erros) {
		List<Erro> listaErros = new ArrayList<>();
		for(String erro : erros) {
			listaErros.add (new Erro(erro));
		}
		return listaErros;
	}
	
	private class Erro {
		String mensagem;

		public Erro() {
			super();
		}

		public Erro(String mensagem) {
			super();
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		
	}
}
