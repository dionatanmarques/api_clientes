package br.com.fa7.api_cliente.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fa7.api_cliente.entity.Cliente;
import br.com.fa7.api_cliente.entity.Endereco;
import br.com.fa7.api_cliente.exception.NotFoundException;
import br.com.fa7.api_cliente.service.ClienteService;
import br.com.fa7.api_cliente.service.EnderecoService;

@Component
@Path("/clientes/{idCliente}/enderecos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@GET
	public List<Endereco> findAll(@PathParam("idCliente") Long id){
		Cliente cliente = clienteService.findById(id);
		List<Endereco> enderecos = service.findAll(cliente);
		return enderecos ;
	}
	
	@GET
	@Path("{id}")
	public Response get(@PathParam("idCliente") Long idCliente, @PathParam("id") Long id){
		Cliente cliente = clienteService.findById(idCliente);
		Endereco endereco = service.findById(id);
		if(cliente==null || endereco == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(endereco).build();
	}
	
	@POST
	public Response insert(@PathParam("idCliente") Long idCliente, Endereco endereco){
		Cliente cliente = clienteService.findById(idCliente);
		if(cliente == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		endereco.setCliente(cliente);
		service.insert(endereco);
		return Response.ok(endereco).build();
	}
	
	@PUT
	@Path("{id}")
	public Response update(@PathParam("idCliente") Long idCliente, @PathParam("id") Long id, Endereco endereco){
		
		endereco.setId(id);
		try {
			Cliente cliente  = clienteService.findById(idCliente);
			if(cliente == null){
				return Response.status(Status.NOT_FOUND).build();
			}
			endereco.setCliente(cliente);
			Endereco enderecoRegistrado = service.update(endereco);
			return Response.ok(enderecoRegistrado).build();
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("idCliente") Long idCliente,@PathParam("id") Long id){
		try {
			Cliente cliente = clienteService.findById(idCliente);
			if(cliente==null){
				return Response.status(Status.NOT_FOUND).build();
			}
			service.remove(id);
			return Response.status(Status.OK).build(); //NO_CONTENT
		} catch (NotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
}
