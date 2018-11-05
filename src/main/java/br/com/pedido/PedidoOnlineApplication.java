package br.com.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.pedido.controller.ProdutoResource;

@SpringBootApplication
@ComponentScan(basePackageClasses=ProdutoResource.class)
public class PedidoOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoOnlineApplication.class, args);
	}
}
