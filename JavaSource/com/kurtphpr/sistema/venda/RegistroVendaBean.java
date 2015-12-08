package com.kurtphpr.sistema.venda;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kurtphpr.sistema.cliente.Cliente;
import com.kurtphpr.sistema.produto.Produto;
import com.kurtphpr.sistema.produto.ProdutoRN;

/*Tipos de Anotação de Escopos
 * É o tempo que a class irá fica em processamento;
 * 
 * Na class ClienteBean temos o escopo RequestScoped, é simplesmente um dispara, 
 * quando se faz um ação ele salva vai para o banco e não gerar histórico desta transação;
 * 
 * @SessionScoped: Essa anotação informa que o escopo de nosso bean gerenciado é de sessão, ou seja, enquanto durar 
 * minha sessão eu vou ter um objeto da classe RegistroVendasBean na sessão da minha aplicação;
 * 
 * @ConversationScoped: Essa anotação cria um escopo de conversação entre duas páginas onde os objetos 
 * permanecem “vivos”  entre a pagina que originou o request até a página alvo;
 * 
 * @ApplicationScoped: Cria um escopo cujos os objetos são vistos por toda aplicação enquanto a aplicação estiver em execução;
 * 
 * ViewScoped: Cria um escopo maior em requisição e menor do que a sessão, é restringido apenas a mesma página e 
 * tem incompopatibilidade com JSTL é necessário limpar a todo o instante os dados do ManagerBean;
 * */
@ManagedBean(name = "registroVendas")
@ViewScoped
public class RegistroVendaBean {

	//Variáveis que serão utilizados no momento de registra as vendas;
	private Cliente clienteSelecionado;
	private Produto produtoSelecionado = new Produto();
	
	//É necessário instância o carrinhoCompras pois quando for pesquisar e adicionar um produto ele será null;
	private List<Produto> carrinhoCompras = new ArrayList<Produto>();
	
	//Irá mostrar o valor total dos produtos separadamente;
	private float valorTotal;

	
	//Métodos que serão utilizadas na página registro de vendas;
	public void getBuscaProduto(){
		
		ProdutoRN produtoRN = new ProdutoRN();
		Produto produtoPesquisado = new Produto();
		
		//No inputText da página irá fazer a busca pela descrição por causa desta implementação;
		if (produtoSelecionado.getDescricao() != null && !this.produtoSelecionado.getDescricao().equals("")) {
			
			produtoPesquisado =  produtoRN.pesquisar(this.produtoSelecionado.getDescricao());
			if (produtoPesquisado != null) {
				
				this.carrinhoCompras.add(produtoPesquisado);
				
				//Após aidcionar o produto pesquisado acima, será necessário atualizar o valor total do carrinho de compra;
				calculaTotal();
				
			}
		}
	}
	
	private void calculaTotal() {
		
		//Valida se o carrinho de compras não está vázio;
		if (!this.carrinhoCompras.isEmpty()) {
			
			//forech para percorrer todo o carrinho de compras;
			for (Produto p : this.carrinhoCompras) {
				//Enquanto tiver um valor irá atualizar o valorTotal da página;
				valorTotal =+ p.getValor();
			}
		}
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public List<Produto> getCarrinhoCompras() {
		return carrinhoCompras;
	}

	public void setCarrinhoCompras(List<Produto> carrinhoCompras) {
		this.carrinhoCompras = carrinhoCompras;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
}
