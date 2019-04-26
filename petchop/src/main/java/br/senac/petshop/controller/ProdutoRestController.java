package br.senac.petshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.petshop.model.produto.Produto;
import br.senac.petshop.model.produto.ProdutoService;

@RestController
@RequestMapping("restproduto")
public class ProdutoRestController {

	ProdutoService prodService = new ProdutoService();

	@RequestMapping("save")
	public String save(@RequestBody Produto novo) {

		// Retorna o codigo do produto
		return prodService.salvar(novo);
	}

	@RequestMapping("update")
	public void update(@RequestBody Produto alterado) {
		prodService.alterar(alterado);
	}

	@RequestMapping("name/{nome}")
	public List<Produto> listNome(@PathVariable("nome") String nome) {
		List<Produto> produtos = prodService.getByNome(nome);
		return produtos;
	}

	@RequestMapping("code/{codigo}")
	public Produto listCodigo(@PathVariable("codigo") String codigo) {
		Produto produto = prodService.getByCodigo(codigo);
		return produto;
	}
}
