package br.com.fa7.api_cliente.service;

import java.util.List;

import br.com.fa7.api_cliente.entity.Cliente;
import br.com.fa7.api_cliente.exception.NotFoundException;

public interface ClienteService {
	
	public List<Cliente> findAll();

	public Cliente findById(Long id);

	public void insert(Cliente cliente);

	public Cliente update(Cliente cliente) throws NotFoundException;

	public void remove(Long id) throws NotFoundException;

}
