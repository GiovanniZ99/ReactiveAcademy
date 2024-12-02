package it.reactive.esercizioTesting.businesslogic;

import java.util.List;

public interface IServiceAsta {
	
	public void inizializza(List<String> partecipanti);
	public void setOggettoBandito(String oggettoBandito);
	public void rilancia(String nomeBanditore, int valore);
	public int getValoreSessioneAsta();
	public String getVincitore();
	public List<String> setPartecipanti(List<String> partecipanti);
	public void addPartecipante(String partecipante);
	public List<String> getPartecipanti();
	public void fine();
	public boolean verificaFineAsta();
	

}
