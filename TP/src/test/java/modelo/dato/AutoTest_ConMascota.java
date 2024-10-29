package modelo.dato;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;

public class AutoTest_ConMascota {

	Vehiculo auto;
	
	@Before
	public void setUp() throws Exception {
		auto = new Auto("asd 123",3,true);
	}
	
	/**
	 * Verifico que el constructor asigne correctamente los atributos validos en la clase Auto
	 */
	@Test
	public void testConstructorPatente() {
		assertEquals("ERROR EN EL CONSTRUCTOR. PATENTE DISTINTA.",auto.getPatente(),"asd 123");
	}
	@Test
	public void testConstructorMascota() {
		assertEquals("ERROR EN EL CONSTRUCTOR. ESTADO MASCOTA DISTINTO.",auto.isMascota(),true);
	}
	@Test
	public void testConstructorCantPlazas() {
		assertEquals("ERROR EN EL CONSTRUCTOR. PLAZAS DISTINTAS",auto.getCantidadPlazas(),3);
	}
	
	
	/**
	 * Verifico que el metodo getPuntajePedido calcule correctamente el puntaje de acuerdo las 
	 * caracteristicas del pedido valido.
	 * El valor del puntaje depende solo de si se usa baul o no.
	 * 
	 * Pedido1 = pedido valido, con baul, retorna 80pts
	 * Pedido2 = pedido valido, sin baul, retorna 60pts
	 * Pedido3 = pedido invalido, retorna null
	 */
	@Test
	public void testGetPuntajePedido1() {
		Pedido pedido1 = new Pedido(new Cliente("usuario1","password","mi nombre"),2,true,true,10,Constantes.ZONA_PELIGROSA);
		Pedido pedido2 = new Pedido(new Cliente("usuario1","password","mi nombre"),2,false,false,10,Constantes.ZONA_PELIGROSA);
		Pedido pedido3 = new Pedido(new Cliente("usuario1","password","mi nombre"),5,true,true,10,Constantes.ZONA_PELIGROSA);
		
		assertEquals("ERROR EN EL CALCULO DE PUNTAJE PEDIDO CON BAUL",auto.getPuntajePedido(pedido1),Integer.valueOf(80));
		assertEquals("ERROR EN EL CALCULO DE PUNTAJE PEDIDO SIN BAUL.",auto.getPuntajePedido(pedido2),Integer.valueOf(60));
		assertEquals("ERROR EN EL CALCULO DE PUNTAJE PEDIDO.",auto.getPuntajePedido(pedido3),null);
	}
	
}
