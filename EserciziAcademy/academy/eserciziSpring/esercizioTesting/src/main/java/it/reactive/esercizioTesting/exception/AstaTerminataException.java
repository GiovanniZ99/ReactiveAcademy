package it.reactive.esercizioTesting.exception;

public class AstaTerminataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AstaTerminataException(){
		super("L'asta è terminata");
	}
	
}
