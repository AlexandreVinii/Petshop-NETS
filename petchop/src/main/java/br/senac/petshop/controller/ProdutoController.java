package br.senac.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senac.petshop.model.produto.ProdutoService;

@Controller
@RequestMapping("produtos")
public class ProdutoController {

	ProdutoService prodService = new ProdutoService();

	public String produtos() {
		return "produtos";
	}
	
	@RequestMapping("cadastro")
	public String cadastro() {
		return "testeCadastro";
	}

}
