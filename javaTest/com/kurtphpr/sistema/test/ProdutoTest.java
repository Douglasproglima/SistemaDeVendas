package com.kurtphpr.sistema.test;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kurtphpr.sistema.produto.Produto;
import com.kurtphpr.sistema.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProdutoTest {
	//Nas classes testes devem implementar a mesma interface da aplicação;
	
	private static Session sessao;			//Atributo para iniciar Sessão;
	private static Transaction transacao;	//Atributo para iniciar a transação;
	
				  //Método para abrir a conexão
	@BeforeClass //Anotação que informa para o teste que esse método irá executar antes de todos os outros testes
	public static void abreConexao() {
		
		sessao = HibernateUtil.getSession().getCurrentSession(); //Pega o atributo sessao receberá o sesao do  HibernateUtil;
		transacao = sessao.beginTransaction();			 		//O atributo transação recebe pe necessário iniciar uma 
															   //transação, sem essa transação não é possível dar o commit no banco;
	}
	
		  //Métodos para testar inserção
	@Test //Anotação para testUnitário
	public void salvarProdutoTest(){
		
		Query consulta = pesquisar("002");
		
		Produto produtoRetornado = (Produto) consulta.uniqueResult(); //Em grande projeto é aconselhavel utilizar uma list de valores;
	
		assertEquals("LT", produtoRetornado.getUnidade());
	}
	
	//Essa anotação serve para toda vez que for que um teste é executado na aplicação (Class ProdutoTest()) ele irá executar.;
	//Irá executar essa anotação para cada método desta Class;
	@Before 
	public void setup(){ //Esse método prepara a base para efetuar os teste;
		
		//Instância o método Produto() com os argumentos passados no contrutor Produtos();
		Produto prodTest01 = new Produto("UN", "Produto teste 001", new Date(), 30, 2.5f);
		Produto prodTest02 = new Produto("LT", "Produto teste 002", new Date(), 45, 3.6f);
		Produto prodTest03 = new Produto("KG", "Produto teste 003", new Date(), 25, 4.3f);
		Produto prodTest04 = new Produto("PC", "Produto teste 004", new Date(), 12, 7.1f);
		Produto prodTest05 = new Produto("CM", "Produto teste 005", new Date(), 15, 10.2f);
	
		//Insere valores aos métodos sets da classe Produto();
		sessao.save(prodTest01);
		sessao.save(prodTest02);
		sessao.save(prodTest03);
		sessao.save(prodTest04);
		sessao.save(prodTest05);
	}
	
	//Essa anotação sempre irá executar após a execução do método test;
	//Se faz necessário a utilização desta anotação em conjunto com o método limpaBanco para que após a execução de todos os métodos desta Class, o banco fique limpo;
	@After
	public void limpaBanco(){
		
		Criteria lista = sessao.createCriteria(Produto.class); //Objeto Criteria irá retorna uma lista de produtos do banco;
		@SuppressWarnings("unchecked")						  //Utiliza essa anotação para ignorar os alertas mostrados pela IDE;
		List<Produto> produtos = lista.list();
		
		for (Produto produto : produtos) {
			sessao.delete(produto);
		}
	}
	
	//Métodos para testar os dados inseridos
	@Test
	public void listaProdutosTest(){ //Irá retornar uma lista de objetos cadastrados no banco
		
		Criteria lista = sessao.createCriteria(Produto.class); //Objeto Criteria irá retorna uma lista de produtos do banco;
		@SuppressWarnings("unchecked")						  //Utiliza essa anotação para ignorar os alertas mostrados pela IDE;
		List<Produto> produtos = lista.list();				  //Cria uma lista que recebe a lista do Criteria utilizando o método list();
		
		assertEquals(5,  produtos.size());
	}
	
	//Métodos para testar a exclusão
	@Test
	public void excluirProdutoTest(){
		
		Query consulta = pesquisar("001");
		
		Produto produtoDeletado = (Produto) consulta.uniqueResult(); //Pesquisa o produto 001;
		sessao.delete(produtoDeletado);							    //Pega objeto retornado e deleta;
		
		produtoDeletado = (Produto) consulta.uniqueResult();	   //Pesquisa o produto novamente;
		assertNull(produtoDeletado);							  //E finaliza com o assertNull verificando se retornou null;
	}
	
	//Métodos para testar alteração
	@Test
	public void alteracaoProdutoTest(){
		
		Query consulta = pesquisar("005");
		
		Produto produtoAlterado = (Produto) consulta.uniqueResult();   //Pesquisa o produto 001;
		produtoAlterado.setEstoque(100);							  //Altera o valor do estoque do produto 005;
		sessao.update(produtoAlterado);							     //Pega objeto retornado e deleta;
		
		produtoAlterado = (Produto) consulta.uniqueResult();	    //Retorna a listagem com o valor do estoque alterado;
		assertEquals(100, produtoAlterado.getEstoque().intValue());//intValue se fez necessário para converter em inteiro o tipo de dados do banco que está como long;
	}

	private Query pesquisar(String parametro) {
		String sql = "from Produto produto where produto.descricao like :descricao ";
		Query consulta = sessao.createQuery(sql);
		consulta.setString("descricao", "%"+parametro+"%");
		return consulta;
	}
	
	//Método auxiliar para fechar conexão;
	@AfterClass //Essa anotação informa que esse método fechaConexao execute após todos os testes
	public static void fechaConexao(){
		
		try {
			transacao.commit();//Comita ação realizada;	
			
		} catch (Throwable erro01) {
			System.out.println("Erro 01 ao comitar os dados: " + erro01.getMessage());
		
		}finally {
			
			try {
				if(sessao.isOpen()){ //Testa se sessão estiver aberta para fechar;
					sessao.close();
				}	
			} catch (Exception erro02) {
				System.out.println("Erro 02 ao fechar conexão: " + erro02.getMessage());
			}
		}
	}	
}