package com.kurtphpr.sistema.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kurtphpr.sistema.cliente.Cliente;
import com.kurtphpr.sistema.cliente.ClienteRN;
import com.kurtphpr.sistema.produto.Produto;
import com.kurtphpr.sistema.produto.ProdutoRN;
import com.kurtphpr.sistema.venda.Venda;
import com.kurtphpr.sistema.venda.VendaRN;

public class VendaTest extends TestHeranca{
	
	
	//Variáveis Globais para serem acessadas pelo método registraVenda();
	Cliente c1;
	Cliente c2;
	Cliente c3;
	
	Produto p1;
	Produto p2;
	Produto p3;
	
	@Before
	public void setup(){
		
		//ClienteTest
		c1 = new Cliente("07639231680", "dflima01@yahoo.com", "Rua José", "Cliente 01", new Date(), (float) 1850); 
		c2 = new Cliente("07639231680", "dflima02@yahoo.com", "Rua Maria", "Cliente 02", new Date(), (float) 1458);
		c3 = new Cliente("07639231680", "dflima03@yahoo.com", "Rua João", "Cliente 03", new Date(), (float) 1521.45);
	
		ClienteRN clienteRN = new ClienteRN();
		
		clienteRN.salvar(c1);
		clienteRN.salvar(c2);
		clienteRN.salvar(c3);
		

		//ProdutoTest
		p1 = new Produto("UN", "Produto teste 001", new Date(), 30, 2.5f);
		p2 = new Produto("LT", "Produto teste 002", new Date(), 45, 3.6f);
		p3 = new Produto("KG", "Produto teste 003", new Date(), 25, 4.3f);
		
		ProdutoRN produtoRN = new ProdutoRN();
		
		produtoRN.salvar(p1);
		produtoRN.salvar(p2);
		produtoRN.salvar(p3);

	}
	
	@After
	public void limpaBanco(){
		
		VendaRN vendaRN = new VendaRN();
		List<Venda> vendas = vendaRN.listar();
		
		for (Venda venda : vendas) {
			vendaRN.excluir(venda);
		}
	}
	
	
	@Test
	public void registraTest() {
	
		VendaRN vendaRN = new VendaRN();
		
		Venda venda1 = new Venda();
		venda1.setCliente(c1);
		venda1.setProduto(p1);
		venda1.setDataVenda(new Date());
		
		Venda venda2 = new Venda();
		venda2.setCliente(c2);
		venda2.setProduto(p2);
		venda2.setDataVenda(new Date());

		Venda venda3 = new Venda();
		venda3.setCliente(c3);
		venda3.setProduto(p3);
		venda3.setDataVenda(new Date());
		
		vendaRN.registrarVenda(venda1);
		vendaRN.registrarVenda(venda2);
		vendaRN.registrarVenda(venda3);
		
		//Verifica se existe os registro cadastrados 
		List<Venda> vendas = vendaRN.listar();
		
		assertEquals(3, vendas.size());
	}
}