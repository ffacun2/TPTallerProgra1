package Vista.testGUI;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Vista.TestUtils;
import controlador.Controlador;
import junit.framework.Assert;
import util.Constantes;
import vista.Ventana;

public class TestEnabledDisabled {
	Robot robot;
    Controlador controlador;

    public TestEnabledDisabled()
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
    }

    @After
    public void tearDown() throws Exception
    {
        Ventana ventana = (Ventana) controlador.getVista();
    	ventana.setVisible(false);
    }

// TEST APARTADO LOGIN
    
    @Test
    public void testLogin_SoloNombre()
    {
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        JButton botonLogin = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("random", robot);

        Assert.assertTrue("El boton de IR a registro deberia estar hablitado", botonIrReg.isEnabled());
        Assert.assertFalse("El boton de login deberia estar deshablitado", botonLogin.isEnabled());
    }
    
    @Test
    public void testLogin_SoloPassword()
    {
        robot.delay(TestUtils.getDelay());

        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        JButton botonLogin = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("random", robot);

        Assert.assertTrue("El boton de IR a registro deberia estar hablitado", botonIrReg.isEnabled());
        Assert.assertFalse("El boton de login deberia estar deshablitado", botonLogin.isEnabled());
    }
    
    @Test
    public void testLogin_Completo()
    {
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.NOMBRE_USUARIO);
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.PASSWORD);
        JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
        JButton botonLogin = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.LOGIN);

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("random", robot);
        
        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("random", robot);

        Assert.assertTrue("El boton de IR a registro deberia estar hablitado", botonIrReg.isEnabled());
        Assert.assertTrue("El boton de login deberia estar hablitado", botonLogin.isEnabled());
    }

//TEST APARTADO REGISTRO

	@Test
	public void testRegistro_Completo()
	{
		robot.delay(TestUtils.getDelay());
		JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		TestUtils.clickComponent(botonIrReg, robot);
	
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_REAL_NAME);
		JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField confirmarPassword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		
		JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		JButton botonCancelar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
	
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("randomNombreReal", robot);
	
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("randomContrasenia", robot);
		
		TestUtils.clickComponent(confirmarPassword, robot);
		TestUtils.tipeaTexto("randomContrasenia", robot);
		
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("randomNombreReal", robot);
	
		Assert.assertTrue("El boton de registro deberia estar deshablitado", botonRegistrar.isEnabled());
		Assert.assertTrue("El boton de Cancelar registro deberia estar hablitado", botonCancelar.isEnabled());
	}
	
	@Test
	public void testRegistro_SoloNombreUsuario()
	{
		robot.delay(TestUtils.getDelay());
		JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		TestUtils.clickComponent(botonIrReg, robot);
	
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_USSER_NAME);
		JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		JButton botonCancelar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
	
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("randomNombreReal", robot);
	
		Assert.assertFalse("El boton de registro deberia estar deshablitado", botonRegistrar.isEnabled());
		Assert.assertTrue("El boton de Cancelar registro deberia estar hablitado", botonCancelar.isEnabled());
	}
	
	@Test
	public void testRegistro_SoloPassword()
	{
		robot.delay(TestUtils.getDelay());
		JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		TestUtils.clickComponent(botonIrReg, robot);
	
		JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_PASSWORD);
		JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		JButton botonCancelar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
	
		TestUtils.clickComponent(password, robot);
		TestUtils.tipeaTexto("randomContrasenia", robot);
	
		Assert.assertFalse("El boton de registro deberia estar deshablitado", botonRegistrar.isEnabled());
		Assert.assertTrue("El boton de Cancelar registro deberia estar hablitado", botonCancelar.isEnabled());
	}
	
	@Test
	public void testRegistro_SoloConfirmarPassword()
	{
		robot.delay(TestUtils.getDelay());
		JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		TestUtils.clickComponent(botonIrReg, robot);
	
		JTextField confirmarPassword = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		JButton botonCancelar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
	
		TestUtils.clickComponent(confirmarPassword, robot);
		TestUtils.tipeaTexto("randomContrasenia", robot);
	
		Assert.assertFalse("El boton de registro deberia estar deshablitado", botonRegistrar.isEnabled());
		Assert.assertTrue("El boton de Cancelar registro deberia estar hablitado", botonCancelar.isEnabled());
	}
	
	@Test
	public void testRegistro_SoloNombreReal()
	{
		robot.delay(TestUtils.getDelay());
		JButton botonIrReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REGISTRAR);
		TestUtils.clickComponent(botonIrReg, robot);
	
		JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_REAL_NAME);
		JButton botonRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		JButton botonCancelar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
	
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("randomNombreReal", robot);
	
		Assert.assertFalse("El boton de registro deberia estar deshablitado", botonRegistrar.isEnabled());
		Assert.assertTrue("El boton de Cancelar registro deberia estar hablitado", botonCancelar.isEnabled());
	}

}
