package controlador;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import util.Constantes;
import util.Mensajes;
import vista.DefaultOptionPane;
import vista.IOptionPane;
import vista.IVista;
import vista.Ventana;


/**
 * Controlador con escenario empresa vacia
 */
public class ControladorTest {
	
	Controlador control = new Controlador();
	IVista vista = mock(Ventana.class);
	IOptionPane panel = new DefaultOptionPane();

	
	@Before
	public void setUp() {
		control.setVista(vista);
		when(control.getVista().getOptionPane()).thenReturn(panel);
	}
	
	@Test
	public void test() {
		
		when(vista.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
		when(vista.getNombreChofer()).thenReturn("Raul Perez");
		when(vista.getDNIChofer()).thenReturn("1234");
		
		control.nuevoChofer();
		
	}

	/**
	 * Test Usuario inexistente
	 */
	@Test
	public void testLogin() {
		when(vista.getUsserName()).thenReturn("usuario");
		when(vista.getPassword()).thenReturn("password");
		control.login();
	}

	/**
	 * TEst contrasena no coinciden
	 */
	@Test
	public void testRegistrar() {
		when(vista.getRegNombreReal()).thenReturn("Pepe Lopez");
		when(vista.getRegUsserName()).thenReturn("usuario");
		when(vista.getRegPassword()).thenReturn("password");
		when(vista.getRegConfirmPassword()).thenReturn("password1");
		
		control.registrar();
		
		System.out.println(panel);
	}
	
}
