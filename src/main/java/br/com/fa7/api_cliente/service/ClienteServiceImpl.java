package br.com.fa7.api_cliente.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fa7.api_cliente.entity.Cliente;
import br.com.fa7.api_cliente.exception.NotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private EntityManager em;
	
	
	public List<Cliente> findAll() {
		return em.createQuery("From Cliente e", Cliente.class).getResultList();
	}

	public Cliente findById(Long id) {
		return em.find(Cliente.class, id);
	}

	@Transactional
	public void insert(Cliente cliente) {
		em.persist(cliente);	
	}

	@Transactional(rollbackFor={NotFoundException.class})
	public Cliente update(Cliente cliente) {
		Cliente clie = findById(cliente.getId());
		if(clie == null){
			throw new NotFoundException("Cliente", "não possui este registro em nosso sistema.");
		}
		return em.merge(cliente);
	}

	@Transactional(rollbackFor={NotFoundException.class})
	public void remove(Long id){
		Cliente cliente = findById(id);
		if(cliente == null){
			throw new NotFoundException("Cliente", "não possui este registro em nosso sistema.");
		}
		em.remove(cliente);
	}

}
