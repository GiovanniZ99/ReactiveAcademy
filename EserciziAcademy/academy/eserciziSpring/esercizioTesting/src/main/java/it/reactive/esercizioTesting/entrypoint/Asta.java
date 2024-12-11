package it.reactive.esercizioTesting.entrypoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.reactive.esercizioTesting.businesslogic.IServiceAsta;

public class Asta {
	
	private IServiceAsta serviceAsta;
	private List<String> partecipanti;

	public Asta(IServiceAsta serviceAsta) {
		this.serviceAsta=serviceAsta;
		partecipanti=serviceAsta.getPartecipanti();
	}
	
	@Deprecated
	public void avvia(String oggettoBandito) {
		avvia(oggettoBandito, partecipanti);
	}

	public void avvia(String oggettoBandito, List<String> partecipanti) {
		serviceAsta.inizializza(partecipanti);
		serviceAsta.setOggettoBandito(oggettoBandito);
		if (serviceAsta.verificaFineAsta()){
			serviceAsta.fine();
		}
	}
	
	public int rilancia(String nomeBanditore, int valore) {
		serviceAsta.rilancia(nomeBanditore, valore);
		return serviceAsta.getValoreSessioneAsta();
	}
	
	public boolean passa(String nomeBanditore) {
		serviceAsta.rilancia(nomeBanditore, -1);
		return serviceAsta.verificaFineAsta();
	}
	
	public boolean verificaFineAsta() {
		return serviceAsta.verificaFineAsta();
	}
	
	public Map<String, Object> getSessioneAsta() {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("valore", serviceAsta.getValoreSessioneAsta());
		ret.put("vincitore", serviceAsta.getVincitore());
		ret.put("fine asta", serviceAsta.verificaFineAsta());
		return ret;
	}

	public int getValoreCorrente() {
		return serviceAsta.getValoreSessioneAsta();
	}
	
	public void addPartecipante(String partecipante) {
		List<String> partecipanti = serviceAsta.getPartecipanti();
		if (partecipanti.size()==1 && partecipanti.contains("Default")) {
			partecipanti.remove(0);
		}
		serviceAsta.addPartecipante(partecipante);
	}
	
	public List<String> visualizzaPartecipantiSessioneCorrente(){
		return serviceAsta.getPartecipanti();
	}
	
	public Map<String, Object> fineAstaForzata(){
		serviceAsta.fine();
		return getSessioneAsta();
	}
	
	public List<String> setPartecipanti(List<String> partecipanti) {
		return serviceAsta.setPartecipanti(partecipanti);
	}
	
}
