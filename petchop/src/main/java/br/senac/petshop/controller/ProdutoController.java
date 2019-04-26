package br.senac.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.senac.petshop.model.produto.ProdutoService;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	ProdutoService prodService = new ProdutoService();

	@RequestMapping("{codigo}")
	public ModelAndView detalhe(@PathVariable("codigo") String codigo) {
		return new ModelAndView("produto").addObject("produtoCodigo", codigo).addObject("detalhe", "sim");
	}

	@RequestMapping("lista")
	public String produtos() {
		return "produtos";
	}

	@RequestMapping("alterar/{codigo}")
	public ModelAndView alterar(@PathVariable("codigo") String codigo) {
		return new ModelAndView("produtoAlterar").addObject("produtoCodigo", codigo);
	}

	@RequestMapping("cadastro")
	public String cadastro() {
		return "produtoCadastro";
	}

}
