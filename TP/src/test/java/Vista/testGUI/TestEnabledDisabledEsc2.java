package Vista.testGUI;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Vista.TestUtils;
import controlador.Controlador;
import junit.framework.Assert;
import modelo.negocio.Escenario2;
import util.Constantes;
import vista.Ventana;

//ESCENARIO 2 SOLO TIENE ELEMENTOS BASE
public class TestEnabledDisabledEsc2 {
	Robot robot;
    Controlador controlador;
    private Escenario2 escenario = new Escenario2(); 

    public TestEnabledDisabledEsc2()
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
        TestUtils.tipeaTexto("user1", robot);
        
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("pass1", robot);

        TestUtils.clickComponent(botonLogin, robot);
    }

    @After
    public void tearDown() throws Exception
    {
        Ventana ventana = (Ventana) controlador.getVista();
    	ventana.setVisible(false);
    	escenario.tearDown();
    }
 
// TEST PANEL CLIENTE
    
    @Test
    public void testPanelCliente_CerrarSesionCliente()
    {
        robot.delay(TestUtils.getDelay());
        JButton botonLogout = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);
        
        Assert.assertTrue("El boton de Cerrar Sesion Cliente deberia estar hablitado", botonLogout.isEnabled());
    }
    
// TEST PANEL CLIENTE : Pendiente Actual
    
    @Test
    public void testPanelCliente_SinPendientes()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCalificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField campoValor = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);
        JButton botonCalificarPagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
        
        Assert.assertFalse("El campo de Calificar Viaje deberia estar deshablitado", campoCalificacion.isEnabled());
        Assert.assertTrue("El campo de Valor del Viaje deberia estar vacio", campoValor.getText().isEmpty());
        Assert.assertFalse("El boton de Pagar deberia estar deshablitado", botonCalificarPagar.isEnabled());
    }
    
// TEST PANEL CLIENTE : NUEVO PEDIDO
    
    @Test
    public void testNuevoPedido_CamposCompletos()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(campoCantPax, robot);
        TestUtils.tipeaTexto("5", robot);
        
        TestUtils.clickComponent(campoKM, robot);
        TestUtils.tipeaTexto("10", robot);
        
        TestUtils.clickComponent(radioStandard, robot);
        TestUtils.clickComponent(checkBoxBaul, robot);
        TestUtils.clickComponent(checkBoxMascota, robot);
        
        Assert.assertTrue("El boton de Nuevo Pedido deberia estar hablitado", botonNuevoPedido.isEnabled());
    }
    
    @Test
    public void testNuevoPedido_CamposVacios()
    {
        robot.delay(TestUtils.getDelay());
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
    }
    
    @Test
    public void testNuevoPedido_SoloCantPax()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(campoCantPax, robot);
        TestUtils.tipeaTexto("3", robot);
        
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
    }
    
    @Test
    public void testNuevoPedido_SoloCantKM()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(campoCantKM, robot);
        TestUtils.tipeaTexto("10", robot);
        
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
    }
    
    @Test
    public void testNuevoPedido_SoloZona()
    {
        robot.delay(TestUtils.getDelay());
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(radioStandard, robot);
        
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
    }
    
    @Test
    public void testNuevoPedido_SoloMascota()
    {
        robot.delay(TestUtils.getDelay());
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(checkBoxMascota, robot);
        
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
    }

    @Test
    public void testNuevoPedido_CantPaxValorLimite1()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(campoCantPax, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoKM, robot);
        TestUtils.tipeaTexto("10", robot);
        
        TestUtils.clickComponent(radioStandard, robot);
        TestUtils.clickComponent(checkBoxBaul, robot);
        TestUtils.clickComponent(checkBoxMascota, robot);
        
        Assert.assertTrue("El boton de Nuevo Pedido deberia estar hablitado", botonNuevoPedido.isEnabled());
    }
    
    public void testNuevoPedido_CantPaxValorLimite10()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(campoCantPax, robot);
        TestUtils.tipeaTexto("10", robot);
        
        TestUtils.clickComponent(campoKM, robot);
        TestUtils.tipeaTexto("10", robot);
        
        TestUtils.clickComponent(radioStandard, robot);
        TestUtils.clickComponent(checkBoxBaul, robot);
        TestUtils.clickComponent(checkBoxMascota, robot);
        
        Assert.assertTrue("El boton de Nuevo Pedido deberia estar hablitado", botonNuevoPedido.isEnabled());
    }
    
    public void testNuevoPedido_CantPaxFueraDeRango()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        
        TestUtils.clickComponent(campoCantPax, robot);
        TestUtils.tipeaTexto("15", robot);
        
        TestUtils.clickComponent(campoKM, robot);
        TestUtils.tipeaTexto("10", robot);
        
        TestUtils.clickComponent(radioStandard, robot);
        TestUtils.clickComponent(checkBoxBaul, robot);
        TestUtils.clickComponent(checkBoxMascota, robot);
        
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
    }



}
