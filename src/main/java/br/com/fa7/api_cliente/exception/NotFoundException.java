package br.com.fa7.api_cliente.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3908869069518292139L;

	public NotFoundException(String entidade, String message, Throwable cause) {
		super(entidade+": "+message, cause);
	}

	public NotFoundException(String entidade,String message) {
		super(entidade+": "+message);
	}
}
