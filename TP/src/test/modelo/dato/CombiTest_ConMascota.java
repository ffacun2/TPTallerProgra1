package test.modelo.dato;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;

public class CombiTest_ConMascota {

	Vehiculo combi;
	
	@Before
	public void setUp() throws Exception {
		combi = new Combi("asd 123",6,true);
	}
	
	/**
	 * Verifico que el constructor asigne correctamente los atributos validos en la clase Combi
	 */
	@Test
	public void testConstructorPatente() {
		assertEquals("Error en el constructor. Patente distantas","asd 123",combi.getPatente());
	}
	@Test
	public void testConstructorCantPlazas() {
		assertEquals("Error en el constructor. Plazas distantas",6,combi.getCantidadPlazas());
	}
	@Test
	public void testConstructorMascota() {
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
