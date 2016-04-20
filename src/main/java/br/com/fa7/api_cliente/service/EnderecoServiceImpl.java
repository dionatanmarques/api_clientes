package br.com.fa7.api_cliente.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fa7.api_cliente.entity.Cliente;
import br.com.fa7.api_cliente.entity.Endereco;
import br.com.fa7.api_cliente.exception.NotFoundException;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EntityManager em;
	
	public List<Endereco> findAll(Cliente cliente) {
		List<Endereco> enderecos =  em.createQuery("FROM Endereco e Where e.cliente = :cliente", Endereco.class).setParameter("cliente", cliente).getResultList();
		return enderecos;
	}

	public Endereco findById(Long id) {
		return em.find(Endereco.class, id);
	}

	@Transactional
	public void insert(Endereco endereco) {
		em.persist(endereco);
	}

	@Transactional(rollbackFor={NotFoundException.class})
	public Endereco update(Endereco endereco) {
		Endereco end = findById(endereco.getId());
		if(end!=null){
			return em.merge(endereco);
		}
		throw new NotFoundException("Endereço", "não possui este registro em nosso sistema.");
	}

	@Transactional(rollbackFor={NotFoundException.class})
	public void remove(Long id) {
		
		Endereco endereco = findById(id);
		if(endereco == null){
			throw new NotFoundException("Endereço", "não possui este registro em nosso sistema.");
		}
		em.remove(endereco);
	}

}
