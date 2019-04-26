package br.senac.petshop.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.senac.petshop.connection.ConnectionFactory;
import br.senac.petshop.model.produto.Produto;

public class ProdutoDAO {

	static Connection cn = null;

	public ProdutoDAO() {
	};

	public void create(Produto novo) {

		String novoProdutoSQL = "INSERT INTO produto (codigo , nome, descricao, peso, preco, dtCadastro, emEstoque, desativado) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement insertProduto = null;

		cn = ConnectionFactory.getConnection();

		try {

			insertProduto = cn.prepareStatement(novoProdutoSQL);

			insertProduto.setString(1, novo.getCodigo());
			insertProduto.setString(2, novo.getNome());
			insertProduto.setString(3, novo.getDescricao());
			insertProduto.setDouble(4, novo.getPeso());
			insertProduto.setDouble(5, novo.getPreco());
			insertProduto.setTimestamp(6, new java.sql.Timestamp(novo.getDtCadastro().getTime()));
			insertProduto.setBoolean(7, novo.isEmEstoque());
			insertProduto.setBoolean(8, novo.isDesativado());

			insertProduto.execute();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, insertProduto);
		}

	}

	public void update(Produto alterado) {

		String updateProdutoSQL = "UPDATE produto " + "SET nome = ?, descricao = ?, peso = ?, preco = ?,"
				+ " emEstoque = ?, desativado = ? " + "WHERE codigo = ?";

		cn = ConnectionFactory.getConnection();
		PreparedStatement updateProduto = null;

		try {

			updateProduto = cn.prepareStatement(updateProdutoSQL);

			// UPDATE
			updateProduto.setString(1, alterado.getNome());
			updateProduto.setString(2, alterado.getDescricao());
			updateProduto.setDouble(3, alterado.getPeso());
			updateProduto.setDouble(4, alterado.getPreco());
			updateProduto.setBoolean(5, alterado.isEmEstoque());
			updateProduto.setBoolean(6, alterado.isDesativado());

			// WHERE
			updateProduto.setString(7, alterado.getCodigo());
			updateProduto.execute();
			updateProduto.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn);
		}

	}

	public List<Produto> getByNome(String nome) {

		String sql = "SELECT * FROM produto WHERE nome = ? LIMIT 1";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> prods = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			while (rs.next()) {
				prods.add(new Produto(rs));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return prods;
	}

	public List<Produto> getByCodigo(String codigo) {
		String sql = "SELECT * FROM produto WHERE codigo = ? LIMIT 1";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> prods = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, codigo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				prods.add(new Produto(rs));
			}

			return prods;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return null;
	}

	public List<Produto> getAllLimited() {

		String sql = "SELECT * FROM produto LIMIT 20";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> prods = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				prods.add(new Produto(rs));
			}

			return prods;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return null;

	}

}
