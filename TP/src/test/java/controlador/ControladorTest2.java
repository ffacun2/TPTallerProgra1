package controlador;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;
import util.Constantes;
import util.Mensajes;
import vista.IOptionPane;
import vista.IVista;
import vista.Ventana;

/**
 * Escenario con empresa con elementos en las listas de 
 * vehiculo, clientes, chofer
 */
public class ControladorTest2 {

	Empresa empresa;
	Controlador control;
	IVista vista = mock(Ventana.class);
	IOptionPane panel;
	
	Cliente c1 ;
	Vehiculo a1 = new Auto("asd 123",4,true);
	Chofer ch1 = new ChoferPermanente("1234","Juan Pepe",2000, 1);
	Vehiculo a2 = new Combi("qwe 123",6,false);
	
	@Before
	public void setUp() throws Exception{
		control = new Controlador();
		panel = new OptionPane();
		empresa = Empresa.getInstance();
		control.setVista(vista);
		when(control.getVista().getOptionPane()).thenReturn(panel);
		
		empresa.agregarChofer(ch1);
		empresa.agregarVehiculo(a1);
		empresa.agregarVehiculo(a2);
		empresa.agregarCliente("usuario", "password","Raul Perez");
		
		c1 = empresa.getClientes().get("usuario");
		empresa.setUsuarioLogeado(c1);
	}
	
	@After
	public void tearDown() {
		empresa.getChoferes().clear();
		empresa.getChoferesDesocupados().clear();
		empresa.getClientes().clear();
		empresa.getVehiculos().clear();
		empresa.getVehiculosDesocupados().clear();
		empresa.setUsuarioLogeado(null);
	}
	
	/**
	 * Test contrasena erronea
	 */
	@Test
	public void testLogin() {
		when(control.getVista().getUsserName()).thenReturn("usuario");
		when(control.getVista().getPassword()).thenReturn("1234");
		
		control.login();
		
		assertEquals(((OptionPane)panel).getMsj(),Mensajes.PASS_ERRONEO.getValor());
	}
	
	/**
	 * Test login correcto
	 */
	@Test
	public void testLogin2() {
		when(control.getVista().getUsserName()).thenReturn("usuario");
		when(control.getVista().getPassword()).thenReturn("password");
		
		control.login();
		assertNull(((OptionPane)panel).getMsj());
		assertEquals(empresa.getUsuarioLogeado(),c1);
	}
	
	/**
	 * TEst registrar usuario existente
	 */
	@Test
	public void testRegistrar() {
		when(control.getVista().getRegUsserName()).thenReturn("usuario");
		when(control.getVista().getRegNombreReal()).thenReturn("Raul Peres");
		when(control.getVista().getRegPassword()).thenReturn("password");
		when(control.getVista().getRegConfirmPassword()).thenReturn("password");
		
		control.registrar();
		assertEquals(Mensajes.USUARIO_REPETIDO.getValor(),((OptionPane)panel).getMsj());
		assertEquals(empresa.getClientes().size(),1);
	}
	
	/**
	 * Test registro de vehiculo repetido
	 */
	@Test
	public void testNuevoVehiculo() {
		when(control.getVista().getTipoVehiculo()).thenReturn(Constantes.AUTO);
		when(control.getVista().getPatente()).thenReturn("asd 123");
		when(control.getVista().getPlazas()).thenReturn(4);
		when(control.getVista().isVehiculoAptoMascota()).thenReturn(true);
		control.nuevoVehiculo();
		assertEquals(Mensajes.VEHICULO_YA_REGISTRADO.getValor(),((OptionPane)panel).getMsj());
	}
	
	/**
	 * TEst registro de chofer existente
	 */
	@Test
	public void testNuevoChofer() {
		when(vista.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
		when(vista.getNombreChofer()).thenReturn("Juan Pepe");
		when(vista.getDNIChofer()).thenReturn("1234");
		when(vista.getAnioChofer()).thenReturn(2000);
		when(vista.getHijosChofer()).thenReturn(2);	
		
		control.nuevoChofer();
		assertEquals(empresa.getChoferes().size(),1);
		assertEquals(Mensajes.CHOFER_YA_REGISTRADO,((OptionPane)panel).getMsj());
	}
	
	/**
	 * Test nuevo pedido con cliente valido logeado
	 */
	@Test
	public void testNuevoPedido() {
		when(control.getVista().getCantidadPax()).thenReturn(3);
		when(control.getVista().isPedidoConMascota()).thenReturn(false);
		when(control.getVista().isPedidoConBaul()).thenReturn(true);
		when(control.getVista().getCantKm()).thenReturn(10);
		when(control.getVista().getTipoZona()).thenReturn(Constantes.ZONA_PELIGROSA);
		
		control.nuevoPedido();
		assertNull(((OptionPane)panel).getMsj());
	}
	
	/**
	 *  Test nuevo pedido donde no hay vehiculo que lo satisfaga
	 */
	@Test
	public void testNuevoPedido2() {
		when(control.getVista().getCantidadPax()).thenReturn(8);
		when(control.getVista().isPedidoConMascota()).thenReturn(false);
		when(control.getVista().isPedidoConBaul()).thenReturn(true);
		when(control.getVista().getCantKm()).thenReturn(10);
		when(control.getVista().getTipoZona()).thenReturn(Constantes.ZONA_PELIGROSA);
		
		control.nuevoPedido();
		assertEquals(Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(),((OptionPane)panel).getMsj());
	}
}
