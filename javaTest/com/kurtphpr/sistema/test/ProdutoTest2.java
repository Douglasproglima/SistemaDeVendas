package com.kurtphpr.sistema.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kurtphpr.sistema.produto.Produto;
import com.kurtphpr.sistema.produto.ProdutoRN;

public class ProdutoTest2 extends TestHeranca{
	
	@Before
	public void setup(){
		
		Produto produtoP1 = new Produto("lote", "Caderno", new Date(), 25, (float) 13.55);
		Produto produtoP2 = new Produto("lote2", "Regua", new Date(), 35, (float) 14.55);
		Produto produtoP3 = new Produto("Fardo", "Caneta", new Date(), 45, (float) 15.55);
		Produto produtoP4 = new Produto("edicao", "livro", new Date(), 66, (float) 16.55);
		Produto produtoP5 = new Produto("caixa", "Caneta", new Date(), 77, (float) 17.55);
		
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.salvar(produtoP1);
		produtoRN.salvar(produtoP2);
		produtoRN.salvar(produtoP3);
		produtoRN.salvar(produtoP4);
		produtoRN.salvar(produtoP5);
	}
	
	@After
	public void limpaBanco(){
		//Criteria lista = sessao.createCriteria(Produto.class);
		//@SuppressWarnings("unchecked")
		
		ProdutoRN produtoRN  = new ProdutoRN();
		List<Produto> produtos = produtoRN.listar();
		
		for (Produto produto : produtos) {
			
			produtoRN.excluir(produto);
		}
	}	
	
	@Test
	public void salvarProdutoTest(){
		
		/*Produto produtoP1 = new Produto();
		
		produtoP1.setDescricao("Produto02 Teste 02");
		produtoP1.setUnidade("LT");
		produtoP1.setDataCadastro(new Date());
		produtoP1.setEstoque(100);
		produtoP1.setValor(1500.5f);
		*/
		
		ProdutoRN produtoRN = new ProdutoRN();
		Produto produtoSalvo = new Produto("Apontador", "lote33", new Date(), 110, 1.0f);
		produtoRN.salvar(produtoSalvo);
		
		Produto produtoPesquisado = produtoRN.pesquisar("Apon");
		assertEquals("lote33", produtoPesquisado.getUnidade());
	}
	
	@Test
	public void listarProdutoTest(){
		
		ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> produtos = produtoRN.listar();
		
		assertEquals(5, produtos.size());
	}
	
	//Aula 18
	/*@Test
	public void excluirProdutoTest(){
		
		/*ProdutoRN produtoRN = new ProdutoRN();
		List<Produto> lista = produtoRN.listar();
		
		Produto produtoExcluido = lista.get(0);
		produtoRN.excluir(produtoExcluido);
		
		lista = produtoRN.listar();
		assertEquals(2, lista.size());*/
	
		/*Query consulta = pesquisar("papel");
		Produto produtoDeletado = (Produto) consulta.uniqueResult();
		sessao.delete(produtoDeletado);
	}*/
	
	
	
	//Aula 19
	/*@Test
	public void pesquisarProdutoTest(){
		
		ProdutoRN produtoRN = new ProdutoRN();
		Produto produtoPesquisado = produtoRN.pesquisar("Apontador");
		
		assertEquals("lote33", produtoPesquisado.getUnidade());
	}
	
	//Aula 19
	@Test
	public void alterarProdutoTest(){
		
		ProdutoRN produtoRN = new ProdutoRN();
		Produto produtoPesquisado = produtoRN.pesquisar("Apontador");
		
		assertEquals("lote33", produtoPesquisado.getUnidade());
		
		produtoPesquisado.setEstoque(550);
		produtoRN.alterar(produtoPesquisado);
		
		Produto produtoAlterado = produtoRN.pesquisar("Apontador");
		assertEquals(550, produtoAlterado.getEstoque().intValue());
	}*/
}
