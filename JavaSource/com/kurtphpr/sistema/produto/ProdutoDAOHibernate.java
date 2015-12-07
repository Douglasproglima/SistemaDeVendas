package com.kurtphpr.sistema.produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProdutoDAOHibernate implements ProdutoDAO {

	//Atributo
	private Session sessao;
	
	//Método Salvar
	@Override
	public void salvar(Produto produto) {
		
		this.sessao.save(produto);
	}

	//Get e Set da sessão
	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listar() {
		
		Criteria lista = sessao.createCriteria(Produto.class);
		return lista.list();
	}

	@Override
	public Produto pesquisar(String descricao) {
		
		Query consultaDescricao = this.sessao.createQuery("from Produto p1 where p1.descricao like :descricao ");
		consultaDescricao.setString("descricao", "%"+ descricao +"%");
		
		return (Produto) consultaDescricao.uniqueResult();
	}

	@Override
	public void alterar(Produto produto) {
		
		this.sessao.update(produto);
	}

	@Override
	public void excluir(Produto produto) {
		
		this.sessao.delete(produto);
	}
}
