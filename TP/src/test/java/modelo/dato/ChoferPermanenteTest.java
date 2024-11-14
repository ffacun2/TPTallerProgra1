package modelo.dato;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;

/**
 * Testeo de la clase ChoferPermanente en el escenario donde chofer es valido.
 */
public class ChoferPermanenteTest {

	Chofer chofer;
	Chofer chofer2;
	
	@Before
	public void setUp() throws Exception {
		chofer = new ChoferPermanente("123","mi nombre",2000,2);
		chofer2 = new ChoferPermanente("456","otro nombre",2015,1);
	}

	/**
	 * Verifico que el constructor ChoferPermanenete asigne 
	 * correctamente los parametros.
	 */
	@Test
	public void testConstructorDNI() {
		assertEquals("Error en el constructor.No se asigna correctamente el dni.","123",chofer.getDni());
	}
	@Test
	public void testConstructorNombre() {
		assertEquals("Error en el constructor.No se asigna correctamente el nombre.","mi nombre",chofer.getNombre());
	}
	@Test
	public void testConstructorCantHijos() {
		assertEquals("Error en el constructor.No se asigna correctamente la cantidad de hijos.",Integer.valueOf(2),((ChoferPermanente)chofer).getCantidadHijos(),2);
	}
	@Test
	public void testConstructorAnioIngreso() {
		assertEquals("Error en el constructor.No se asigna correctamente el anio de ingreso.",2000,((ChoferPermanente)chofer).getAnioIngreso());
	}
	
	/**
	 * El metodo getAntiguedad calcula la diferencia de la fecha del anio actual con la fecha del anio de ingreso
	 * y retorna el resultado en formato int
	 */
	@Test
	public void testGetAntiguedad() {
		assertEquals("Error al calcular la antiguedad.",24,((ChoferPermanente)chofer).getAntiguedad());
	}
	
	/**
	 * Metodo sueldo bruto retorna el sueldo en base a su antiguedad y la cantidad de hijos.
	 * Se le aumenta un 5% por cada anio de antiguedad hasta llegar a 100%.
	 * Se le aumenta 7% por cada hijo.
	 */
	@Test
	public void testSueldoBruto() {
		Chofer.setSueldoBasico(700.0);
		double sueldo = Chofer.getSueldoBasico();
		int antiguedad = ((ChoferPermanente)chofer).getAntiguedad();
		int cant_hijos = ((ChoferPermanente)chofer).getCantidadHijos();
		
		if ( antiguedad > 20)
			sueldo += Chofer.getSueldoBasico();
		else
			sueldo += Chofer.getSueldoBasico()*antiguedad*0.05;
		
		sueldo += Chofer.getSueldoBasico()*cant_hijos*0.07;
		System.out.println(sueldo);
		assertEquals(sueldo,chofer.getSueldoBruto(),1.0);
	}
	
	@Test
	public void testSueldoBruto2() {
		Chofer.setSueldoBasico(700.0);
		double sueldo = Chofer.getSueldoBasico();
		int antiguedad = ((ChoferPermanente)chofer2).getAntiguedad();
		int cant_hijos = ((ChoferPermanente)chofer2).getCantidadHijos();
		
		if ( antiguedad > 20)
			sueldo += Chofer.getSueldoBasico();
		else
			sueldo += Chofer.getSueldoBasico()*antiguedad*0.05;
		
		sueldo += Chofer.getSueldoBasico()*cant_hijos*0.07;
		System.out.println(sueldo);
		assertEquals(sueldo,chofer2.getSueldoBruto(),1.0);
		
	}

}
