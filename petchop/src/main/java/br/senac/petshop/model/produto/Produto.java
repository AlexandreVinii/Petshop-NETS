package br.senac.petshop.model.produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Produto {

	private int idProduto;
	private String codigo;
	private String nome;
	private String descricao;
	private double peso;
	private double preco;
	private Date dtCadastro;
	private boolean emEstoque;
	private boolean desativado;

	public Produto() {
	}

	public Produto(ResultSet rs) throws SQLException {

		super();
		this.idProduto = rs.getInt("idproduto");
		this.codigo = rs.getString("codigo");
		this.nome = rs.getString("nome");
		this.descricao = rs.getString("descricao");
		this.peso = rs.getDouble("peso");
		this.preco = rs.getDouble("preco");
		this.dtCadastro = new Date(rs.getTimestamp("dtcadastro").getTime());
		this.emEstoque = rs.getBoolean("emEstoque");
		this.desativado = rs.getBoolean("desativado");

	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public boolean isEmEstoque() {
		return emEstoque;
	}

	public void setEmEstoque(boolean emEstoque) {
		this.emEstoque = emEstoque;
	}

	public boolean isDesativado() {
		return desativado;
	}

	public void setDesativado(boolean desativado) {
		this.desativado = desativado;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao
				+ ", peso=" + peso + ", preco=" + preco + ", dtCadastro=" + dtCadastro + ", emEstoque=" + emEstoque
				+ ", desativado=" + desativado + "]";
	};

}
