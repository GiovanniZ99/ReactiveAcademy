package it.reactive.esercizioTesting.exception;

public class TurnoNonValidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TurnoNonValidoException(String giocatore){
		super("E' il turno di: " + giocatore);
	}
	
}
