package it.reactive.esercizioTesting.businesslogic;

import java.util.*;

import it.reactive.esercizioTesting.exception.*;
import org.junit.Before;
import org.junit.Test;

import it.reactive.esercizioTesting.entrypoint.Asta;
import org.junit.function.ThrowingRunnable;

import static org.junit.Assert.*;

public class ServiceAstaTest {
	@Before
	public void setupt(){
		partecipanti.add("partecipante");
		partecipanti.add("secondoPartecipante");
	}
	static ServiceAsta serviceAsta = new ServiceAsta();
	static  List<String> partecipanti = new ArrayList<>();


	@Test
	public void test() {
//		fail("Not yet implemented");
	}

	@Test
	public void testList(){
		serviceAsta = new ServiceAsta();
		assertEquals ("Default", serviceAsta.getPartecipanti().get(0));
	}

//	public void inizializza(List<String> partecipanti) {
//		this.sessioneAsta=new SessioneAsta(partecipanti);
//		this.sessioneAsta.setInCorso(true);
//		this.sessioneAsta.setPartecipantiPassati(new ArrayList<String>());
//	}


	@Test
	public void inizializzaTest() {
		partecipanti.add("boh");
		this.serviceAsta.inizializza(partecipanti);
		assertEquals(partecipanti.size(), this.serviceAsta.getPartecipanti().size());
	}

	@Test(expected = AstaTerminataException.class)
	public void rilanciaTest(){
		this.serviceAsta.inizializza(partecipanti);
		serviceAsta.rilancia("partecipante", -1);
		serviceAsta.rilancia("secondoPartecipante", 1);

	}
	@Test(expected = ValoreNonAmmessoException.class)
	public void rilanciaValoreNonAmmessoException(){
		this.serviceAsta.inizializza(partecipanti);
		serviceAsta.rilancia("partecipante", 0);
	}
	@Test(expected = PartecipanteNonCensitoException.class)
	public void rilanciaPartecipanteNonAmmessoException(){
		this.serviceAsta.inizializza(partecipanti);
		serviceAsta.rilancia("partecipanteNonEsistente", 0);
	}
	@Test(expected = UnsupportedOperationException.class)
	public void setPartecipantiTestException(){
		serviceAsta.setPartecipanti(partecipanti);
	}
	@Test(expected = AstaInCorsoException.class)
	public void addPartecipanteTestAstaInCorsoException(){
		serviceAsta.inizializza(partecipanti);
		serviceAsta.addPartecipante("partecipante");
	}
	@Test(expected = PartecipanteEsistenteException.class)
	public void addPartecipanteTestNonCensitoException(){
		this.serviceAsta.inizializza(partecipanti);
		serviceAsta.fine();
		serviceAsta.addPartecipante("secondoPartecipante");
	}
	@Test(expected = TurnoNonValidoException.class)
	public void turnoCorrenteException(){
		this.serviceAsta.inizializza(partecipanti);

		serviceAsta.rilancia("secondoPartecipante", 1);
	}

	/*
	 * Questa non � una classe di test perch� non contiene Assert!!!!!
	 * E' stata lasciata per documentare come pu� avvenire un flow di chiamate
	 */
	@Test
	public void test2() {
		Asta asta = new Asta(new ServiceAsta());
		asta.avvia("Auto");
		Map<String, Object> sessioneAsta = asta.getSessioneAsta();
		System.out.println(sessioneAsta);
		sessioneAsta = asta.getSessioneAsta();
		System.out.println(sessioneAsta);
		System.out.println(asta.visualizzaPartecipantiSessioneCorrente());
//		asta.addPartecipante("Daniele");
		asta.fineAstaForzata();
		asta.addPartecipante("Daniele");
		asta.addPartecipante("Mario");
		asta.avvia("Moto");
		System.out.println(asta.getValoreCorrente());
//		asta.rilancia("Mario", 3);
		asta.rilancia("Daniele", 3);
		asta.rilancia("Mario", 1);
		asta.rilancia("Daniele", 2);
		sessioneAsta = asta.getSessioneAsta();
		System.out.println(sessioneAsta);
		System.out.println(asta.visualizzaPartecipantiSessioneCorrente());
		System.out.println(asta.verificaFineAsta());
		System.out.println(asta.passa("Mario"));
		List<String> lista = new ArrayList<String>();
		lista.add("Mario");
		lista.add("Daniele");
		lista.add("Anna");
		asta.avvia("Bici", lista);
		asta.rilancia("Mario", 1);
		asta.passa("Daniele");
		asta.rilancia("Anna", 1);
		asta.rilancia("Mario", 1);
		asta.rilancia("Anna", 1);
		System.out.println(asta.passa("Mario"));
		sessioneAsta = asta.getSessioneAsta();
		System.out.println(sessioneAsta);
	}
}
