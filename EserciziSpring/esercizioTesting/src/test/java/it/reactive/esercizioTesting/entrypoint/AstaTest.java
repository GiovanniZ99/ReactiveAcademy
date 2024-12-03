package it.reactive.esercizioTesting.entrypoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.reactive.esercizioTesting.businesslogic.ServiceAsta;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AstaTest {

	@InjectMocks
	private Asta asta;
	@Mock
	private ServiceAsta serviceAsta;

	@Test
	public void addPartecipanteTest() {
	List<String> partecipanti = new ArrayList<>();
	partecipanti.add("Default");
	when(serviceAsta.getPartecipanti()).thenReturn(partecipanti);
	doNothing().when(serviceAsta).addPartecipante(anyString());
	asta.addPartecipante("partecipante");
	assertFalse(partecipanti.contains("Default") && partecipanti.size() == 1);
	}
	@Test
	public void visualizzaPartecipantiTest(){
		when(serviceAsta.getPartecipanti()).thenReturn(new ArrayList<>());
		assertEquals(asta.visualizzaPartecipantiSessioneCorrente(), new ArrayList<>());
	}
	@Test
	public void getValoreCorrenteTest(){
		when(serviceAsta.getValoreSessioneAsta()).thenReturn(2);
		assertEquals(asta.getValoreCorrente(), 2);
	}
	@Test
	public void testVerificaFineAsta() {
		when(serviceAsta.verificaFineAsta()).thenReturn(true);
		boolean risultato = asta.verificaFineAsta();
		assertTrue(risultato);
		verify(serviceAsta, times(1)).verificaFineAsta();
	}

	@Test
	public void setPartecipantiTest(){
		asta.setPartecipanti(new ArrayList<>());
	}
	@Test
	public void testAvvia() {
		String oggettoBandito = "Vaso";
		List<String> partecipanti = Arrays.asList("partecipante", "partecipante2");
		when(serviceAsta.verificaFineAsta()).thenReturn(true);
		asta.avvia(oggettoBandito, partecipanti);
		verify(serviceAsta, times(1)).inizializza(partecipanti);
		verify(serviceAsta, times(1)).setOggettoBandito(oggettoBandito);
		verify(serviceAsta, times(1)).verificaFineAsta();
		verify(serviceAsta, times(1)).fine();
	}
	@Test
	public void getSessioneAstaTest(){
		when(serviceAsta.getValoreSessioneAsta()).thenReturn(2);
		when(serviceAsta.getVincitore()).thenReturn("Giovanni");
		when(serviceAsta.verificaFineAsta()).thenReturn(true);
		Map<String, Object> mappa = this.asta.getSessioneAsta();
		assertEquals(mappa.get("valore"), 2);
		assertEquals(mappa.get("vincitore"), "Giovanni");
		assertEquals(mappa.get("fine asta"), true);
	}
}
