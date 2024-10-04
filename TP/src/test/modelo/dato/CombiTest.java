package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	CombiTest.CombiConMascota.class,
	CombiTest.CombiSinMascota.class
})

public class CombiTest {

	/**
	 * Escenario donde la combi permite el acceso con mascota
	 */
	public static class  CombiConMascota {

		Vehiculo combi;
		
		@Before
		public void setUp() throws Exception {
			combi = new Combi("asd 123",6,true);
		}
		
		@After
		public void tearDown() throws Exception {
			combi = new Combi("asd 123",6,true);
		}
		
		/**
		 * Verifico que el constructor asigne correctamente los atributos validos en la clase Combi
		 */
		@Test
		public void testConstructorCombi1() {
			assertEquals("Error en el constructor. Patente distantas","asd 123",combi.getPatente());
			assertEquals("Error en el constructor. Plazas distantas",6,combi.getCantidadPlazas());
			assertEquals("Error en el constructor. Estado mascota distantas",true,combi.isMascota());
		}
		
		
		/**
		 * Verifico que el metodo getPuntajePedido calcule correctamente el puntaje de acuerdo las 
		 * caracteristicas del pedido valido.
		 * El valor del puntaje depende solo de si se usa baul o no.
		 */
		@Test
		public void testGetPuntajePedido1() {
			Pedido pedido1 = new Pedido(new Cliente("usuario1","password","mi nombre"),4,true,true,10,Constantes.ZONA_PELIGROSA);
			Pedido pedido2 = new Pedido(new Cliente("usuario1","password","mi nombre"),4,false,false,10,Constantes.ZONA_PELIGROSA);
			
			assertEquals("Error en el calculo de puntaje pedido con baul.",Integer.valueOf(140),combi.getPuntajePedido(pedido1));
			assertEquals("Error en el calculo de puntajet pedido sin baul.",Integer.valueOf(40),combi.getPuntajePedido(pedido2));
		}

	}

	/**
	 * Escenario donde el combi no permite el acceso con mascotas
	 */
	public static class CombiSinMascota {
		
		Vehiculo combi;
		
		@Before
		public void setUp() throws Exception {
			combi = new Combi("asd 123",6,false);
		}
		
		@After
		public void tearDown() throws Exception {
			combi = new Combi("asd 123",6,false);
		}
		
		@Test
		public void testConstructorCombi() {
			assertEquals("Error en el constructor. La patente no se asigna correctamente.","asd 123",combi.getPatente());
			assertEquals("Error en el constructor. La cantidad de plazas no se asgina correctamente.",6,combi.getCantidadPlazas());
			assertEquals("Error en el constructor. El uso de mascota no se asigna correctamente.",false,combi.isMascota());
		}
		
		@Test
		public void testGetPuntajePedido() {
			Pedido pedido = new Pedido(new Cliente("usuario1","password","mi nombre"),3,true,true,10,Constantes.ZONA_STANDARD);
			
			assertEquals("Error en el calculo de Pedido.Deberia retornar null.",null,combi.getPuntajePedido(pedido));
		}
	}

}
