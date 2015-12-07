package com.kurtphpr.sistema.produto;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="produtoBean")
@RequestScoped
public class ProdutoBean {

	private Produto produtoSelecionado = new Produto();
	
	private List<Produto> lista = null;
	
	public void salvar() {
		
		ProdutoRN produtoRN = new ProdutoRN();
		produtoSelecionado.setDataCadastro(new Date());
		
		//Se o id for diferente de null ou zero então altera se não inclui como novo registro;
		if (this.produtoSelecionado.getId() != null && this.produtoSelecionado.getId() != 0) {
			
			produtoRN.alterar(this.produtoSelecionado);
		}else{
			produtoRN.salvar(produtoSelecionado);
	
			FacesMessage faces = new FacesMessage("Produto cadastrado com sucesso!");
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, faces);
		}
		this.lista = null;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	public List<Produto> getLista() {
		
		ProdutoRN produtoRN = new ProdutoRN();
		if(lista == null){
			lista = produtoRN.listar();
		}
		return lista;
	}
	
	public void excluir(){
		
		ProdutoRN produtoRN = new ProdutoRN();
		produtoRN.excluir(this.produtoSelecionado);
		this.lista = null;
	}
	
	public void novo() {
		
		this.produtoSelecionado = new Produto();
	}
}