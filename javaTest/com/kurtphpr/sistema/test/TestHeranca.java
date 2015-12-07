package com.kurtphpr.sistema.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.kurtphpr.sistema.util.HibernateUtil;

//Essa Class foi criada como abstrata pois será utilizada seus atributos e métodos através de herança
public abstract class TestHeranca {

	private static Session sessao;			//Atributo para iniciar Sessão;
	private static Transaction transacao;	//Atributo para iniciar a transação;
	
	@BeforeClass //Anotação que informa para o teste que esse método irá executar antes de todos os outros testes
	public static void abreConexao() {
		
		sessao = HibernateUtil.getSession().getCurrentSession(); //Pega o atributo sessao receberá o sesao do  HibernateUtil;
		transacao = sessao.beginTransaction();			 		//O atributo transação recebe pe necessário iniciar uma 
															   //transação, sem essa transação não é possível dar o commit no banco;
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
