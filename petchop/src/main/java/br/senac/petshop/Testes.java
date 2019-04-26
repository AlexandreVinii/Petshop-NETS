package br.senac.petshop;

import br.senac.petshop.daos.ProdutoDAO;

public class Testes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProdutoDAO prodao = new ProdutoDAO();
		String produtao = prodao.getByNome("ProdutoTeste").toString();
		System.out.print(produtao);
	}

}
