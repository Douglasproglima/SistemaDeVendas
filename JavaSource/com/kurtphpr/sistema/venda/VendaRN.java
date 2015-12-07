package com.kurtphpr.sistema.venda;

import java.util.List;

import com.kurtphpr.sistema.util.DAOFactory;

public class VendaRN {

	//Objeto do tipo DAO, que vai ser a interface que irá fazer a persistência entre os dados;
	private VendaDAO vendaDAO;
	
	//Construtor que irá pegar o produtoDAO que irá criar a conexão com o DAOFactory;
	public VendaRN(){
		
		this.vendaDAO = DAOFactory.criaVendaDAO();
	}

	public void registrarVenda(Venda venda) {
		
		this.vendaDAO.registra(venda);
	}

	public List<Venda> listar() {
		
		return this.vendaDAO.getLista();
	}

	public void excluir(Venda vendas) {
		
		this.vendaDAO.excluir(vendas);
	}
}
