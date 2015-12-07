package com.kurtphpr.sistema.produto;

import java.util.List;

public interface ProdutoDAO {

	//Assinatura do método salvar();
	public void salvar(Produto produto);

	public List<Produto> listar();

	public void alterar(Produto produto);
	
	public Produto pesquisar(String descricao);

	public void excluir(Produto produto);
}
