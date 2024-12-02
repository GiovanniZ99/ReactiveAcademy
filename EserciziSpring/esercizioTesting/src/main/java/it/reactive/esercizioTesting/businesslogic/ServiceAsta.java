package it.reactive.esercizioTesting.businesslogic;

import java.util.ArrayList;
import java.util.List;

import it.reactive.esercizioTesting.exception.AstaInCorsoException;
import it.reactive.esercizioTesting.exception.AstaTerminataException;
import it.reactive.esercizioTesting.exception.PartecipanteEsistenteException;
import it.reactive.esercizioTesting.exception.PartecipanteNonCensitoException;
import it.reactive.esercizioTesting.exception.TurnoNonValidoException;
import it.reactive.esercizioTesting.exception.ValoreNonAmmessoException;
import it.reactive.esercizioTesting.oggetto.SessioneAsta;

public class ServiceAsta implements IServiceAsta {

	private SessioneAsta sessioneAsta;

	ServiceAsta(){
		List<String> partecipanti=new ArrayList<>();
		partecipanti.add("Default");
		sessioneAsta=new SessioneAsta(partecipanti);
	}

	public void inizializza(List<String> partecipanti) {
		this.sessioneAsta=new SessioneAsta(partecipanti);
		this.sessioneAsta.setInCorso(true);
		this.sessioneAsta.setPartecipantiPassati(new ArrayList<String>());
	}

	public void setOggettoBandito(String oggettoBandito) {
		sessioneAsta.setOggettoBandito(oggettoBandito);
	}

	public void rilancia(String nomePartecipante, int valore) {

		if (verificaFineAsta()) {
			throw new AstaTerminataException();
		}

		List<String> partecipanti = sessioneAsta.getPartecipanti();
		if (!partecipanti.contains(nomePartecipante)) {
			throw new PartecipanteNonCensitoException();
		}
		int progressivoTurnoCorrente = sessioneAsta.getProgressivoTurnoCorrente();
		String partecipanteCorrente = partecipanti.get(progressivoTurnoCorrente);
		if (nomePartecipante.equals(partecipanteCorrente)) {
			if (valore>0) {
				sessioneAsta.setVincitore(nomePartecipante);
				int valoreAtt = sessioneAsta.getValore();
				sessioneAsta.setValore(valoreAtt+valore);
			} else if (valore<0) {
				sessioneAsta.getPartecipantiPassati().add(nomePartecipante);
			} else {
				throw new ValoreNonAmmessoException("0");
			}
			boolean continua=true;
			while (continua) {
				progressivoTurnoCorrente++;
				if (progressivoTurnoCorrente == sessioneAsta.getPartecipanti().size()) {
					progressivoTurnoCorrente=0;
				}
				if (!sessioneAsta.getPartecipantiPassati().contains(sessioneAsta.getPartecipanti().get(progressivoTurnoCorrente))) {
					continua=false;
				}
			}
			sessioneAsta.setProgressivoTurnoCorrente(progressivoTurnoCorrente);
		}
		else {
			throw new TurnoNonValidoException(partecipanteCorrente);
		}
	}

	public int getValoreSessioneAsta() {
		return sessioneAsta.getValore();
	}

	public String getVincitore() {
		return sessioneAsta.getVincitore();
	}

	public List<String> setPartecipanti(List<String> partecipanti) {
		throw new UnsupportedOperationException("Non ancora implementato");
	}

	public void addPartecipante(String partecipante) {
		if(sessioneAsta.isInCorso()) {
			throw new AstaInCorsoException();
		}
		List<String> partecipanti = sessioneAsta.getPartecipanti();
		if (partecipanti.contains(partecipante)) {
			throw new PartecipanteEsistenteException(partecipante);
		}
		partecipanti.add(partecipante);
	}

	public List<String> getPartecipanti() {
		return sessioneAsta.getPartecipanti();

	}

	public void fine() {
		this.sessioneAsta.setInCorso(false);
	}

	public boolean verificaFineAsta() {
		if (sessioneAsta.getPartecipantiPassati().size()==sessioneAsta.getPartecipanti().size()-1) {
			return true;
		} else {
			return false;
		}
	}


}
