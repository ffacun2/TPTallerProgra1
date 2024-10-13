package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;

public class AutoTest_SinMascota{
	
	Vehiculo auto;
	
	@Before
	public void setUp() throws Exception {
		auto = new Auto("asd 123",3,false);
	}
	
	@After
	public void tearDown() throws Exception {
		auto = new Auto("asd 123",3,false);
	}
	
	/**
	 * Pedido valido en donde el auto no lo satisface, no permite mascota.
	 */
	@Test
	public void testGetPuntajePedido() {
		Pedido pedido = new Pedido(new Cliente("usuario1","password","mi nombre"),3,true,true,10,Constantes.ZONA_STANDARD);
		
		assertEquals("ERROR EN EL CALCULO DE PUNTAJE.DEBERIA RETORNAR NULL.",auto.getPuntajePedido(pedido),null);
	}

}
