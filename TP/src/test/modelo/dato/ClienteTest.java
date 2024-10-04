package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;

public class ClienteTest {
	
	Cliente cliente;

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("usuario1","password","mi nombre");
	}

	@After
	public void tearDown() throws Exception {
		cliente = new Cliente("usuario1","password","mi nombre");
	}

	/**
	 * Verifico que los parametros pasados en el constructor se asignen
	 * correctamente
	 */
	@Test
	public void testConstructorCliente() {
		assertEquals("EL NOMBRE DE USUARIO NO SE ASIGNA CORRECTAMENTE","usuario1",cliente.getNombreUsuario());
		assertEquals("LA CONTRASEÑA NO SE ASIGNA CORRECTAMENTE.","password",cliente.getPass());
		assertEquals("EL NOMBRE REAL NO SE ASIGNA CORRECTAMENTE.","mi nombre",cliente.getNombreReal());
	}
	
	

}
