package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;

/**
 * Testeo Clase ChoferTemporario donde el escenario el chofer valido.
 */
public class ChoferTemporarioTest {

	Chofer chofer;
	
	@Before
	public void setUp() throws Exception {
		chofer = new ChoferTemporario("123", "mi nombre");
	}


	/**
	 * Verifico que el constructor asigne correctamente los parametros
	 */
	@Test
	public void testConstructorDNI() {
		assertEquals("Error en el constructor.No se asigna correctamente el dni.","123",chofer.getDni());
	}
	@Test
	public void testConstructorNombre() {
		assertEquals("Error en el constructor.No se asigna correctamente el nombre.","mi nombre",chofer.getNombre());
	}

	/**
	 * Verifico que los metodos estaticos set y get sueldo basico
	 * funcionen correctamente.
	 */
	@Test
	public void testSetSueldoBasico() {
		Chofer.setSueldoBasico(700.0);
		assertEquals(700.0,Chofer.getSueldoBasico(),1.0);
	}
	
	/**
	 * El metodo sueldo bruto retorna para el chofer temporario
	 * el sueldo basico
	 */
	@Test
	public void testGetSueldoBruto() {
		assertEquals(Chofer.getSueldoBasico(),chofer.getSueldoBruto(),1.0);
	}
	
	/**
	 * El metodo sueldo bruto retorna el 86% del sueldo bruto
	 * que a su vez, este sueldo bruto es igual al sueldo basico.
	 */
	@Test
	public void testSueldoBruto() {
		Chofer.setSueldoBasico(700.0);
		assertEquals(700.0*0.86,chofer.getSueldoNeto(),1.0);
	}
}
