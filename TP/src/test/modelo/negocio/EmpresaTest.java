package test.modelo.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.UsuarioYaExisteException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;

public class EmpresaTest {
	
	// Fixture
	private Escenarios Escenario = new Escenarios(); 
  
    
    @Before
    public void setUp() throws Exception {
        Escenario.setUp();
    }

    @After
    public void tearDown() throws Exception {
        Escenario.tearDown();
    }
    
    // Clase cubierta: 1, 3
    @Test
    public void testAgregarChofer_ClaseCorrecta() {
        try {
            // Creamos un chofer válido
            ChoferPermanente choferP = new ChoferPermanente("12345678", "Gian", 2000, 2);
            Escenario.empresa.agregarChofer(choferP);
            
            try {
                // Verificamos si el chofer fue añadido correctamente en la estructura de datos
                HashMap<String, Chofer> choferesRegistrados = Escenario.empresa.getChoferes();
                
                // Comprobamos que el chofer fue agregado
                assertTrue("El chofer no fue registrado correctamente en la empresa", 
                           choferesRegistrados.containsKey(choferP.getDni()));
                
                // Verificamos que el chofer registrado es efectivamente el mismo que se agregó
                assertEquals("El chofer registrado no coincide con el chofer ingresado", 
                             choferP, choferesRegistrados.get(choferP.getDni()));
                
            } catch (Exception ex) {
                // Si ocurre alguna excepción en la verificación, fallamos el test y mostramos el mensaje
                fail("Error inesperado al verificar el chofer registrado: " + ex.getMessage());
            }
        } catch (ChoferRepetidoException e) {
            // Fallamos el test si se lanza ChoferRepetidoException, se supone inicialmente Map vacio
            fail("No se esperaba una excepción de ChoferRepetidoException: " + e.getMessage());
        } catch (NullPointerException e) {
            // Fallamos el test si se lanza NullPointerException, se creo un Chofer valido
            fail("No se esperaba una excepción de NullPointerException: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }

    // Clase cubierta: 2
    @Test
    public void testAgregarChofer_ChoferNull() {
        try {
            Escenario.empresa.agregarChofer(null);
            fail("Se esperaba una NullPointerException al agregar un chofer null");
        } catch (NullPointerException e) {
            // Éxito: Se lanza NullPointerException como se esperaba
            assertTrue("Se lanzó correctamente NullPointerException", true);
        } catch (ChoferRepetidoException e) {
            // Fallamos el test si se lanza ChoferRepetidoException porque no es la excepción esperada
            fail("No se esperaba una ChoferRepetidoException: " + e.getMessage());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
 
    // Clase cubierta: 1, 4
    @Test
    public void testAgregarChofer_ChoferRepetido() {
        try {
            ChoferPermanente choferP = new ChoferPermanente("12345678", "Gian", 2000, 2);
            
            // Agregamos el chofer por primera vez, lo cual debería funcionar sin problemas
            Escenario.empresa.agregarChofer(choferP);
            
            // Intentamos agregar el mismo chofer nuevamente (con el mismo DNI), lo que debería lanzar la excepción
            Escenario.empresa.agregarChofer(choferP);
            
            // Si llegamos a esta línea, es un error, ya que se esperaba la excepción
            fail("Se esperaba una ChoferRepetidoException al agregar un chofer con un DNI repetido");
            
        } catch (ChoferRepetidoException e) {
            // Éxito: Se lanzó ChoferRepetidoException como se esperaba
            assertTrue("Se lanzó correctamente ChoferRepetidoException", true);
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
 
    // Clase cubierta: 1, 3, 5, 7
    @Test
    // Clase cubierta: 
    public void testAgregarCliente_ClaseCorrecta() {
        try {
            Escenario.empresa.agregarCliente("user1", "pass123", "Gian");
            
            // Verificamos si el cliente fue añadido correctamente en la estructura de datos
            HashMap<String, Cliente> clientesRegistrados = Escenario.empresa.getClientes();
            
            // Comprobamos que el cliente fue agregado usando su nombre de usuario como clave
            assertTrue("El cliente no fue registrado correctamente en la empresa", 
                       clientesRegistrados.containsKey("user1"));
            
            // Verificamos que el cliente registrado es efectivamente el mismo que se agregó
            Cliente cliente = clientesRegistrados.get("user1");
            assertEquals("El nombre del cliente registrado no coincide", "Gian", cliente.getNombreReal());
            assertEquals("La contraseña del cliente registrado no coincide", "pass123", cliente.getPass());
            
        } catch (UsuarioYaExisteException e) {
            // Fallamos el test si se lanza UsuarioYaExisteException, porque se supone que es el primer registro
            fail("No se esperaba UsuarioYaExisteException, el HashMap debe estar vacío: " + e.getMessage());
        } catch (NullPointerException e) {
            // Fallamos el test si se lanza NullPointerException, ya que los datos son válidos
            fail("No se esperaba una NullPointerException: " + e.getMessage());
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
    
	// Clase cubierta: 2.1, 3, 5
    @Test 
    public void testAgregarCliente_UsuarioNull() {
        try {
            Escenario.empresa.agregarCliente(null, "pass123", "Gian");
            fail("Se esperaba un AssertionError o NullPointerException al pasar un usuario null");

        } catch (AssertionError e) {
            // Verificamos si el AssertionError tiene el mensaje esperado, si corresponde
            assertTrue("Se lanzó correctamente AssertionError por precondición no cumplida", true);

        } catch (UsuarioYaExisteException e) {
            // Fallamos el test si se lanza UsuarioYaExisteException porque no es la excepción esperada
            fail("Se lanzó UsuarioYaExisteException en lugar de AssertionError o NullPointerException: " + e.getMessage());

        } catch (Exception e) {
            // Capturamos cualquier otra excepción inesperada y fallamos el test
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clase cubierta: 2.2, 3, 5
    @Test
    public void testAgregarCliente_UsuarioVacio() {
        try {
            Escenario.empresa.agregarCliente("", "pass123", "Gian");
            fail("Se esperaba un AssertionError al pasar un usuario vacío");

        } catch (AssertionError e) {
            // Verificamos si el AssertionError tiene el mensaje esperado
            assertEquals("EL PARAMETRO nombreUsuario ESTA VACIO", e.getMessage());

        } catch (UsuarioYaExisteException e) {
            // Fallamos el test si se lanza UsuarioYaExisteException porque no es la excepción esperada
            fail("Se lanzó UsuarioYaExisteException en lugar de AssertionError: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clase cubierta: 1, 4.1, 5
    @Test
    public void testAgregarCliente_PassNull() {
        try {
            Escenario.empresa.agregarCliente("user1", null, "Gian");
            fail("Se esperaba un AssertionError al pasar una contraseña null");

        } catch (AssertionError e) {
            // Verificamos que el AssertionError tiene el mensaje esperado
            assertEquals("EL PARAMETRO pass ES NULL", e.getMessage());

        } catch (UsuarioYaExisteException e) {
            fail("Se lanzó UsuarioYaExisteException en lugar de AssertionError: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clases cubiertas: 1, 4.2, 5
    @Test
    public void testAgregarCliente_PassVacio() {
        try {
            // Intentamos agregar un cliente con pass vacío
            Escenario.empresa.agregarCliente("user1", "", "Gian");
            fail("Se esperaba un AssertionError al pasar una contraseña vacía");

        } catch (AssertionError e) {
            // Éxito: Se lanzó AssertionError como se esperaba por una precondición no cumplida
            assertTrue("Se lanzó correctamente AssertionError por precondición no cumplida", true);

        } catch (UsuarioYaExisteException e) {
            // Fallamos el test si se lanza UsuarioYaExisteException porque no es la excepción esperada
            fail("Se lanzó UsuarioYaExisteException en lugar de AssertionError: " + e.getMessage());

        } catch (Exception e) {
            // Capturamos cualquier otra excepción inesperada y fallamos el test
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clases cubiertas: 1, 3, 6.1
    @Test
    public void testAgregarCliente_NombreRealNull() {
        try {
            Escenario.empresa.agregarCliente("user13", "pass123", null);
            fail("Se esperaba un AssertionError al pasar un nombre real null");

        } catch (AssertionError e) {
            // Éxito: Se lanzó AssertionError como se esperaba por una precondición no cumplida
            assertTrue("Se lanzó correctamente AssertionError por precondición no cumplida", true);

        } catch (UsuarioYaExisteException e) {
            // Fallamos el test si se lanza UsuarioYaExisteException porque no es la excepción esperada
            fail("Se lanzó UsuarioYaExisteException en lugar de AssertionError: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 6.2
    @Test
    public void testAgregarCliente_NombreRealVacio() {
        try {
            Escenario.empresa.agregarCliente("user14", "pass123", "");
            fail("Se esperaba un AssertionError o IllegalArgumentException al pasar un nombre real vacío");

        } catch (AssertionError e) {
            // Éxito: Se lanzó AssertionError como se esperaba por una precondición no cumplida
            assertTrue("Se lanzó correctamente AssertionError por precondición no cumplida", true);

        } catch (UsuarioYaExisteException e) {
            // Fallamos el test si se lanza UsuarioYaExisteException porque no es la excepción esperada
            fail("Se lanzó UsuarioYaExisteException en lugar de AssertionError o IllegalArgumentException: " + e.getMessage());

        } catch (Exception e) {
            // Capturamos cualquier otra excepción inesperada y fallamos el test
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clases cubiertas: 1, 3, 5, 8
    @Test
    public void testAgregarCliente_UsuarioRepetido() {
        try {
            // Primero, agregamos un usuario válido
            Escenario.empresa.agregarCliente("user1", "pass123", "Gian");

            // Intentamos agregar el mismo usuario nuevamente (con el mismo nombre de usuario)
            Escenario.empresa.agregarCliente("user1", "pass123", "Gian");
            fail("Se esperaba una UsuarioYaExisteException al agregar un usuario repetido");

        } catch (UsuarioYaExisteException e) {
            // Éxito: Se lanzó UsuarioYaExisteException como se esperaba
            assertTrue("Se lanzó correctamente UsuarioYaExisteException", true);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clases cubiertas: 1, 3, 5, 7, 9
    @Test
    public void testPedido_ClaseCorrecta() {
        try {
            // Registramos un cliente en la empresa
            String usuario = "user20";
            String pass = "pass123";
            String nombreReal = "Gian20";
            
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el HashMap de clientes registrados
            HashMap<String, Cliente> clientesRegistrados = Escenario.empresa.getClientes();
            
            // Buscamos el cliente por su nombre de usuario en el HashMap
            Cliente cliente = clientesRegistrados.get(usuario);
            assertNotNull("El cliente no fue encontrado en el sistema", cliente);  // Mensaje ajustado

            // Creamos un vehículo disponible
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos un pedido válido utilizando el cliente obtenido del HashMap
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");

            // Agregamos el pedido
            Escenario.empresa.agregarPedido(pedido);

            // Verificamos que el pedido haya sido agregado correctamente al HashMap de pedidos
            HashMap<Cliente, Pedido> pedidosRegistrados = Escenario.empresa.getPedidos();
            
            // Verificación de que el cliente esté en el HashMap
            assertTrue("El cliente no tiene un pedido registrado", pedidosRegistrados.containsKey(cliente));  // Mensaje ajustado
            
            // Verificación de que el pedido asociado al cliente sea el correcto
            assertEquals("El pedido asociado al cliente no es el correcto", pedido, pedidosRegistrados.get(cliente));  // Mensaje corregido

        } catch (SinVehiculoParaPedidoException e) {
            fail("No se esperaba una SinVehiculoParaPedidoException: " + e.getMessage());

        } catch (ClienteNoExisteException e) {
            fail("No se esperaba una ClienteNoExisteException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            fail("No se esperaba una ClienteConViajePendienteException: " + e.getMessage());

        } catch (ClienteConPedidoPendienteException e) {
            fail("No se esperaba una ClienteConPedidoPendienteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }



    
    
    
    
}
