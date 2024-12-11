package it.reactive.esercizioTesting.exception;

public class ValoreNonAmmessoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValoreNonAmmessoException(String valore){
		super("Valore " + valore + " non ammesso");
	}
	
}
