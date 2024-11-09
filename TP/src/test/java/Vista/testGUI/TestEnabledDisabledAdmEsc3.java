package Vista.testGUI;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Vista.TestUtils;
import controlador.Controlador;
import junit.framework.Assert;
import modelo.negocio.Escenario3;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class TestEnabledDisabledAdmEsc3 {
	Robot robot;
    Controlador controlador;
    private Escenario3 escenario = new Escenario3(); 

    public TestEnabledDisabledAdmEsc3()
    {
        try{
            robot = new Robot();
        } catch (AWTException e){
        }
    }

    @Before
    public void setUp() throws Exception
    {
        controlador = new Controlador();
        escenario.setUp();
        
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JButton botonLogin = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("admin", robot);
        
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin", robot);

        TestUtils.clickComponent(botonLogin, robot);
    }

    @After
    public void tearDown() throws Exception
    {
        Ventana ventana = (Ventana) controlador.getVista();
    	ventana.setVisible(false);
    	escenario.tearDown();
    }

//PANEL ADMINISTRADOR:Gestion de Pedidos
    
    @Test
    public void testPanelGestionDePedidos_Completo()
    {
        robot.delay(TestUtils.getDelay());
        JList<?> listaPedidosPendientes = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        JList<?> listaChoferesLibres = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        JList<?> listaVehiculosDisponibles = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        JButton nuevoViaje = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);

        listaPedidosPendientes.setSelectedIndex(0);
        listaChoferesLibres.setSelectedIndex(0);
        listaVehiculosDisponibles.setSelectedIndex(0);
        
        TestUtils.clickComponent(listaPedidosPendientes, robot);
        TestUtils.clickComponent(listaChoferesLibres, robot);
        TestUtils.clickComponent(listaVehiculosDisponibles, robot);
        
        Assert.assertTrue("El boton de Nuevo Viaje deberia estar hablitado", nuevoViaje.isEnabled());
    }
    
    @Test
    public void testPanelGestionDePedidos_SinSeleccionar()
    {
        robot.delay(TestUtils.getDelay());
        JButton nuevoViaje = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);
        
        Assert.assertFalse("El boton de Nuevo Viaje deberia estar deshablitado", nuevoViaje.isEnabled());
    }
    
    @Test
    public void testPanelGestionDePedidos_SoloPedidoPendiente()
    {
        robot.delay(TestUtils.getDelay());
        JList<?> listaPedidosPendientes = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        JButton nuevoViaje = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);

        listaPedidosPendientes.setSelectedIndex(0);
        
        TestUtils.clickComponent(listaPedidosPendientes, robot);
        
        Assert.assertFalse("El boton de Nuevo Viaje deberia estar deshablitado", nuevoViaje.isEnabled());
    }
    
    @Test
    public void testPanelGestionDePedidos_SoloChoferLibre()
    {
        robot.delay(TestUtils.getDelay());
        JList<?> listaChoferesLibres = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        JButton nuevoViaje = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);

        listaChoferesLibres.setSelectedIndex(0);
        
        TestUtils.clickComponent(listaChoferesLibres, robot);
        
        Assert.assertFalse("El boton de Nuevo Viaje deberia estar deshablitado", nuevoViaje.isEnabled());
    }
    
    @Test
    public void testPanelGestionDePedidos_SoloVehiculoDisponible()
    {
        robot.delay(TestUtils.getDelay());
        JList<?> listaVehiculosDisponibles = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        JButton nuevoViaje = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);

        listaVehiculosDisponibles.setSelectedIndex(0);
        
        TestUtils.clickComponent(listaVehiculosDisponibles, robot);
        
        Assert.assertFalse("El boton de Nuevo Viaje deberia estar deshablitado", nuevoViaje.isEnabled());
    }

    //CHEQUEAR
    @Test
    public void testPanelGestionDePedidos_NuevoViaje()
    {
        robot.delay(5000);
        JList<?> listaPedidosPendientes = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        JList<?> listaChoferesLibres = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        JList<?> listaVehiculosDisponibles = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        JButton nuevoViaje = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VIAJE);

        listaPedidosPendientes.setSelectedIndex(0);
        listaChoferesLibres.setSelectedIndex(0);
        listaVehiculosDisponibles.setSelectedIndex(0);
        
        TestUtils.clickComponent(listaPedidosPendientes, robot);
        TestUtils.clickComponent(listaChoferesLibres, robot);
        TestUtils.clickComponent(listaVehiculosDisponibles, robot);
        
        int pedidosPendientesSizeInicial = listaPedidosPendientes.getModel().getSize();
        int choferesLibresSizeInicial = listaChoferesLibres.getModel().getSize();
        int vehiculosDisponiblesSizeInicial = listaVehiculosDisponibles.getModel().getSize();

        TestUtils.clickComponent(nuevoViaje, robot);
        Assert.assertFalse("El botón de Nuevo Viaje debería estar deshabilitado", nuevoViaje.isEnabled());

        Assert.assertEquals("La lista de pedidos pendientes debería haber reducido su tamaño en 1", pedidosPendientesSizeInicial - 1, listaPedidosPendientes.getModel().getSize());
        Assert.assertEquals("La lista de choferes libres debería haber reducido su tamaño en 1", choferesLibresSizeInicial - 1, listaChoferesLibres.getModel().getSize());
        Assert.assertEquals("La lista de vehículos disponibles debería estar vacía", 0, listaVehiculosDisponibles.getModel().getSize());
    }


}
