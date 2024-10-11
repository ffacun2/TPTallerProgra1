package test.persistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistencia.PersistenciaBIN;


/**
 * Escenario donde se abre un archivo existente para lectura.
 */
public class PersistenciaBinTest_Esc2 {
	
	PersistenciaBIN persistencia = new PersistenciaBIN();
	String nombreArchivo = "datos.xml";
	File archivo = new File(nombreArchivo);

	@Before
	public void setUp() throws Exception {
		if(!archivo.exists())
			archivo.createNewFile();
	}

	@After
	public void tearDown() {
			try
			{
				persistencia.cerrarInput();
				archivo.delete();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
	}

	@Test
	public void abrirInputtest() {
		try {
			persistencia.abrirInput(nombreArchivo);
		} catch (IOException e) {
			fail("NO DEBERIA LANZAR IOEXCEPTION.EL ARCHIVO EXISTE.");
		}
	}
	
//	@Test
//	public void cerrarInputTest() {
//		try {
//			try
//			{
//				persistencia.abrirInput(nombreArchivo);
//				
//			}
//			catch(IOException e)
//			{
//				fail("ERROR.");
//			}
//			persistencia.cerrarInput();
//		} catch (IOException e) {
//			fail("NO DEBERIA LANZAR IOEXCEPTION.EL ARCHIVO FUE ABIERTO");
//		}
//	}

}
