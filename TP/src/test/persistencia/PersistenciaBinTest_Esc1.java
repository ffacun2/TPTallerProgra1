package test.persistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistencia.PersistenciaBIN;

/**
 * Escenario donde se intenta abrir el archivo datos.xml el cual no existe.
 */
public class PersistenciaBinTest_Esc1 {
	
	PersistenciaBIN persistencia = new PersistenciaBIN();
	String nombreArchivo = "datos.txt";
	File arch = new File(nombreArchivo);

	@Before
	public void setUp() throws Exception{
		if(arch.exists())
			arch.delete();
	}
	
	@After
	public void tearDown() throws Exception{
		if(arch.exists())
			arch.delete();
	}
	
	
	/**
	 * Abre para lectura el archivo pasado como parametro. En caso de que no exista
	 * Lanza excepcion
	 */
	@Test
	public void abrirInputTest() {
			try
			{
				persistencia.abrirInput(nombreArchivo);
				fail("DEBERIA LANZAR IOEXCEPCION.EL ARCHIVO NO EXISTE.");
			}
			catch(IOException error)
			{
			}
	}
	
	/**
	 * Cierra el archivo abierto para lectura. SI no se abrio lanza excepcion
	 */
	@Test
	public void cerrarInputTest() {
		try
		{
			persistencia.cerrarInput();
			fail("DEBERIA LANZAR IOEXCEPCION. NO HAY ARCHIVO ABIERTO.");
		}
		catch(IOException error)
		{
		}
	}

	@Test
	public void leerTest() {
		try {
			Serializable lectura = persistencia.leer();
			fail("DEBERIA LANZAR EXCEPCION. ARCHIVO NO ABIERTO.");
		} 
		catch (ClassNotFoundException e)
		{
		}
		catch(IOException e)
		{
		}
	}
	
	
	
	
}
