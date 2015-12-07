package com.kurtphpr.sistema.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kurtphpr.sistema.cliente.Cliente;
import com.kurtphpr.sistema.cliente.ClienteRN;

public class ClienteTest extends TestHeranca{	

	@Before
	public void setup(){
		
		Cliente c1 = new Cliente("07639231680", "dflima01@yahoo.com", "Rua José", "Cliente 01", new Date(), (float) 1850); 
		Cliente c2 = new Cliente("07639231680", "dflima02@yahoo.com", "Rua Maria", "Cliente 02", new Date(), (float) 1458);
		Cliente c3 = new Cliente("07639231680", "dflima03@yahoo.com", "Rua João", "Cliente 03", new Date(), (float) 1521.45);
	
		ClienteRN clienteRN = new ClienteRN();
		
		clienteRN.salvar(c1);
		clienteRN.salvar(c2);
		clienteRN.salvar(c3);	
	}
	
	@After
	public void limpaBanco(){
		
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista = clienteRN .listar();
		
		for (Cliente cliente : lista) {
			
			clienteRN.excluir(cliente);
		}
	}
	
	@Test
	public void salvarClienteTest(){
		
		Cliente clienteC1 = new Cliente();
		
		/*clienteC1.setNome("Ronaldo");
		clienteC1.setEndereco("Rua Teste");
		clienteC1.setRenda(5000f);
		clienteC1.setCpf("08639236580");
		*/
		
		ClienteRN clienteRN = new ClienteRN();
		
		clienteRN.salvar(clienteC1);
		
		assertEquals(true, true);
	}
	
	@Test
	public void listarClienteTest(){
		
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista = clienteRN.listar();
		assertEquals(3, lista.size());
	}
	
	//Aula 18
	@Test
	public void excluirClienteTest(){
		
		ClienteRN clienteRN = new ClienteRN();
		List<Cliente> lista = clienteRN.listar();
		
		Cliente clienteExcluido = lista.get(0);
		clienteRN.excluir(clienteExcluido);
		
		lista = clienteRN.listar();
		assertEquals(2, lista.size());
	}
	
	//Aula 19
	@Test
	public void pesquisarClienteTest(){
		
		ClienteRN clienteRN = new ClienteRN();
		Cliente clientePesquisado = clienteRN.pesquisar("te 01"); //Retorna todos os dados do cliente com essa string;
		
		assertEquals("dflima01@yahoo.com", clientePesquisado.getEmail()); //Para certificar que retornou o registro correto
																		 //compara com o e=mail, o correto é comparar com o CPF que é único;
	}
	
	//Aula 19
	@Test
	public void alterarClienteTest(){

		ClienteRN clienteRN = new ClienteRN();
		Cliente clientePesquisado = clienteRN.pesquisar("te 01"); 	       //Retorna todos os dados do cliente com essa string;
		
		assertEquals("dflima01@yahoo.com", clientePesquisado.getEmail()); //Para certificar que retornou o registro correto
																		 //compara com o e=mail, o correto é comparar com o CPF que é único;
		
		clientePesquisado.setEndereco("Novo Endereco");					 //Alterar o endereço do registro pesquisado;
		clienteRN.alterar(clientePesquisado);						    //Finaliza alteração com o clienteRN;
		
		Cliente clienteAlterado = clienteRN.pesquisar("te 01");		 	 //Pesquisou o registro alterado;
		assertEquals("Novo Endereco", clienteAlterado.getEndereco());	//Compara se o registro alterado está presente no BD;		
	}
}