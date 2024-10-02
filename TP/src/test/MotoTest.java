package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import util.Constantes;

public class MotoTest {

	Moto moto;
	
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
	public void testConstructorMoto() {
		assertEquals("Error en el constructor. Patente no se asigna correctamente.","asd 123",moto.getPatente());
		assertEquals("Error en el constructor. Cantidad Plazas distinta de 1.",1,moto.getCantidadPlazas());
		assertEquals("Error en el constructor. Mascota distinto de false.",false,moto.isMascota());
	}

	/**
	 * 
	 */
	@Test
	public void testGetPuntajePedido() {
		Pedido pedido = new Pedido(new Cliente("usuario1","password","mi nombre"),1,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
		Pedido pedido2 = new Pedido(new Cliente("usuario1","password","mi nombre"),2,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
		Pedido pedido3 = new Pedido(new Cliente("usuario1","password","mi nombre"),1,true,false,10,Constantes.ZONA_SIN_ASFALTAR);
		Pedido pedido4 = new Pedido(new Cliente("usuario1","password","mi nombre"),1,false,true,10,Constantes.ZONA_SIN_ASFALTAR);
		
		assertEquals("Error obtener puntaje para un pedido valido.",Integer.valueOf(1000),moto.getPuntajePedido(pedido));
		assertEquals("Error obtener puntaje para un pedido invalido.",null,moto.getPuntajePedido(pedido2));
		assertEquals("Error obtener puntaje para un pedido invalido.",null,moto.getPuntajePedido(pedido3));
		assertEquals("Error obtener puntaje para un pedido invalido.",null,moto.getPuntajePedido(pedido4));
		
	}
}
