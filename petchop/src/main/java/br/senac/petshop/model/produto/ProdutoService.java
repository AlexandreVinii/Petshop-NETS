package br.senac.petshop.model.produto;

import java.util.Date;
import java.util.List;

import br.senac.petshop.daos.ProdutoDAO;

public class ProdutoService {

	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public String salvar(Produto novo) {
		// Retornar o codigo do produto pra mostrar ele talvez
		Long code = new Date().getTime();
		String finalcode = code.toString() + novo.getNome().substring(novo.getNome().length() - 2).toUpperCase();
		novo.setCodigo(finalcode);

		novo.setDtCadastro(new Date());

		// Salvar o produto no banco
		produtoDAO.create(novo);

		return novo.getCodigo();
	}

	public String alterar(Object prod) {
		// Tratar o object para virar um produto
		Produto alterado = (Produto) prod;

		// Salvar o produto no banco
		produtoDAO.update(alterado);

		return alterado.getCodigo();
	}

	public List<Produto> getByNome(String nome) {

		List<Produto> encontrados = produtoDAO.getByNome(nome);
		return encontrados;
	}

	public Produto getByCodigo(String codigo) {
		List<Produto> encontrados = produtoDAO.getByCodigo(codigo);
		return encontrados.get(0);
	}

}
