package modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

/**
 * Testeo clase Pedido con usuario valido.
 */
public class PedidoTest {
	
	Pedido pedido;
	Cliente cliente;

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("usuario1","password","Mi nombre");
	}

	@Test
	public void testConstructorCliente() {
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals("Error en la asignacion de cliente.",cliente,pedido.getCliente());
	}
	@Test
	public void testConstructorCantPasajeros() {
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals("Error en la asignacion de cantidad de pasajeros.",2,pedido.getCantidadPasajeros());
	}
	@Test
	public void testConstructorMascota() {
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals("Error en la asignacion de mascota.",true,pedido.isMascota());
	}
	@Test
	public void testConstructorBaul() {
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals("Error en la asignacion de baul.",false,pedido.isBaul());
	}
	@Test
	public void testConstructorZona1() {
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
		assertEquals("Error en la asignacion de zona.",Constantes.ZONA_SIN_ASFALTAR,pedido.getZona());
	}
	
	@Test
	public void testConstructorZona2() {
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_PELIGROSA);
		assertEquals("Error en la asignacion de zona.",Constantes.ZONA_PELIGROSA,pedido.getZona());
	}
	
	@Test
	public void testConstructorZona3() {
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_STANDARD);
		assertEquals("Error en la asignacion de zona.",Constantes.ZONA_STANDARD,pedido.getZona());
	}

}
