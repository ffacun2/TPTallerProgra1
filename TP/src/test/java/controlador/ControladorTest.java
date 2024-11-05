package controlador;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import excepciones.UsuarioYaExisteException;
import modeloDatos.Cliente;
import modeloNegocio.Empresa;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
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
	
	Empresa empresa;
	Controlador control;
	IVista vista = mock(Ventana.class);
	IOptionPane panel ;

	
	@Before
	public void setUp() {
		control = new Controlador();
		panel = new OptionPane();
		empresa = Empresa.getInstance();
		control.setVista(vista);
		when(control.getVista().getOptionPane()).thenReturn(panel);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(control.getVista());
		assertNotNull(control.getPersistencia());
		assertEquals(control.getFileName(),"empresa.bin");
	}
	
	/**
	 * Test Usuario inexistente
	 */
	@Test
	public void testLogin() {
		when(control.getVista().getUsserName()).thenReturn("usuario");
		when(control.getVista().getPassword()).thenReturn("password");
		control.login();
		assertEquals(((OptionPane)panel).getMsj(),Mensajes.USUARIO_DESCONOCIDO.getValor());
	}
	
	
	/**
	 * TEst registrar contrasena no coinciden
	 */
	@Test
	public void testRegistrar() {
		when(vista.getRegNombreReal()).thenReturn("Pepe Lopez");
		when(vista.getRegUsserName()).thenReturn("usuario");
		when(vista.getRegPassword()).thenReturn("password");
		when(vista.getRegConfirmPassword()).thenReturn("password1");
		
		control.registrar();
		assertEquals(((OptionPane)panel).getMsj(),Mensajes.PASS_NO_COINCIDE.getValor());
	}
	
	/**
	 * Agrega un nuevo usuario no repetido
	 */
	@Test
	public void testRegistrar2() {
		when(vista.getRegNombreReal()).thenReturn("Pepe Lopez");
		when(vista.getRegUsserName()).thenReturn("usuario");
		when(vista.getRegPassword()).thenReturn("password");
		when(vista.getRegConfirmPassword()).thenReturn("password");
		
		control.registrar();
		assertTrue(empresa.getClientes().containsKey(control.getVista().getRegUsserName()));
	}
	
	
	/**
	 * Agrega nuevo chofer Temporario no repetido
	 */
	@Test
	public void testNuevoChofer() {
		when(vista.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
		when(vista.getNombreChofer()).thenReturn("Raul Perez");
		when(vista.getDNIChofer()).thenReturn("1234");
		
		control.nuevoChofer();
		assertTrue(empresa.getChoferes().containsKey(control.getVista().getDNIChofer()));
	}

	/**
	 * Agrega nuevo chofer Permanente no repetido
	 */
	@Test
	public void testNuevoChofer2() {
		when(vista.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
		when(vista.getNombreChofer()).thenReturn("Raul Perez");
		when(vista.getDNIChofer()).thenReturn("12345");
		when(vista.getAnioChofer()).thenReturn(2000);
		when(vista.getHijosChofer()).thenReturn(2);
		
		control.nuevoChofer();
		assertTrue(empresa.getChoferes().containsKey(control.getVista().getDNIChofer()));
	}
	
	/**
	 * Agrega un nuevo vehiculo no repetido
	 */
	@Test
	public void testNuevoVehiculo() {
		when(vista.getTipoVehiculo()).thenReturn(Constantes.AUTO);
		when(vista.getPatente()).thenReturn("123 asd");
		when(vista.getPlazas()).thenReturn(3);
		when(vista.isVehiculoAptoMascota()).thenReturn(true);
		
		assertNull(((OptionPane)(control.getVista().getOptionPane())).getMsj());
	}
		
}
