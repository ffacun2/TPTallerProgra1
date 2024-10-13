package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;

public class MotoTest {

	Vehiculo moto;
	
	@Before
	public void setUp() throws Exception {
		moto = new Moto("asd 123");
	}

	@After
	public void tearDown() throws Exception {
		moto = new Moto("asd 123");
	}

	/**
	 * Verifico que los parametros pasados en el constructor de Moto se 
	 * asignen correctamente.
	 */
	@Test
	public void testConstructorPatente() {
		assertEquals("Error en el constructor. Patente no se asigna correctamente.","asd 123",moto.getPatente());
	}
	@Test
	public void testConstructorCantPlazas() {
		assertEquals("Error en el constructor. Cantidad Plazas distinta de 1.",1,moto.getCantidadPlazas());
	}
	@Test
	public void testConstructorMascota() {
		assertEquals("Error en el constructor. Mascota distinto de false.",false,moto.isMascota());
	}

	/**
	 * Verifico que el puntaje calculado sea el correcto para distintos tipso de pedidos.
	 */
	@Test
	public void testGetPuntajePedidoValido() {
		Pedido pedido = new Pedido(new Cliente("usuario1","password","mi nombre"),1,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
		
		assertEquals("Error obtener puntaje para un pedido valido.",Integer.valueOf(1000),moto.getPuntajePedido(pedido));
	}
	@Test
	public void testGetPuntajePedidoValidoInsastisfecho() {
		Pedido pedido2 = new Pedido(new Cliente("usuario1","password","mi nombre"),2,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
		
		assertEquals("Error obtener puntaje para un pedido invalido.",null,moto.getPuntajePedido(pedido2));
	}
	@Test
	public void testGetPuntajePedidoValidoInsastisfecho2() {
		Pedido pedido3 = new Pedido(new Cliente("usuario1","password","mi nombre"),1,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
		
		assertEquals("Error obtener puntaje para un pedido invalido.",null,moto.getPuntajePedido(pedido3));
	}
	@Test
	public void testGetPuntajePedidoValidoInsastisfecho3() {
		Pedido pedido4 = new Pedido(new Cliente("usuario1","password","mi nombre"),1,false,true,10,Constantes.ZONA_SIN_ASFALTAR);
		
		assertEquals("Error obtener puntaje para un pedido invalido.",null,moto.getPuntajePedido(pedido4));
	}
}
