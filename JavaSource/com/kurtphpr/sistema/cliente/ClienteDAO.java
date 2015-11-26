package com.kurtphpr.sistema.cliente;

import java.util.List;

public interface ClienteDAO {

	//Lembrando que na interface é criado somente as assinaturas dos métodos da Class ClienteDAOHibernate
	public void salvar(Cliente cliente);

	public List<Cliente> listar();
}
