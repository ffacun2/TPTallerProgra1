package test.modelo.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.PasswordErroneaException;
import excepciones.UsuarioNoExisteException;
import modeloDatos.Cliente;
import modeloDatos.Usuario;

public class EmpresaTestEscenario1 {

	private Escenario1 escenario = new Escenario1(); 
    
    @Before
    public void setUp() throws Exception {
    	escenario.setUp();
    }

    @After
    public void tearDown() throws Exception {
    	escenario.tearDown();
    }
	
//----- Metodo Usuario login(String usserName, String pass)				
    
    // Clases cubiertas: 1, 3, 5, 7 
    @Test
    public void testLogin_ClaseCorrecta() {
        try {
        	Cliente usuario = escenario.empresa.getClientes().get("user1");
        	
            // Intentamos realizar el login con credenciales correctas
            Usuario usuarioLogueado = escenario.empresa.login(usuario.getNombreUsuario(), usuario.getPass());
            
            // Verificamos que el login fue exitoso y que el usuario retornado es correcto
            assertNotNull("El usuario debería haber sido logueado correctamente", usuarioLogueado);
            assertEquals("El nombre de usuario no coincide", usuario.getNombreUsuario(), usuarioLogueado.getNombreUsuario());
            assertEquals("La contraseña no coincide", usuario.getPass(), usuarioLogueado.getPass());

        } catch (UsuarioNoExisteException e) {
            fail("No se esperaba una UsuarioNoExisteException para un usuario registrado: " + e.getMessage());

        } catch (PasswordErroneaException e) {
            fail("No se esperaba una PasswordErroneaException para una contraseña correcta: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clases cubiertas: 1, 3, 5, 8 
    @Test
    public void testLogin_PasswordIncorrecta() {
        try {
        	Cliente usuario = escenario.empresa.getClientes().get("user1");

            // Intentamos realizar el login con una contraseña incorrecta
        	Usuario usuarioLogueado = escenario.empresa.login(usuario.getNombreUsuario(), "wrongpass");
        	
            fail("Se esperaba una PasswordErroneaException al intentar loguearse con una contraseña incorrecta");

        } catch (PasswordErroneaException e) {
            // Éxito: Se lanzó PasswordErroneaException como se esperaba
            assertTrue("Se lanzó correctamente PasswordErroneaException por contraseña incorrecta", true);

        } catch (UsuarioNoExisteException e) {
            fail("Se lanzó UsuarioNoExisteException en lugar de PasswordErroneaException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
}
