package test.modelo.dato;

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
		pedido = new Pedido(cliente,2,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
	}

	@After
	public void tearDown() throws Exception {
		cliente = new Cliente("usuario1","password","Mi nombre");
		pedido = new Pedido(new Cliente("usuario1","password","Mi nombre"),2,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
	}

	@Test
	public void testConstructorPedido() {
		assertEquals("Error en la asignacion de cliente.",cliente,pedido.getCliente());
		assertEquals("Error en la asignacion de cantidad de pasajeros.",2,pedido.getCantidadPasajeros());
		assertEquals("Error en la asignacion de mascota.",true,pedido.isMascota());
		assertEquals("Error en la asignacion de baul.",false,pedido.isBaul());
		assertEquals("Error en la asignacion de zona.",Constantes.ZONA_SIN_ASFALTAR,pedido.getZona());
	}

}
