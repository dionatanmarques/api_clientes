package br.com.fa7.api_cliente.service;

import java.util.List;

import br.com.fa7.api_cliente.entity.Cliente;
import br.com.fa7.api_cliente.entity.Endereco;
import br.com.fa7.api_cliente.exception.NotFoundException;

public interface EnderecoService {

	public List<Endereco> findAll(Cliente cliente);

	public Endereco findById(Long id);

	public void insert(Endereco endereco);

	public Endereco update(Endereco endereco) throws NotFoundException;

	public void remove(Long id) throws NotFoundException;

}
