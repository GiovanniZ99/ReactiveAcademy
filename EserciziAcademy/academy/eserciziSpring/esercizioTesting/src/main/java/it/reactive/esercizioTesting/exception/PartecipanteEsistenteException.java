package it.reactive.esercizioTesting.exception;


public class PartecipanteEsistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartecipanteEsistenteException(String giocatore){
		super("Partecipante esistente: " + giocatore);
	}
	
}
