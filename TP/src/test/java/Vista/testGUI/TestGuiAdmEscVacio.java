package Vista.testGUI;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Vista.FalseOptionPanel;
import Vista.TestUtils;
import controlador.Controlador;
import junit.framework.Assert;
import modelo.negocio.EscenarioBase;
import util.Constantes;
import vista.Ventana;

public class TestGuiAdmEscVacio {
	Robot robot;
    Controlador controlador;
    FalseOptionPanel op = new FalseOptionPanel();
    private EscenarioBase escenario = new EscenarioBase(); 

    public TestGuiAdmEscVacio()
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
        controlador.getVista().setOptionPane(op);
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
    	escenario.empresa.logout();
    }

  //TEST PANEL ADMINISTRADOR: Alta Chofer

    @Test
    public void testPanelAdm_AltaVehiculoExitoso()
    {
  		robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);
        JList<?> listaChoferesTotales = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_TOTALES);
        JList<?> listaChoferesLibres = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        
        ListModel<?> modeloChoferesTotales = listaChoferesTotales.getModel();
        int cantidadChoferesInicial = modeloChoferesTotales.getSize();
        
        ListModel<?> modeloChoferesLibres = listaChoferesLibres.getModel();
        int cantidadChoferesLibresInicial = modeloChoferesLibres.getSize();
        
        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioPermanente, robot);
        
        TestUtils.clickComponent(campoCantHijos, robot);
        TestUtils.tipeaTexto("3", robot);
        
        TestUtils.clickComponent(campoAnio, robot);
        TestUtils.tipeaTexto("2000", robot);
        
        TestUtils.clickComponent(altaChofer, robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El campo DNI deberia estar vacio", campoDNI.getText().isEmpty());
        Assert.assertFalse("El campo Nombre deberia estar vacio", campoNombre.getText().isEmpty());
        Assert.assertFalse("El campo Cantidad de Hijos deberia estar vacio", campoCantHijos.getText().isEmpty());
        Assert.assertFalse("El campo Año de Ingreso deberia estar vacio", campoAnio.getText().isEmpty());
        
        int cantidadChoferesFinal = modeloChoferesTotales.getSize();
        Assert.assertEquals("listaChoferesTotales: La cantidad de choferes deberia haber aumentado", cantidadChoferesInicial + 1, cantidadChoferesFinal);
        
        int cantidadChoferesLibresFinal = modeloChoferesLibres.getSize();
        Assert.assertEquals("listaChoferesLibres: La cantidad de choferes deberia haber aumentado", cantidadChoferesLibresInicial + 1, cantidadChoferesLibresFinal);
    }

    @Test
    public void testPanelAdm_AltaAutoExitoso()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);
        JList<?> listaVehiculosTotales = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_TOTALES);
        JList<?> listaVehiculosDisponibles = (JList<?>) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        
        ListModel<?> modeloVehiculosTotales = listaVehiculosTotales.getModel();
        int cantidadVehiculosTotalesInicial = modeloVehiculosTotales.getSize();
        
        ListModel<?> modeloVehiculosDisponibles = listaVehiculosDisponibles.getModel();
        int cantidadVehiculosDisponiblesInicial = modeloVehiculosDisponibles.getSize();
        
        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("2", robot);
        
        TestUtils.clickComponent(altaVehiculo, robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El campo Patente deberia estar vacio", campoPatente.getText().isEmpty());
        Assert.assertFalse("El campo Cantidad de Plazas deberia estar vacio", campoCantPlazas.getText().isEmpty());
        
        int cantidadVehiculosTotalesFinal = modeloVehiculosTotales.getSize();
        Assert.assertEquals("listaVehiculosTotales: La cantidad de vehiculos deberia haber aumentado en uno", cantidadVehiculosTotalesInicial + 1, cantidadVehiculosTotalesFinal);
        
        int cantidadVehiculosDisponiblesFinal = modeloVehiculosDisponibles.getSize();
        Assert.assertEquals("listaVehiculosDisponibles: La cantidad de vehiculos deberia haber aumentado en uno", cantidadVehiculosDisponiblesInicial + 1, cantidadVehiculosDisponiblesFinal);
    }
    
}
