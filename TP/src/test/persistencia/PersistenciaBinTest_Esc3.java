package test.persistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistencia.PersistenciaBIN;

/**
 * Escenario donde se escribe un archivo y se lee para corroborar su escritura.
 */
public class PersistenciaBinTest_Esc3 {

	PersistenciaBIN persistencia = new PersistenciaBIN();
	String nombreArchivo = "datos.xml";
	File archivo = new File(nombreArchivo);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try
		{
			persistencia.abrirInput(nombreArchivo);
		}
		catch(IOException e)
		{
			fail("asd");
		}
	}

}
