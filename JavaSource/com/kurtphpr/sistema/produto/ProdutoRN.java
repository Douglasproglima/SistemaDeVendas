package com.kurtphpr.sistema.produto;

import java.util.List;

import com.kurtphpr.sistema.util.DAOFactory;

public class ProdutoRN {

	//Objeto do tipo DAO, que vai ser a interface que irá fazer a persistência entre os dados;
	private ProdutoDAO produtoDAO;
	
	//Construtor que irá pegar o produtoDAO que irá criar a conexão com o DAOFactory;
	public ProdutoRN(){
		
		this.produtoDAO = DAOFactory.criaProdutoDAO();
	}

	public void salvar(Produto produto) {
		
		this.produtoDAO.salvar(produto);
	}

	public List<Produto> listar() {
		
		return this.produtoDAO.listar();
	}

	public Produto pesquisar(String descricao) {
		
		return this.produtoDAO.pesquisar(descricao);
	}

	public void excluir(Produto produto) {
		
		this.produtoDAO.excluir(produto);
	}
	
	public void alterar(Produto produto) {
		
		this.produtoDAO.alterar(produto);
	}
}
