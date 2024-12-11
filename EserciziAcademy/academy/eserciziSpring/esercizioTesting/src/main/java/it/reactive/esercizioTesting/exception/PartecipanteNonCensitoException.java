package it.reactive.esercizioTesting.exception;

public class PartecipanteNonCensitoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartecipanteNonCensitoException(){
		super("Partecipante non censito");
	}
	
}
