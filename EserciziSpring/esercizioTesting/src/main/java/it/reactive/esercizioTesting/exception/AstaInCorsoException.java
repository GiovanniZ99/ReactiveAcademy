package it.reactive.esercizioTesting.exception;

public class AstaInCorsoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AstaInCorsoException(){
		super("Asta in corso");
	}
	
}
