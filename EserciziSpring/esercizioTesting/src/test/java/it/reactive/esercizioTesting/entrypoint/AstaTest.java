package it.reactive.esercizioTesting.entrypoint;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.reactive.esercizioTesting.businesslogic.ServiceAsta;

@RunWith(MockitoJUnitRunner.class)
public class AstaTest {

	@InjectMocks Asta asta;
	@Mock ServiceAsta serviceAsta;

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
}
