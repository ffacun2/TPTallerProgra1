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
import modelo.negocio.Escenario4;
import util.Constantes;
import vista.Ventana;

//ESCENARIO 4 TIENE VIAJES PENDIENTES
public class TestEnabledDisabledEsc4 {
	Robot robot;
    Controlador controlador;
    private Escenario4 escenario = new Escenario4(); 

    public TestEnabledDisabledEsc4()
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
 
// TEST PANEL CLIENTE : Pendiente Actual
    
    @Test
    public void testPanelCliente_ViajePendiente()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JRadioButton radioSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        JRadioButton radioPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);

        Assert.assertFalse("El campo de Cantidad de Pasajeros deberia estar deshablitado", campoCantPax.isEnabled());
        Assert.assertFalse("El campo de Cantidad de Kilometros deberia estar deshablitado", campoKM.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Standard deberia estar deshablitado", radioStandard.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Sin Asfaltar deberia estar deshablitado", radioSinAsfaltar.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Peligrosa deberia estar deshablitado", radioPeligrosa.isEnabled());
        Assert.assertFalse("El checkBox de Baul deberia estar deshablitado", checkBoxBaul.isEnabled());
        Assert.assertFalse("El checkBox de Mascota Pedido deberia estar deshablitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
        
        JTextField campoCalificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField campoValor = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);
        JButton botonCalificarPagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);

        Assert.assertTrue("El campo de Calificar Viaje deberia estar hablitado", campoCalificacion.isEnabled());
        Assert.assertFalse("El campo de Valor del Viaje deberia estar vacio", campoValor.getText().isEmpty());
        Assert.assertFalse("El boton de Pagar deberia estar deshablitado", botonCalificarPagar.isEnabled());
    }
    
    @Test
    public void testPanelCliente_PagarViaje()  
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JRadioButton radioSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        JRadioButton radioPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);

        Assert.assertFalse("El campo de Cantidad de Pasajeros deberia estar deshablitado", campoCantPax.isEnabled());
        Assert.assertFalse("El campo de Cantidad de Kilometros deberia estar deshablitado", campoKM.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Standard deberia estar deshablitado", radioStandard.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Sin Asfaltar deberia estar deshablitado", radioSinAsfaltar.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Peligrosa deberia estar deshablitado", radioPeligrosa.isEnabled());
        Assert.assertFalse("El checkBox de Baul deberia estar deshablitado", checkBoxBaul.isEnabled());
        Assert.assertFalse("El checkBox de Mascota Pedido deberia estar deshablitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
        
        JTextField campoCalificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField campoValor = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);
        JButton botonCalificarPagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);

        Assert.assertTrue("El campo de Calificar Viaje deberia estar hablitado", campoCalificacion.isEnabled());
        Assert.assertFalse("El campo de Valor del Viaje deberia estar vacio", campoValor.getText().isEmpty());
        
        TestUtils.clickComponent(campoCalificacion, robot);
        TestUtils.tipeaTexto("3", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Pagar deberia estar hablitado", botonCalificarPagar.isEnabled());
    }
    
    @Test
    public void testPanelCliente_CalificarValorLimite0()  
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JRadioButton radioSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        JRadioButton radioPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);

        Assert.assertFalse("El campo de Cantidad de Pasajeros deberia estar deshablitado", campoCantPax.isEnabled());
        Assert.assertFalse("El campo de Cantidad de Kilometros deberia estar deshablitado", campoKM.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Standard deberia estar deshablitado", radioStandard.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Sin Asfaltar deberia estar deshablitado", radioSinAsfaltar.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Peligrosa deberia estar deshablitado", radioPeligrosa.isEnabled());
        Assert.assertFalse("El checkBox de Baul deberia estar deshablitado", checkBoxBaul.isEnabled());
        Assert.assertFalse("El checkBox de Mascota Pedido deberia estar deshablitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
        
        JTextField campoCalificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField campoValor = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);
        JButton botonCalificarPagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);

        Assert.assertTrue("El campo de Calificar Viaje deberia estar hablitado", campoCalificacion.isEnabled());
        Assert.assertFalse("El campo de Valor del Viaje deberia estar vacio", campoValor.getText().isEmpty());
        
        TestUtils.clickComponent(campoCalificacion, robot);
        TestUtils.tipeaTexto("0", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Pagar deberia estar hablitado", botonCalificarPagar.isEnabled());
    }
    
    @Test
    public void testPanelCliente_CalificarValorLimite5()  
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JRadioButton radioSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        JRadioButton radioPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);

        Assert.assertFalse("El campo de Cantidad de Pasajeros deberia estar deshablitado", campoCantPax.isEnabled());
        Assert.assertFalse("El campo de Cantidad de Kilometros deberia estar deshablitado", campoKM.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Standard deberia estar deshablitado", radioStandard.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Sin Asfaltar deberia estar deshablitado", radioSinAsfaltar.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Peligrosa deberia estar deshablitado", radioPeligrosa.isEnabled());
        Assert.assertFalse("El checkBox de Baul deberia estar deshablitado", checkBoxBaul.isEnabled());
        Assert.assertFalse("El checkBox de Mascota Pedido deberia estar deshablitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
        
        JTextField campoCalificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField campoValor = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);
        JButton botonCalificarPagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);

        Assert.assertTrue("El campo de Calificar Viaje deberia estar hablitado", campoCalificacion.isEnabled());
        Assert.assertFalse("El campo de Valor del Viaje deberia estar vacio", campoValor.getText().isEmpty());
        
        TestUtils.clickComponent(campoCalificacion, robot);
        TestUtils.tipeaTexto("5", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Pagar deberia estar hablitado", botonCalificarPagar.isEnabled());
    }
    
    @Test
    public void testPanelCliente_CalificarFueraDeRango()  
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoCantPax = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_PAX);
        JTextField campoKM = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANT_KM);
        JRadioButton radioStandard = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_STANDARD);
        JRadioButton radioSinAsfaltar = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        JRadioButton radioPeligrosa = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.ZONA_PELIGROSA);
        JCheckBox checkBoxBaul = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_BAUL);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_MASCOTA);
        JButton botonNuevoPedido = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_PEDIDO);

        Assert.assertFalse("El campo de Cantidad de Pasajeros deberia estar deshablitado", campoCantPax.isEnabled());
        Assert.assertFalse("El campo de Cantidad de Kilometros deberia estar deshablitado", campoKM.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Standard deberia estar deshablitado", radioStandard.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Sin Asfaltar deberia estar deshablitado", radioSinAsfaltar.isEnabled());
        Assert.assertFalse("La opcion de Topo de Zona Peligrosa deberia estar deshablitado", radioPeligrosa.isEnabled());
        Assert.assertFalse("El checkBox de Baul deberia estar deshablitado", checkBoxBaul.isEnabled());
        Assert.assertFalse("El checkBox de Mascota Pedido deberia estar deshablitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El boton de Nuevo Pedido deberia estar deshablitado", botonNuevoPedido.isEnabled());
        
        JTextField campoCalificacion = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField campoValor = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.VALOR_VIAJE);
        JButton botonCalificarPagar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CALIFICAR_PAGAR);

        Assert.assertTrue("El campo de Calificar Viaje deberia estar hablitado", campoCalificacion.isEnabled());
        Assert.assertFalse("El campo de Valor del Viaje deberia estar vacio", campoValor.getText().isEmpty());
        
        TestUtils.clickComponent(campoCalificacion, robot);
        TestUtils.tipeaTexto("10", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El boton de Pagar deberia estar deshablitado", botonCalificarPagar.isEnabled());
    }
}
