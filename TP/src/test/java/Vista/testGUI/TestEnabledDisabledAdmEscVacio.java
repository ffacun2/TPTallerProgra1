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
import modelo.negocio.EscenarioBase;
import util.Constantes;
import vista.Ventana;

public class TestEnabledDisabledAdmEscVacio {
	Robot robot;
    Controlador controlador;
    private EscenarioBase escenario = new EscenarioBase(); 

    public TestEnabledDisabledAdmEscVacio()
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

//PANEL ADMINISTRADOR: Altas Nuevo Chofer

    @Test
    public void testPanelAltas_ChoferCamposVacios()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        Assert.assertTrue("La opcion de Cantidad de Hijos deberia estar hablitado", campoDNI.isEnabled());
        Assert.assertTrue("La opcion de Cantidad de Hijos deberia estar hablitado", campoNombre.isEnabled());
        Assert.assertTrue("La opcion de Chofer Permanente deberia estar seleccionada por defecto", radioPermanente.isSelected());
        Assert.assertTrue("La opcion de Cantidad de Hijos deberia estar hablitado", campoCantHijos.isEnabled());
        Assert.assertTrue("La opcion de Año de Ingreso deberia estar hablitado", campoAnio.isEnabled());
        Assert.assertFalse("El boton de Aceptar Chofer deberia estar deshablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferTemporarioCompleto()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioTemporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioTemporario, robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("La opcion de Cantidad de Hijos deberia estar hablitado", campoCantHijos.isEnabled());
        Assert.assertFalse("La opcion de Año de Ingreso deberia estar hablitado", campoAnio.isEnabled());
        Assert.assertTrue("El boton de Aceptar Chofer deberia estar hablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteCompleto()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioPermanente, robot);
        
        TestUtils.clickComponent(campoCantHijos, robot);
        TestUtils.tipeaTexto("2", robot);
        
        TestUtils.clickComponent(campoAnio, robot);
        TestUtils.tipeaTexto("2000", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Aceptar Chofer deberia estar hablitado", altaChofer.isEnabled());
    } 
    
    @Test
    public void testPanelAltas_ChoferPermanenteCantHijosValorLimite0()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioPermanente, robot);
        
        TestUtils.clickComponent(campoCantHijos, robot);
        TestUtils.tipeaTexto("0", robot);
        
        TestUtils.clickComponent(campoAnio, robot);
        TestUtils.tipeaTexto("2000", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Aceptar Chofer deberia estar hablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteCantHijosValorLimite2000()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioPermanente, robot);
        
        TestUtils.clickComponent(campoCantHijos, robot);
        TestUtils.tipeaTexto("2000", robot);
        
        TestUtils.clickComponent(campoAnio, robot);
        TestUtils.tipeaTexto("2000", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Aceptar Chofer deberia estar hablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteAnioValorLimite1900()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioPermanente, robot);
        
        TestUtils.clickComponent(campoCantHijos, robot);
        TestUtils.tipeaTexto("2", robot);
        
        TestUtils.clickComponent(campoAnio, robot);
        TestUtils.tipeaTexto("1900", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Aceptar Chofer deberia estar hablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteAnioValorLimite3000()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_ANIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        
        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioPermanente, robot);
        
        TestUtils.clickComponent(campoCantHijos, robot);
        TestUtils.tipeaTexto("2", robot);
        
        TestUtils.clickComponent(campoAnio, robot);
        TestUtils.tipeaTexto("3000", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El boton de Aceptar Chofer deberia estar hablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteSoloDNI()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoDNI = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.DNI_CHOFER);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoDNI, robot);
        TestUtils.tipeaTexto("1", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El boton de Aceptar Chofer deberia estar deshablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteSoloNombre()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoNombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(campoNombre, robot);
        TestUtils.tipeaTexto("a", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El boton de Aceptar Chofer deberia estar deshablitado", altaChofer.isEnabled());
    }

    @Test
    public void testPanelAltas_ChoferPermanenteSoloRadioPermanente()
    {
        robot.delay(TestUtils.getDelay());
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(radioPermanente, robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El boton de Aceptar Chofer deberia estar deshablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteSoloRadioPermanenteYHijos()
    {
        robot.delay(TestUtils.getDelay());
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoCantHijos = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(radioPermanente, robot);

        TestUtils.clickComponent(campoCantHijos, robot);
        TestUtils.tipeaTexto("2", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El boton de Aceptar Chofer deberia estar deshablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteSoloRadioPermanenteYAnio()
    {
        robot.delay(TestUtils.getDelay());
        JRadioButton radioPermanente = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PERMANENTE);
        JTextField campoAnio = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(radioPermanente, robot);

        TestUtils.clickComponent(campoAnio, robot);
        TestUtils.tipeaTexto("2000", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El boton de Aceptar Chofer deberia estar deshablitado", altaChofer.isEnabled());
    }
    
    @Test
    public void testPanelAltas_ChoferPermanenteSoloRadioTemporario()
    {
        robot.delay(TestUtils.getDelay());
        JRadioButton radioTemporario = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.TEMPORARIO);
        JButton altaChofer = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_CHOFER);

        TestUtils.clickComponent(radioTemporario, robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertFalse("El boton de Aceptar Chofer deberia estar deshablitado", altaChofer.isEnabled());
    }

  //PANEL ADMINISTRADOR: Altas Nuevo Vehiculo

    @Test
    public void testPanelAltas_VehiculoCamposVacios()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Auto deberia estar seleccionada por defecto", radioAuto.isSelected());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El checkBox de Mascota no debería estar seleccionado por defecto", checkBoxMascota.isSelected());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertFalse("El boton de Aceptar Vehiculo deberia estar deshablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_VehiculoSoloPatente()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Auto deberia estar seleccionada por defecto", radioAuto.isSelected());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El checkBox de Mascota no debería estar seleccionado por defecto", checkBoxMascota.isSelected());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertFalse("El boton de Aceptar Vehiculo deberia estar deshablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_VehiculoSoloCantPlazas()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("1", robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Auto deberia estar seleccionada por defecto", radioAuto.isSelected());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El checkBox de Mascota no debería estar seleccionado por defecto", checkBoxMascota.isSelected());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertFalse("El boton de Aceptar Vehiculo deberia estar deshablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_VehiculoSoloMascota()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(checkBoxMascota, robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Auto deberia estar seleccionada por defecto", radioAuto.isSelected());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El checkBox de Mascota no debería estar seleccionado por defecto", checkBoxMascota.isSelected());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertFalse("El boton de Aceptar Vehiculo deberia estar deshablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegMoto()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioMoto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.MOTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(radioMoto, robot);
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Moto deberia estar seleccionada", radioMoto.isSelected());
        Assert.assertFalse("El checkBox de Mascota debería estar deshabilitado", checkBoxMascota.isEnabled());
        Assert.assertFalse("El campo de la Cantidad de Plazas deberia estar deshablitado", campoCantPlazas.isEnabled());
        Assert.assertTrue("El boton de Aceptar Vehiculo deberia estar hablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegAuto()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("2", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertTrue("El boton de Aceptar Vehiculo deberia estar hablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegAutoCantPlazasValorLimite1()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("1", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Auto deberia estar seleccionada por defecto", radioAuto.isSelected());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertTrue("El boton de Aceptar Vehiculo deberia estar hablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegAutoCantPlazasValorLimite4()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("4", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Auto deberia estar seleccionada por defecto", radioAuto.isSelected());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertTrue("El boton de Aceptar Vehiculo deberia estar hablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegAutoCantPlazasFueraDeRango()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioAuto = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.AUTO);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("0", robot);
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("La opcion de Auto deberia estar seleccionada por defecto", radioAuto.isSelected());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertFalse("El boton de Aceptar Vehiculo deberia estar deshablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegCombi()
    {
    	robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioCombi = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.COMBI);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(radioCombi, robot);
        
        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("7", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertTrue("El boton de Aceptar Vehiculo deberia estar hablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegCombiCantPlazasValorLimite5()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioCombi = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.COMBI);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(radioCombi, robot);
        
        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("5", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertTrue("El boton de Aceptar Vehiculo deberia estar hablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegCombiCantPlazasValorLimite10()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioCombi = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.COMBI);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(radioCombi, robot);
        
        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("10", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertTrue("El boton de Aceptar Vehiculo deberia estar hablitado", altaVehiculo.isEnabled());
    }
    
    @Test
    public void testPanelAltas_RegCombiCantPlazasFueraDeRango()
    {
        robot.delay(TestUtils.getDelay());
        JTextField campoPatente = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PATENTE);
        JRadioButton radioCombi = (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.COMBI);
        JCheckBox checkBoxMascota = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CHECK_VEHICULO_ACEPTA_MASCOTA);
        JTextField campoCantPlazas = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        JButton altaVehiculo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NUEVO_VEHICULO);

        TestUtils.clickComponent(radioCombi, robot);
        
        TestUtils.clickComponent(campoPatente, robot);
        TestUtils.tipeaTexto("a", robot);
        
        TestUtils.clickComponent(campoCantPlazas, robot);
        TestUtils.tipeaTexto("0", robot);
        
        robot.delay(TestUtils.getDelay());
        
        Assert.assertTrue("El campo de Patente deberia estar hablitado", campoPatente.isEnabled());
        Assert.assertTrue("El checkBox de Mascota debería estar habilitado", checkBoxMascota.isEnabled());
        Assert.assertTrue("El campo de la Cantidad de Plazas deberia estar hablitado", campoCantPlazas.isEnabled());
        Assert.assertFalse("El boton de Aceptar Vehiculo deberia estar deshablitado", altaVehiculo.isEnabled());
    }
    
}
