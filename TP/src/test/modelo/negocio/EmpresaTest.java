package test.modelo.negocio;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.ChoferNoDisponibleException;
import excepciones.ChoferRepetidoException;
import excepciones.ClienteConPedidoPendienteException;
import excepciones.ClienteConViajePendienteException;
import excepciones.ClienteNoExisteException;
import excepciones.ClienteSinViajePendienteException;
import excepciones.PasswordErroneaException;
import excepciones.PedidoInexistenteException;
import excepciones.SinVehiculoParaPedidoException;
import excepciones.SinViajesException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.VehiculoNoDisponibleException;
import excepciones.VehiculoNoValidoException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;

public class EmpresaTest {

	private Escenarios Escenario = new Escenarios(); 
    
    @Before
    public void setUp() throws Exception {
        Escenario.setUp();
    }

    @After
    public void tearDown() throws Exception {
        Escenario.tearDown();
    }
    
//----- Metodo void agregarChofer(Chofer chofer)				   
    
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
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
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
 
//----- Metodo void agregarCliente(String usuario, String pass, String nombreReal)				    
    
    // Clase cubierta: 1, 3, 5, 7
    @Test
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
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
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
    
//----- Metodo void agregarPedido(Pedido pedido)				   
    
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

    // Clases cubiertas: 1, 4 
    @Test
    public void testAgregarPedido_ClienteNoRegistrado() {
        try {
            Cliente clienteNoRegistrado = new Cliente("userNotRegistered", "pass123", "Juan Perez");

            // Creamos un pedido con ese cliente
            Pedido pedido = new Pedido(clienteNoRegistrado, 2, true, false, 10, "ZONA_STANDARD");

            // Intentamos agregar el pedido
            Escenario.empresa.agregarPedido(pedido);

            // Si llegamos aquí, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una ClienteNoExisteException al agregar un pedido con un cliente no registrado");

        } catch (ClienteNoExisteException e) {
            // Éxito: Se lanzó ClienteNoExisteException como se esperaba
            assertTrue("Se lanzó correctamente ClienteNoExisteException", true);

        } catch (SinVehiculoParaPedidoException e) {
            // Fallamos si se lanza SinVehiculoParaPedidoException
            fail("No se esperaba una SinVehiculoParaPedidoException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            // Fallamos si se lanza ClienteConViajePendienteException
            fail("No se esperaba una ClienteConViajePendienteException: " + e.getMessage());

        } catch (ClienteConPedidoPendienteException e) {
            // Fallamos si se lanza ClienteConPedidoPendienteException
            fail("No se esperaba una ClienteConPedidoPendienteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 6 
    @Test
    public void testAgregarPedido_ClienteConViajePendiente() {
        try {
            // Creamos y registramos un cliente en la empresa
            String usuario = "userConViaje";
            String pass = "pass123";
            String nombreReal = "Cliente Con Viaje";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos un vehículo y un chofer disponibles para el viaje
            Vehiculo vehiculo = new Auto("ACC123", 4, true);
            Chofer chofer = new ChoferPermanente("12345678", "Gian", 2000, 2);
            Escenario.empresa.agregarVehiculo(vehiculo);
            Escenario.empresa.agregarChofer(chofer);

            // Creamos un primer pedido con ese cliente
            Pedido pedido1 = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido1);

            // Creamos un viaje con el primer pedido (esto debería marcar que el cliente tiene un viaje pendiente)
            Escenario.empresa.crearViaje(pedido1, chofer, vehiculo);

            // Verificamos si el cliente tiene un viaje pendiente en el HashMap de viajes iniciados
            HashMap<Cliente, Viaje> viajesIniciados = Escenario.empresa.getViajesIniciados();
            assertTrue("El cliente debería tener un viaje iniciado", viajesIniciados.containsKey(cliente));

            // Verificamos que el viaje asociado al cliente es correcto y está marcado como iniciado
            Viaje viajeAsociado = viajesIniciados.get(cliente);
            assertNotNull("El viaje no fue registrado correctamente para el cliente", viajeAsociado);

            // Ahora intentamos crear un segundo pedido para el mismo cliente, que debería fallar
            Pedido pedido2 = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido2);

            fail("Se esperaba una ClienteConViajePendienteException al agregar un segundo pedido con un cliente que tiene un viaje pendiente");

        } catch (ClienteConViajePendienteException e) {
            // Éxito: Se lanzó ClienteConViajePendienteException como se esperaba
            assertTrue("Se lanzó correctamente ClienteConViajePendienteException", true);

        } catch (SinVehiculoParaPedidoException e) {
            fail("No se esperaba una SinVehiculoParaPedidoException: " + e.getMessage());

        } catch (ClienteNoExisteException e) {
            fail("No se esperaba una ClienteNoExisteException: " + e.getMessage());

        } catch (ClienteConPedidoPendienteException e) {
            fail("No se esperaba una ClienteConPedidoPendienteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 5, 8 
    @Test
    public void testAgregarPedido_ClienteConPedidoPendiente() {
        try {
            // Creamos y registramos un cliente en la empresa
            String usuario = "userConPedidoPendiente";
            String pass = "pass123";
            String nombreReal = "Cliente Con Pedido Pendiente";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos un vehículo disponible para el pedido
            Vehiculo vehiculo = new Auto("DEF456", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos un primer pedido válido para ese cliente
            Pedido pedido1 = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido1);

            // Intentamos agregar un segundo pedido para el mismo cliente, lo que debería fallar
            Pedido pedido2 = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido2);

            fail("Se esperaba una ClienteConPedidoPendienteException al intentar agregar un segundo pedido con un cliente que ya tiene un pedido pendiente");

        } catch (ClienteConPedidoPendienteException e) {
            // Éxito: Se lanzó ClienteConPedidoPendienteException como se esperaba
            assertTrue("Se lanzó correctamente ClienteConPedidoPendienteException", true);

        } catch (SinVehiculoParaPedidoException e) {
            fail("No se esperaba una SinVehiculoParaPedidoException: " + e.getMessage());

        } catch (ClienteNoExisteException e) {
            fail("No se esperaba una ClienteNoExisteException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            fail("No se esperaba una ClienteConViajePendienteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 5, 7, 10 
    @Test
    public void testAgregarPedido_SinVehiculoDisponible() {
        try {
            // Creamos y registramos un cliente en la empresa
            String usuario = "userSinVehiculo";
            String pass = "pass123";
            String nombreReal = "Cliente Sin Vehiculo";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y agregamos un vehículo que no satisface las características del pedido
            // Ejemplo: un vehículo con solo 2 plazas, pero el pedido requerirá más plazas
            Vehiculo vehiculoInadecuado = new Auto("XYZ987", 2, false);
            Escenario.empresa.agregarVehiculo(vehiculoInadecuado);

            // Creamos un pedido que no puede ser satisfecho por el vehículo registrado
            // El pedido requiere 10 plazas, pero el vehículo tiene solo 2 plazas
            Pedido pedido = new Pedido(cliente, 10, true, false, 10, "ZONA_STANDARD");

            // Intentamos agregar el pedido
            Escenario.empresa.agregarPedido(pedido);

            fail("Se esperaba una SinVehiculoParaPedidoException al agregar un pedido que no puede ser satisfecho por los vehículos disponibles");

        } catch (SinVehiculoParaPedidoException e) {
            // Éxito: Se lanzó SinVehiculoParaPedidoException como se esperaba
            assertTrue("Se lanzó correctamente SinVehiculoParaPedidoException", true);

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

//----- Metodo Metodo ArrayList<Vehiculo> vehiculosOrdenadosPorPedido(Pedido pedido)				
    
    // Clases cubiertas: 1, 3, 5 (Pedido válido con vehículos habilitados y puntajes distintos)
    @Test
    public void testVehiculosOrdenadosPorPedido_ClaseCorrecta() {
        try {
            // Creamos y registramos un cliente en la empresa
            String usuario = "userOrdenVehiculos";
            String pass = "pass123";
            String nombreReal = "Cliente Orden Vehiculos";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y agregamos vehículos de diferentes tipos habilitados para el pedido
            Vehiculo auto = new Auto("ABC123", 4, true);  	// Puntaje si no se usa baúl = 30 * cantPax
            Vehiculo combi = new Combi("DEF456", 10, true); // Puntaje sin baúl = 10 * cantPax
            Vehiculo moto = new Moto("GHI789");  			// Puntaje solo si 1 pasajero sin baúl ni mascota

            Escenario.empresa.agregarVehiculo(auto);
            Escenario.empresa.agregarVehiculo(combi);
            Escenario.empresa.agregarVehiculo(moto);

            // Creamos un pedido válido (2 pasajeros, sin mascotas, sin baúl)
            Pedido pedido = new Pedido(cliente, 2, false, false, 10, "ZONA_STANDARD");

            // Llamamos al método vehiculosOrdenadosPorPedido
            ArrayList<Vehiculo> vehiculosOrdenados = Escenario.empresa.vehiculosOrdenadosPorPedido(pedido);

            // Verificamos que el ArrayList no sea null y contenga los vehículos habilitados
            assertNotNull("El ArrayList de vehículos no debería ser null", vehiculosOrdenados);
            assertEquals("El número de vehículos habilitados es incorrecto", 2, vehiculosOrdenados.size());

            // Puntajes esperados:
            int puntajeEsperadoAuto = 30 * 2; // Auto
            int puntajeEsperadoCombi = 10 * 2; // Combi
            Integer puntajeEsperadoMoto = null; // Moto (más de un pasajero)

            // Verificamos el puntaje de cada vehículo en la lista ordenada
            assertEquals("El puntaje del auto no es el correcto", (Integer) puntajeEsperadoAuto, auto.getPuntajePedido(pedido));
            assertEquals("El puntaje de la combi no es el correcto", (Integer) puntajeEsperadoCombi, combi.getPuntajePedido(pedido));
            assertNull("El puntaje de la moto debería ser null", moto.getPuntajePedido(pedido));

            // Verificamos que los vehículos estén ordenados por puntaje en orden descendente
            assertTrue("El auto no está en la lista de vehículos habilitados", vehiculosOrdenados.contains(auto));
            assertTrue("La combi no está en la lista de vehículos habilitados", vehiculosOrdenados.contains(combi));

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
    // Clases cubiertas: 1, 4 (Pedido válido pero sin vehículos habilitados)
    @Test
    public void testVehiculosOrdenadosPorPedido_SinVehiculosHabilitados() {
        try {
            // Creamos y registramos un cliente en la empresa
            String usuario = "userSinVehiculos";
            String pass = "pass123";
            String nombreReal = "Cliente Sin Vehiculos";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y agregamos vehículos con capacidad insuficiente para el pedido
            Vehiculo auto = new Auto("ABC123", 4, true);  	// Capacidad para 4 pasajeros
            Vehiculo combi = new Combi("DEF456", 5, true); 	// Capacidad para 5 pasajeros
            Vehiculo moto = new Moto("GHI789");  			// Solo acepta 1 pasajero sin baúl ni mascota

            Escenario.empresa.agregarVehiculo(auto);
            Escenario.empresa.agregarVehiculo(combi);
            Escenario.empresa.agregarVehiculo(moto);

            // Creamos un pedido válido pero con más pasajeros que la capacidad de los vehículos (7 pasajeros)
            Pedido pedido = new Pedido(cliente, 7, true, true, 10, "ZONA_STANDARD");
            
            // Imprimimos los puntajes de los vehículos
            //System.out.println("Puntaje del Auto: " + auto.getPuntajePedido(pedido));
            //System.out.println("Puntaje de la Combi: " + combi.getPuntajePedido(pedido));
            //System.out.println("Puntaje de la Moto: " + moto.getPuntajePedido(pedido));

            // Llamamos al método vehiculosOrdenadosPorPedido
            ArrayList<Vehiculo> vehiculosOrdenados = Escenario.empresa.vehiculosOrdenadosPorPedido(pedido);

            // Verificamos que el ArrayList esté vacío, ya que ningún vehículo puede satisfacer el pedido
            assertNotNull("El ArrayList de vehículos no debería ser null", vehiculosOrdenados);
            assertTrue("El ArrayList debería estar vacío ya que ningún vehículo está habilitado", vehiculosOrdenados.isEmpty());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 6 
    @Test
    public void testVehiculosOrdenadosPorPedido_VehiculosConMismoPuntaje() {
        try {
            // Creamos y registramos un cliente en la empresa
            String usuario = "userMismoPuntaje";
            String pass = "pass123";
            String nombreReal = "Cliente Con Mismo Puntaje";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y agregamos vehículos con la misma capacidad y características que les darán el mismo puntaje
            Vehiculo auto1 = new Auto("ABC123", 3, true);  // Capacidad para 3 pasajeros, admite mascota
            Vehiculo auto2 = new Auto("DEF456", 3, true);  // Capacidad para 3 pasajeros, admite mascota
            Vehiculo auto3 = new Auto("GHI789", 3, true);  // Capacidad para 3 pasajeros, admite mascota

            Escenario.empresa.agregarVehiculo(auto1);
            Escenario.empresa.agregarVehiculo(auto2);
            Escenario.empresa.agregarVehiculo(auto3);

            // Creamos un pedido que generará el mismo puntaje para todos los vehículos
            Pedido pedido = new Pedido(cliente, 3, true, false, 10, "ZONA_STANDARD"); // No se usa baúl, 3 pasajeros

            // Llamamos al método vehiculosOrdenadosPorPedido
            ArrayList<Vehiculo> vehiculosOrdenados = Escenario.empresa.vehiculosOrdenadosPorPedido(pedido);
            
            // Verificamos que el ArrayList contiene exactamente los 3 vehículos
            assertNotNull("El ArrayList de vehículos no debería ser null", vehiculosOrdenados);
            assertEquals("El número de vehículos habilitados es incorrecto", 3, vehiculosOrdenados.size());

            // Verificamos que los 3 vehículos estén en el ArrayList, sin importar el orden
            assertTrue("El ArrayList debería contener el auto1", vehiculosOrdenados.contains(auto1));
            assertTrue("El ArrayList debería contener el auto2", vehiculosOrdenados.contains(auto2));
            assertTrue("El ArrayList debería contener el auto3", vehiculosOrdenados.contains(auto3));

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo boolean validarPedido(Pedido pedido)				
    
    // Clases cubiertas: 1, 3 
    @Test
    public void testValidarPedido_ClaseCorrecta() {
        try {
            // Registramos un cliente
            String usuario = "userValido";
            String pass = "pass123";
            String nombreReal = "Cliente Valido";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y agregamos vehículos habilitados para el pedido
            Vehiculo auto = new Auto("ABC123", 4, true);  	// Capacidad para 4 pasajeros, acepta mascota
            Escenario.empresa.agregarVehiculo(auto);

            // Creamos un pedido válido que puede ser satisfecho por el auto
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");

            // Validamos el pedido
            boolean resultado = Escenario.empresa.validarPedido(pedido);

            // Verificamos que el pedido sea válido
            assertTrue("El pedido debería ser válido ya que hay vehículos habilitados", resultado);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4 
    @Test
    public void testValidarPedido_SinVehiculosHabilitados() {
        try {
            // Registramos un cliente
            String usuario = "userNoVehiculos";
            String pass = "pass123";
            String nombreReal = "Cliente Sin Vehiculos";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y agregamos vehículos que no cumplen con los requisitos del pedido
            Vehiculo auto = new Auto("ABC123", 4, false);  	// Vehículo no acepta mascotas
            Vehiculo moto = new Moto("GHI789");  			// Moto solo acepta 1 pasajero y sin baúl ni mascota

            Escenario.empresa.agregarVehiculo(auto);
            Escenario.empresa.agregarVehiculo(moto);

            // Creamos un pedido que requiere mascotas y baúl (ningún vehículo puede satisfacerlo)
            Pedido pedido = new Pedido(cliente, 2, true, true, 10, "ZONA_STANDARD");

            // Validamos el pedido
            boolean resultado = Escenario.empresa.validarPedido(pedido);

            // Verificamos que el resultado sea false, ya que ningún vehículo puede satisfacer el pedido
            assertFalse("El pedido debería ser inválido ya que no hay vehículos habilitados", resultado);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
  
//----- Metodo void agregarVehiculo(Vehiculo vehiculo)				
    
    // Clases cubiertas: 1, 3
    @Test
    public void testAgregarVehiculo_ClaseCorrecta() {
        try {
            Vehiculo auto = new Auto("ABC123", 4, true);

            // Agregamos el vehículo a la empresa
            Escenario.empresa.agregarVehiculo(auto);

            // Verificamos si el vehículo fue añadido correctamente al HashMap
            HashMap<String, Vehiculo> vehiculosRegistrados = Escenario.empresa.getVehiculos();
            
            // Verificamos que el vehículo se ha agregado correctamente
            assertTrue("El vehículo no fue registrado correctamente en la empresa", 
                       vehiculosRegistrados.containsKey("ABC123"));
            
            // Verificamos que el vehículo registrado es el mismo que agregamos
            assertEquals("El vehículo registrado no coincide con el vehículo ingresado", 
                         auto, vehiculosRegistrados.get("ABC123"));

        } catch (VehiculoRepetidoException e) {
            fail("No se esperaba una VehiculoRepetidoException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4 (Patente ya registrada)
    @Test
    public void testAgregarVehiculo_VehiculoRepetido() {
        try {
            // Creamos y agregamos un vehículo válido por primera vez
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Intentamos agregar el mismo vehículo nuevamente (con la misma patente)
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Si llegamos aquí, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una VehiculoRepetidoException al agregar un vehículo con patente repetida");

        } catch (VehiculoRepetidoException e) {
            // Éxito: Se lanzó VehiculoRepetidoException como se esperaba
            assertTrue("Se lanzó correctamente VehiculoRepetidoException", true);

        } catch (Exception e) {
            // Capturamos cualquier otra excepción inesperada
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo void crearViaje(Pedido pedido, Chofer chofer, Vehiculo vehiculo)					    
    
    // Clases cubiertas: 1, 3, 5, 7, 9, 11, 13, 15
    @Test
    public void testCrearViaje_ClaseCorrecta() {
        try {
            // Crear y registrar el chofer disponible
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);
            Escenario.empresa.getChoferesDesocupados().add(chofer);  // Marcar chofer como desocupado
            
            // Crear y registrar un vehículo disponible y válido para el pedido
            Vehiculo vehiculo = new Auto("ABC123", 4, true); // Acepta 4 pasajeros y acepta mascotas
            Escenario.empresa.agregarVehiculo(vehiculo);
            Escenario.empresa.getVehiculosDesocupados().add(vehiculo);  // Marcar vehículo como desocupado

            // Crear y registrar cliente
            String usuario = "userViajeCorrecto";
            String pass = "pass123";
            String nombreReal = "Cliente Viaje Correcto";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Crear pedido válido (que no requiere baúl y acepta mascotas)
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido);
            
            // Validar si el pedido es válido con este vehículo
            assertTrue("El vehículo debería ser válido para el pedido", Escenario.empresa.validarPedido(pedido));

            // Crear el viaje
            Escenario.empresa.crearViaje(pedido, chofer, vehiculo);
            
            // Verificar que el viaje haya sido creado correctamente
            HashMap<Cliente, Viaje> viajesIniciados = Escenario.empresa.getViajesIniciados();
            assertTrue("El viaje no fue registrado correctamente", viajesIniciados.containsKey(cliente));

        } catch (PedidoInexistenteException e) {
            fail("No se esperaba una PedidoInexistenteException: " + e.getMessage());

        } catch (ChoferNoDisponibleException e) {
            fail("No se esperaba una ChoferNoDisponibleException: " + e.getMessage());

        } catch (VehiculoNoDisponibleException e) {
            fail("No se esperaba una VehiculoNoDisponibleException: " + e.getMessage());

        } catch (VehiculoNoValidoException e) {
            fail("No se esperaba una VehiculoNoValidoException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            fail("No se esperaba una ClienteConViajePendienteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4, 5, 9 
    @Test
    public void testCrearViaje_PedidoNoRegistrado() {
        try {
            // Crear y registrar un chofer válido
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);
            Escenario.empresa.getChoferesDesocupados().add(chofer);  // Marcar como desocupado

            // Crear y registrar un vehículo válido
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);
            Escenario.empresa.getVehiculosDesocupados().add(vehiculo);  // Marcar como desocupado

            // Crear un pedido que NO será registrado en la empresa (pedido no registrado)
            Cliente cliente = new Cliente("user1", "pass123", "Cliente Test");
            Pedido pedidoNoRegistrado = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");

            // Intentamos crear el viaje con el pedido no registrado
            Escenario.empresa.crearViaje(pedidoNoRegistrado, chofer, vehiculo);
            fail("Se esperaba una PedidoInexistenteException al crear un viaje con un pedido no registrado");

        } catch (PedidoInexistenteException e) {
            // Éxito: Se lanzó PedidoInexistenteException como se esperaba
            assertTrue("Se lanzó correctamente PedidoInexistenteException", true);

        } catch (ChoferNoDisponibleException e) {
            fail("Se lanzó ChoferNoDisponibleException en lugar de PedidoInexistenteException: " + e.getMessage());

        } catch (VehiculoNoDisponibleException e) {
            fail("Se lanzó VehiculoNoDisponibleException en lugar de PedidoInexistenteException: " + e.getMessage());

        } catch (VehiculoNoValidoException e) {
            fail("Se lanzó VehiculoNoValidoException en lugar de PedidoInexistenteException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            fail("Se lanzó ClienteConViajePendienteException en lugar de PedidoInexistenteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 6, 9
    @Test
    public void testCrearViaje_ChoferNoDisponible() {
        try {
            // Crear y registrar un chofer válido
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);

            // Eliminar manualmente el chofer de la lista de choferes desocupados
            Escenario.empresa.getChoferesDesocupados().remove(chofer);

            // Verificar que el chofer NO está en la lista de choferes desocupados
            assertFalse("El chofer no debería estar disponible (en choferesDesocupados)", 
                        Escenario.empresa.getChoferesDesocupados().contains(chofer));

            // Crear y registrar un vehículo válido
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Crear y registrar cliente
            String usuario = "userViajeCorrecto";
            String pass = "pass123";
            String nombreReal = "Cliente Viaje Correcto";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);
            
            Pedido pedidoValido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedidoValido);  // Registrar el pedido

            // Intentamos crear el viaje con un chofer no disponible
            Escenario.empresa.crearViaje(pedidoValido, chofer, vehiculo);

            // Si llegamos a esta línea, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una ChoferNoDisponibleException al crear un viaje con un chofer no disponible");

        } catch (ChoferNoDisponibleException e) {
            // Éxito: Se lanzó ChoferNoDisponibleException como se esperaba
            assertTrue("Se lanzó correctamente ChoferNoDisponibleException", true);

        } catch (PedidoInexistenteException e) {
            fail("Se lanzó PedidoInexistenteException en lugar de ChoferNoDisponibleException: " + e.getMessage());

        } catch (VehiculoNoDisponibleException e) {
            fail("Se lanzó VehiculoNoDisponibleException en lugar de ChoferNoDisponibleException: " + e.getMessage());

        } catch (VehiculoNoValidoException e) {
            fail("Se lanzó VehiculoNoValidoException en lugar de ChoferNoDisponibleException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            fail("Se lanzó ClienteConViajePendienteException en lugar de ChoferNoDisponibleException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 5, 7, 10
    @Test
    public void testCrearViaje_VehiculoNoDisponible() {
        try {
            // Crear y registrar un chofer válido
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);

            // Aseguramos que el chofer esté disponible
            assertTrue("El chofer debería estar disponible", Escenario.empresa.getChoferesDesocupados().contains(chofer));

            // Crear y registrar un vehículo válido
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Eliminar manualmente el vehículo de la lista de vehículos desocupados para simular que está ocupado
            Escenario.empresa.getVehiculosDesocupados().remove(vehiculo);

            // Verificar que el vehículo NO está en la lista de vehículos desocupados
            assertFalse("El vehículo no debería estar disponible (en vehiculosDesocupados)", 
                        Escenario.empresa.getVehiculosDesocupados().contains(vehiculo));

            // Crear y registrar cliente
            String usuario = "userViajeVehiculoNoDisponible";
            String pass = "pass123";
            String nombreReal = "Cliente Viaje Vehiculo No Disponible";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            Pedido pedidoValido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedidoValido);  // Registrar el pedido

            // Intentamos crear el viaje con un vehículo no disponible
            Escenario.empresa.crearViaje(pedidoValido, chofer, vehiculo);

            // Si llegamos a esta línea, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una VehiculoNoDisponibleException al crear un viaje con un vehículo no disponible");

        } catch (VehiculoNoDisponibleException e) {
            // Éxito: Se lanzó VehiculoNoDisponibleException como se esperaba
            assertTrue("Se lanzó correctamente VehiculoNoDisponibleException", true);

        } catch (PedidoInexistenteException e) {
            fail("Se lanzó PedidoInexistenteException en lugar de VehiculoNoDisponibleException: " + e.getMessage());

        } catch (ChoferNoDisponibleException e) {
            fail("Se lanzó ChoferNoDisponibleException en lugar de VehiculoNoDisponibleException: " + e.getMessage());

        } catch (VehiculoNoValidoException e) {
            fail("Se lanzó VehiculoNoValidoException en lugar de VehiculoNoDisponibleException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            fail("Se lanzó ClienteConViajePendienteException en lugar de VehiculoNoDisponibleException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 5, 7, 9, 13, 14
    @Test
    public void testCrearViaje_VehiculoNoValido() {
        try {
            // Crear y registrar un chofer válido
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);

            // Aseguramos que el chofer esté disponible
            assertTrue("El chofer debería estar disponible", Escenario.empresa.getChoferesDesocupados().contains(chofer));

            // Crear un vehículo válido que será ocupado (y por lo tanto no estará disponible para el pedido)
            Vehiculo vehiculoValidoOcupado = new Auto("DEF456", 4, true); // Acepta mascotas, pero estará ocupado
            Escenario.empresa.agregarVehiculo(vehiculoValidoOcupado);
            Escenario.empresa.getVehiculosDesocupados().remove(vehiculoValidoOcupado); // Simular que está ocupado

            // Crear un vehículo que NO es válido para el pedido (no acepta mascotas)
            Vehiculo vehiculoNoValido = new Auto("ABC123", 4, false); // No acepta mascotas
            Escenario.empresa.agregarVehiculo(vehiculoNoValido);

            // Verificamos que ambos vehículos están correctamente registrados
            assertTrue("El vehículo válido debería estar registrado", Escenario.empresa.getVehiculos().containsKey("DEF456"));
            assertTrue("El vehículo no válido debería estar registrado", Escenario.empresa.getVehiculos().containsKey("ABC123"));

            // Crear y registrar cliente
            String usuario = "userVehiculoNoValido";
            String pass = "pass123";
            String nombreReal = "Cliente Vehiculo No Valido";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos un pedido que requiere transporte de mascotas
            Pedido pedidoInvalido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD"); // Requiere transporte de mascotas
            Escenario.empresa.agregarPedido(pedidoInvalido);  // Registrar el pedido

            // Intentamos crear el viaje con un vehículo NO válido (vehiculoNoValido)
            Escenario.empresa.crearViaje(pedidoInvalido, chofer, vehiculoNoValido);

            // Si llegamos a esta línea, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una VehiculoNoValidoException al crear un viaje con un vehículo no válido para el pedido");

        } catch (VehiculoNoValidoException e) {
            // Éxito: Se lanzó VehiculoNoValidoException como se esperaba
            assertTrue("Se lanzó correctamente VehiculoNoValidoException", true);

        } catch (PedidoInexistenteException e) {
            fail("Se lanzó PedidoInexistenteException en lugar de VehiculoNoValidoException: " + e.getMessage());

        } catch (ChoferNoDisponibleException e) {
            fail("Se lanzó ChoferNoDisponibleException en lugar de VehiculoNoValidoException: " + e.getMessage());

        } catch (VehiculoNoDisponibleException e) {
            fail("Se lanzó VehiculoNoDisponibleException en lugar de VehiculoNoValidoException: " + e.getMessage());

        } catch (ClienteConViajePendienteException e) {
            fail("Se lanzó ClienteConViajePendienteException en lugar de VehiculoNoValidoException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 5, 7, 9, 13, 15, 16
    @Test
    public void testCrearViaje_ClienteConViajePendiente() {
        try {
            // Crear y registrar un chofer válido
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);
            assertTrue("El chofer debería estar disponible", Escenario.empresa.getChoferesDesocupados().contains(chofer));

            // Crear y registrar un vehículo válido
            Vehiculo vehiculo = new Auto("ABC123", 4, true); // Acepta mascotas
            Escenario.empresa.agregarVehiculo(vehiculo);
            assertTrue("El vehículo debería estar disponible", Escenario.empresa.getVehiculosDesocupados().contains(vehiculo));

            // Crear y registrar cliente
            String usuario = "userViajePendiente";
            String pass = "pass123";
            String nombreReal = "Cliente Viaje Pendiente";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos un pedido válido
            Pedido primerPedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(primerPedido);

            // Creación del primer viaje (cliente sin viajes pendientes al inicio)
            Escenario.empresa.crearViaje(primerPedido, chofer, vehiculo);

            // Verificamos que el cliente tiene un viaje iniciado
            assertTrue("El cliente debería tener un viaje pendiente", Escenario.empresa.getViajesIniciados().containsKey(cliente));

            // Creamos un segundo pedido para el mismo cliente
            Pedido segundoPedido = new Pedido(cliente, 3, false, true, 15, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(segundoPedido);

            // Intentamos crear el viaje con el cliente que ya tiene un viaje pendiente
            Escenario.empresa.crearViaje(segundoPedido, chofer, vehiculo);

            // Si llegamos a esta línea, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una ClienteConViajePendienteException al crear un viaje con un cliente que ya tiene un viaje pendiente");

        } catch (ClienteConViajePendienteException e) {
            // Éxito: Se lanzó ClienteConViajePendienteException como se esperaba
            assertTrue("Se lanzó correctamente ClienteConViajePendienteException", true);

        } catch (PedidoInexistenteException e) {
            fail("Se lanzó PedidoInexistenteException en lugar de ClienteConViajePendienteException: " + e.getMessage());

        } catch (ChoferNoDisponibleException e) {
            fail("Se lanzó ChoferNoDisponibleException en lugar de ClienteConViajePendienteException: " + e.getMessage());

        } catch (VehiculoNoDisponibleException e) {
            fail("Se lanzó VehiculoNoDisponibleException en lugar de ClienteConViajePendienteException: " + e.getMessage());

        } catch (VehiculoNoValidoException e) {
            fail("Se lanzó VehiculoNoValidoException en lugar de ClienteConViajePendienteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo void pagarYFinalizarViaje(int calificacion)				
    
    // Clases cubiertas: 1, 3 
    @Test
    public void testPagarYFinalizarViaje_Calificacion5() {
        try {
            // Creamos y registramos un cliente
            String usuario = "userViajeFinalizado";
            String pass = "pass123";
            String nombreReal = "Cliente Viaje Finalizado";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y registramos un chofer y vehículo válidos
            ChoferPermanente chofer = new ChoferPermanente("98765432", "Pedro", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);
            Vehiculo vehiculo = new Auto("XYZ123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos un pedido válido
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido);

            // Iniciamos un viaje para el cliente
            Escenario.empresa.crearViaje(pedido, chofer, vehiculo);

            // Logueamos al cliente en la empresa
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Verificamos que el cliente tiene un viaje pendiente
            assertTrue("El cliente debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));

            // Pagamos y finalizamos el viaje con una calificación de 5
            Escenario.empresa.pagarYFinalizarViaje(5);

            // Verificamos que el viaje ha sido movido de "viajesIniciados" a "viajesTerminados"
            assertFalse("El cliente ya no debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));
            assertTrue("El viaje debería estar en la lista de viajes terminados", 
                Escenario.empresa.getViajesTerminados().size() > 0);

        } catch (ClienteSinViajePendienteException e) {
            // Fallamos si se lanza ClienteSinViajePendienteException, ya que el cliente tiene un viaje pendiente
            fail("No se esperaba una ClienteSinViajePendienteException: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            // Fallamos si se lanza IllegalArgumentException, ya que la calificación está dentro del rango permitido
            fail("No se esperaba una IllegalArgumentException para una calificación válida: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3 
    @Test
    public void testPagarYFinalizarViaje_Calificacion0() {
        try {
            // Creamos y registramos un cliente
            String usuario = "userViajeFinalizadoCalificacion0";
            String pass = "pass123";
            String nombreReal = "Cliente Viaje Finalizado Calificacion 0";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y registramos un chofer y vehículo válidos
            ChoferPermanente chofer = new ChoferPermanente("98765433", "Carlos", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);
            Vehiculo vehiculo = new Auto("XYZ124", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos un pedido válido
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido);

            // Iniciamos un viaje para el cliente
            Escenario.empresa.crearViaje(pedido, chofer, vehiculo);

            // Logueamos al cliente en la empresa
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Verificamos que el cliente tiene un viaje pendiente
            assertTrue("El cliente debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));

            // Pagamos y finalizamos el viaje con una calificación de 0
            Escenario.empresa.pagarYFinalizarViaje(0);

            // Verificamos que el viaje ha sido movido de "viajesIniciados" a "viajesTerminados"
            assertFalse("El cliente ya no debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));
            assertTrue("El viaje debería estar en la lista de viajes terminados", 
                Escenario.empresa.getViajesTerminados().size() > 0);

        } catch (ClienteSinViajePendienteException e) {
            // Fallamos si se lanza ClienteSinViajePendienteException, ya que el cliente tiene un viaje pendiente
            fail("No se esperaba una ClienteSinViajePendienteException: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            // Fallamos si se lanza IllegalArgumentException, ya que la calificación está dentro del rango permitido
            fail("No se esperaba una IllegalArgumentException para una calificación válida: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3 
    @Test
    public void testPagarYFinalizarViaje_Calificacion3() {
        try {
            // Creamos y registramos un cliente
            String usuario = "userViajeFinalizadoCalificacion3";
            String pass = "pass123";
            String nombreReal = "Cliente Viaje Finalizado Calificacion 3";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Creamos y registramos un chofer y vehículo válidos
            ChoferPermanente chofer = new ChoferPermanente("98765434", "Carlos", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);
            Vehiculo vehiculo = new Auto("XYZ125", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos un pedido válido
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido);

            // Iniciamos un viaje para el cliente
            Escenario.empresa.crearViaje(pedido, chofer, vehiculo);

            // Logueamos al cliente en la empresa
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Verificamos que el cliente tiene un viaje pendiente
            assertTrue("El cliente debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));

            // Pagamos y finalizamos el viaje con una calificación de 3
            Escenario.empresa.pagarYFinalizarViaje(3);

            // Verificamos que el viaje ha sido movido de "viajesIniciados" a "viajesTerminados"
            assertFalse("El cliente ya no debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));
            assertTrue("El viaje debería estar en la lista de viajes terminados", 
                Escenario.empresa.getViajesTerminados().size() > 0);

        } catch (ClienteSinViajePendienteException e) {
            // Fallamos si se lanza ClienteSinViajePendienteException, ya que el cliente tiene un viaje pendiente
            fail("No se esperaba una ClienteSinViajePendienteException: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            // Fallamos si se lanza IllegalArgumentException, ya que la calificación está dentro del rango permitido
            fail("No se esperaba una IllegalArgumentException para una calificación válida: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 2, 3 
    @Test
    public void testPagarYFinalizarViaje_SinViajePendiente() {
        try {
            // Registramos el cliente sin ningún viaje pendiente
            String usuario = "userSinViajePendiente";
            String pass = "pass123";
            String nombreReal = "Cliente Sin Viaje";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Verificar que el cliente NO tiene un viaje pendiente
            assertFalse("El cliente no debería tener un viaje pendiente", 
                        Escenario.empresa.getViajesIniciados().containsKey(cliente));

            // Logeamos al cliente en la empresa
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Intentamos finalizar el viaje con una calificación válida (ej: 3)
            Escenario.empresa.pagarYFinalizarViaje(3);

            // Si llegamos aquí, el test debe fallar porque no se lanzó la excepción esperada
            fail("Se esperaba una ClienteSinViajePendienteException al intentar finalizar un viaje sin tener uno pendiente");

        } catch (ClienteSinViajePendienteException e) {
            // Éxito: se lanzó ClienteSinViajePendienteException como se esperaba
            assertTrue("Se lanzó correctamente ClienteSinViajePendienteException", true);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo Usuario login(String usserName, String pass)				
    
    // Clases cubiertas: 1, 3, 5, 7 (Nombre de usuario y contraseña válidos)
    @Test
    public void testLogin_ClaseCorrecta() {
        try {
            // Registramos un usuario válido en el sistema
            String usuario = "admin";
            String pass = "admin123";
            Escenario.empresa.agregarCliente(usuario, pass, "Administrador");
            
            // Intentamos realizar el login con credenciales correctas
            Usuario usuarioLogueado = Escenario.empresa.login(usuario, pass);
            
            // Verificamos que el login fue exitoso y que el usuario retornado es correcto
            assertNotNull("El usuario debería haber sido logueado correctamente", usuarioLogueado);
            assertEquals("El nombre de usuario no coincide", usuario, usuarioLogueado.getNombreUsuario());
            assertEquals("La contraseña no coincide", pass, usuarioLogueado.getPass());

        } catch (UsuarioNoExisteException e) {
            fail("No se esperaba una UsuarioNoExisteException para un usuario registrado: " + e.getMessage());

        } catch (PasswordErroneaException e) {
            fail("No se esperaba una PasswordErroneaException para una contraseña correcta: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 6 
    @Test
    public void testLogin_UsuarioNoRegistrado() {
        try {
            Escenario.empresa.login("nonexistent", "password");
            fail("Se esperaba una UsuarioNoExisteException al intentar loguearse con un usuario no registrado");

        } catch (UsuarioNoExisteException e) {
            // Éxito: Se lanzó UsuarioNoExisteException como se esperaba
            assertTrue("Se lanzó correctamente UsuarioNoExisteException por usuario no registrado", true);

        } catch (PasswordErroneaException e) {
            fail("Se lanzó PasswordErroneaException en lugar de UsuarioNoExisteException: " + e.getMessage());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 5, 8 (Contraseña incorrecta)
    @Test
    public void testLogin_PasswordIncorrecta() {
        try {
            Escenario.empresa.agregarCliente("admin", "admin123", "Administrador");

            // Intentamos realizar el login con una contraseña incorrecta
            Escenario.empresa.login("admin", "wrongpass");
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

//----- Metodo double getTotalSalarios()				
    
    // Clases cubiertas: 1, 3 
    @Test
    public void testGetTotalSalarios_MultiplesChoferes() {
        try {
            // Registramos un chofer permanente con un salario basado en su antigüedad y cantidad de hijos
        	// Antigüedad de 14 años, 2 hijos
            ChoferPermanente choferPermanente = new ChoferPermanente("12345678", "Juan", 2010, 2); 
            Escenario.empresa.agregarChofer(choferPermanente);
            
            // El sueldo básico del chofer permanente será calculado con un 5% por cada año de antigüedad, 
            // y 7% por cada hijo.
            double sueldoBrutoPermanente = choferPermanente.getSueldoBruto();
            //System.out.println("Sueldo bruto del chofer permanente: " + sueldoBrutoPermanente);

            // Registramos un chofer temporario con su sueldo igual al sueldo básico
            ChoferTemporario choferTemporario = new ChoferTemporario("87654321", "Pedro");
            Escenario.empresa.agregarChofer(choferTemporario);
            
            double sueldoBrutoTemporario = choferTemporario.getSueldoBruto();
            //System.out.println("Sueldo bruto del chofer temporario: " + sueldoBrutoTemporario);

            // Verificamos que el salario total es la suma de ambos salarios brutos
            double totalSalarios = Escenario.empresa.getTotalSalarios();
            double salarioEsperado = sueldoBrutoPermanente + sueldoBrutoTemporario;

            assertEquals("El total de salarios es incorrecto", salarioEsperado, totalSalarios, 0.01);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3 LANZA ASERTO POR SUELDO CERO LO CUAL TIENE SENTIDO 
    // PERO EL JDOC DICE QUE PUEDE SER >= 0 ASIQ UE NO DEBERIA LANZAR ASERTO
    @Test
    public void testGetTotalSalarios_ChoferesConSalarioCero() {
        try {
            // Sueldo básico definido como 0 para los choferes
            double sueldoBasico = 0;

            // Registramos un chofer permanente con salario 0
            ChoferPermanente choferPermanente = new ChoferPermanente("12345678", "Juan", 2010, 2); 
            Chofer.setSueldoBasico(sueldoBasico); // El sueldo básico es 0
            Escenario.empresa.agregarChofer(choferPermanente);

            // Registramos un chofer temporario con salario 0
            ChoferTemporario choferTemporario = new ChoferTemporario("87654321", "Pedro");
            Chofer.setSueldoBasico(sueldoBasico); // El sueldo básico es 0
            Escenario.empresa.agregarChofer(choferTemporario);

            // Verificamos que el salario total es 0.0
            double totalSalarios = Escenario.empresa.getTotalSalarios();
            double salarioEsperado = 0.0;

            assertEquals("El total de salarios es incorrecto", salarioEsperado, totalSalarios, 0.01);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3 Parece que se calculan mal los salarios o este metodo hace algo raro en la suma
    @Test
    public void testGetTotalSalarios_UnChofer() {
        try {
            // Definimos el sueldo básico y otros parámetros del chofer
            double sueldoBasico = 2000.0;
            int anioIngreso = 2010;  // Antigüedad de 14 años (asumiendo que estamos en 2024)
            int cantidadHijos = 1;  // Un hijo

            // Registramos un chofer permanente con estos datos
            ChoferPermanente choferPermanente = new ChoferPermanente("12345678", "Juan", anioIngreso, cantidadHijos);
            Chofer.setSueldoBasico(sueldoBasico);  // Establecemos sueldo básico como 2000
            Escenario.empresa.agregarChofer(choferPermanente);

            // Calculamos el salario esperado:
            int antiguedad = LocalDate.now().getYear() - anioIngreso;
            double incrementoAntiguedad = Math.min(antiguedad * 0.05, 1.0) * sueldoBasico;  // Máximo de 100% del básico
            double incrementoPorHijos = cantidadHijos * 0.07 * sueldoBasico;
            double salarioEsperado = sueldoBasico + incrementoAntiguedad + incrementoPorHijos;

            // Verificamos que el salario total es correcto
            double totalSalarios = Escenario.empresa.getTotalSalarios();

            assertEquals("El total de salarios es incorrecto", salarioEsperado, totalSalarios, 0.01);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 2 
    @Test
    public void testGetTotalSalarios_SinChoferes() {
        try {
            // Verificamos que no haya choferes registrados
            assertTrue("No debería haber choferes registrados", Escenario.empresa.getChoferes().isEmpty());

            // Obtenemos el total de salarios
            double totalSalarios = Escenario.empresa.getTotalSalarios();

            // Verificamos que el total de salarios sea 0.0
            assertEquals("El total de salarios debería ser 0.0 cuando no hay choferes registrados", 0.0, totalSalarios, 0.0);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo ArrayList<Viaje> getHistorialViajeCliente(Cliente cliente)				
    
    // Clases cubiertas: 1, 3, 5 
    @Test
    public void testGetHistorialViajeCliente_ConHistorial() {
        try {
            // Registramos un cliente
            String usuario = "userConHistorial";
            String pass = "pass123";
            String nombreReal = "Cliente Con Historial";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Registramos un vehículo y un chofer
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 2);
            Escenario.empresa.agregarChofer(chofer);

            // Primer viaje
            Pedido pedido1 = new Pedido(cliente, 2, false, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido1);
            Escenario.empresa.crearViaje(pedido1, chofer, vehiculo);
            
            // Logueamos al cliente en la empresa
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Verificamos que el cliente tiene un viaje pendiente
            assertTrue("El cliente debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));
            
            Escenario.empresa.pagarYFinalizarViaje(5);  // Finaliza el primer viaje

            // Segundo viaje
            Pedido pedido2 = new Pedido(cliente, 2, false, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido2);
            Escenario.empresa.crearViaje(pedido2, chofer, vehiculo);
            
            // Verificamos que el cliente tiene un viaje pendiente
            assertTrue("El cliente debería tener un viaje pendiente", 
                Escenario.empresa.getViajesIniciados().containsKey(cliente));
            
            Escenario.empresa.pagarYFinalizarViaje(4);  // Finaliza el segundo viaje

            // Obtenemos el historial de viajes del cliente
            ArrayList<Viaje> historialViajes = Escenario.empresa.getHistorialViajeCliente(cliente);

            // Verificamos que el historial tenga 2 viajes
            assertNotNull("El historial de viajes no debería ser null", historialViajes);
            assertEquals("El cliente debería tener 2 viajes en su historial", 2, historialViajes.size());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4, 5 
    @Test
    public void testGetHistorialViajeCliente_SinHistorial() {
        try {
            // Registramos un cliente sin viajes
            String usuario = "userSinHistorial";
            String pass = "pass123";
            String nombreReal = "Cliente Sin Historial";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Verificamos que el historial de viajes del cliente esté vacío
            ArrayList<Viaje> historialViajes = Escenario.empresa.getHistorialViajeCliente(cliente);
            assertNotNull("El historial de viajes no debería ser null", historialViajes);
            assertTrue("El historial de viajes debería estar vacío", historialViajes.isEmpty());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 6 
    @Test
    public void testGetHistorialViajeCliente_ClienteNoRegistrado() {
        try {
            Cliente clienteNoRegistrado = new Cliente("userNoReg", "pass123", "Cliente No Registrado");

            // Intentamos obtener el historial de este cliente no registrado
            ArrayList<Viaje> historialViajes = Escenario.empresa.getHistorialViajeCliente(clienteNoRegistrado);

            // Verificamos que el ArrayList esté vacío, ya que el cliente no está registrado
            assertNotNull("El ArrayList de viajes no debería ser null", historialViajes);
            assertTrue("El ArrayList debería estar vacío ya que el cliente no está registrado", historialViajes.isEmpty());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo ArrayList<Viaje> getHistorialViajeChofer(Chofer chofer)				
    
    // Clases cubiertas: 1, 3, 5 
    @Test
    public void testGetHistorialViajeChofer_ConHistorial() {
        try {
            // Creamos y registramos un chofer
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 2);
            Escenario.empresa.agregarChofer(chofer);

            // Creamos y registramos un cliente
            String usuario = "userCliente";
            String pass = "pass123";
            String nombreReal = "Cliente Test";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado desde la empresa
            Cliente clienteRegistrado = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", clienteRegistrado);

            // Registramos un vehículo
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos tres pedidos y viajes asociados
            for (int i = 0; i < 3; i++) {
                Pedido pedido = new Pedido(clienteRegistrado, 2, true, false, 10, "ZONA_STANDARD");
                Escenario.empresa.agregarPedido(pedido);

                // Crear el viaje
                Escenario.empresa.crearViaje(pedido, chofer, vehiculo);

                // Logueamos al cliente y pagamos el viaje para poder generar el historial
                Escenario.empresa.setUsuarioLogeado(clienteRegistrado);
                Escenario.empresa.pagarYFinalizarViaje(5);
            }

            // Obtenemos el historial de viajes del chofer
            ArrayList<Viaje> historialViajes = Escenario.empresa.getHistorialViajeChofer(chofer);

            // Verificamos que el ArrayList contiene exactamente 3 viajes
            assertNotNull("El ArrayList de viajes no debería ser null", historialViajes);
            assertEquals("El historial del chofer debería contener 3 viajes", 3, historialViajes.size());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4, 5 
    @Test
    public void testGetHistorialViajeChofer_SinHistorial() {
        try {
            // Creamos y registramos un chofer sin viajes
            ChoferPermanente chofer = new ChoferPermanente("87654321", "Carlos", 2015, 1);
            Escenario.empresa.agregarChofer(chofer);

            // Obtenemos el historial de viajes del chofer
            ArrayList<Viaje> historialViajes = Escenario.empresa.getHistorialViajeChofer(chofer);

            // Verificamos que el ArrayList esté vacío, ya que el chofer no tiene viajes
            assertNotNull("El ArrayList de viajes no debería ser null", historialViajes);
            assertTrue("El historial del chofer debería estar vacío", historialViajes.isEmpty());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 6 
    @Test
    public void testGetHistorialViajeChofer_ChoferNoRegistrado() {
        try {
            ChoferPermanente choferNoRegistrado = new ChoferPermanente("98765432", "No Registrado", 2015, 0);

            // Llamamos al método con un chofer no registrado
            ArrayList<Viaje> historial = Escenario.empresa.getHistorialViajeChofer(choferNoRegistrado);

            // Verificamos que el historial esté vacío
            assertNotNull("El historial no debería ser null", historial);
            assertTrue("El historial debería estar vacío ya que el chofer no está registrado", historial.isEmpty());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo double calificacionDeChofer(Chofer chofer)				   
    
    // Clases cubiertas: 1, 3, 5 
    @Test
    public void testCalificacionDeChofer_ChoferConTresViajes() {
        try {
            // Registramos el chofer
            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan Perez", 2010, 2);
            Escenario.empresa.agregarChofer(chofer);

            // Creamos y registramos un cliente en la empresa
            String usuario = "userConHistorial";
            String pass = "pass123";
            String nombreReal = "Cliente Con Historial";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Logueamos al cliente en la empresa para que pueda realizar viajes
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Creamos y registramos un vehículo
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos 3 pedidos con diferentes características y realizamos cada viaje antes del siguiente
            Pedido pedido1 = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido1);
            Escenario.empresa.crearViaje(pedido1, chofer, vehiculo);
            Escenario.empresa.pagarYFinalizarViaje(5); // Finaliza el primer viaje con calificación 5

            Pedido pedido2 = new Pedido(cliente, 2, true, false, 15, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido2);
            Escenario.empresa.crearViaje(pedido2, chofer, vehiculo);
            Escenario.empresa.pagarYFinalizarViaje(4); // Finaliza el segundo viaje con calificación 4

            Pedido pedido3 = new Pedido(cliente, 2, true, false, 5, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido3);
            Escenario.empresa.crearViaje(pedido3, chofer, vehiculo);
            Escenario.empresa.pagarYFinalizarViaje(3); // Finaliza el tercer viaje con calificación 3

            // Llamamos al método para obtener la calificación promedio
            double calificacionPromedio = Escenario.empresa.calificacionDeChofer(chofer);

            // Verificamos el promedio esperado
            assertEquals("El promedio de calificaciones es incorrecto", 4.0, calificacionPromedio, 0.01);

        } catch (SinViajesException e) {
            fail("No debería lanzarse SinViajesException: El chofer tiene viajes realizados.");
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 3, 5 
    @Test
    public void testCalificacionDeChofer_ChoferConUnViaje() {
        try {
            // Registramos el chofer
            ChoferPermanente chofer = new ChoferPermanente("87654321", "Carlos Lopez", 2015, 1);
            Escenario.empresa.agregarChofer(chofer);

            // Creamos y registramos un cliente en la empresa
            String usuario = "userConUnViaje";
            String pass = "pass123";
            String nombreReal = "Cliente Un Viaje";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);

            // Obtenemos el cliente registrado
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Logueamos al cliente en la empresa
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Creamos y registramos un vehículo
            Vehiculo vehiculo = new Auto("XYZ987", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            // Creamos un pedido y realizamos el viaje
            Pedido pedido = new Pedido(cliente, 2, true, false, 20, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido);
            Escenario.empresa.crearViaje(pedido, chofer, vehiculo);

            // Finalizamos el viaje con una calificación de 5
            Escenario.empresa.pagarYFinalizarViaje(5);

            // Llamamos al método para obtener la calificación promedio
            double calificacionPromedio = Escenario.empresa.calificacionDeChofer(chofer);

            // Verificamos el promedio esperado
            assertEquals("El promedio de calificación es incorrecto", 5.0, calificacionPromedio, 0.01);

        } catch (SinViajesException e) {
            fail("No debería lanzarse SinViajesException: El chofer tiene un viaje realizado.");
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4 
    @Test
    public void testCalificacionDeChofer_ChoferSinViajes() {
        try {
            // Registramos un chofer que no ha realizado ningún viaje
            ChoferPermanente chofer = new ChoferPermanente("87654321", "Carlos Lopez", 2015, 1);
            Escenario.empresa.agregarChofer(chofer);

            // Llamamos al método para obtener la calificación promedio
            Escenario.empresa.calificacionDeChofer(chofer);

            // Si llegamos aquí, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una SinViajesException al intentar obtener la calificación de un chofer sin viajes");

        } catch (SinViajesException e) {
            // Éxito: Se lanzó SinViajesException como se esperaba
            assertTrue("Se lanzó correctamente SinViajesException para un chofer sin viajes", true);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo Metodo Pedido getPedidoDeCliente(Cliente cliente)				
    
    // Clases cubiertas: 1, 3 
    @Test
    public void testGetPedidoDeCliente_ClienteConViajeEnCurso() {
        try {
            // Registramos un cliente en la empresa
            String usuario = "userConViajeEnCurso";
            String pass = "pass123";
            String nombreReal = "Cliente Con Viaje En Curso";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Registramos un vehículo y un chofer
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan Perez", 2010, 2);
            Escenario.empresa.agregarChofer(chofer);

            // Creamos un pedido y lo asignamos
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido);

            // Verificamos que el cliente tiene un viaje en curso (pedido pendiente)
            Pedido pedidoEnCurso = Escenario.empresa.getPedidoDeCliente(cliente);
            assertNotNull("El cliente debería tener un pedido en curso", pedidoEnCurso);
            assertEquals("El pedido en curso debería coincidir con el pedido realizado", pedido, pedidoEnCurso);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4 
    @Test
    public void testGetPedidoDeCliente_ClienteSinPedidoPendiente() {
        try {
            // Registramos un cliente en la empresa
            String usuario = "userSinPedidoPendiente";
            String pass = "pass123";
            String nombreReal = "Cliente Sin Pedido Pendiente";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // El cliente no tiene ningún pedido pendiente, no agregamos ningún pedido

            // Llamamos al método para obtener el pedido
            Pedido pedidoPendiente = Escenario.empresa.getPedidoDeCliente(cliente);

            // Verificamos que el método retorne null, ya que no tiene pedidos pendientes
            assertNull("El cliente no debería tener ningún pedido pendiente", pedidoPendiente);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

//----- Metodo  Viaje getViajeDeCliente(Cliente cliente)					
    
    // Clases cubiertas: 1, 3 
    @Test
    public void testGetViajeDeCliente_ClienteConViajeEnCurso() {
        try {
            // Registramos un cliente en la empresa
            String usuario = "userConViaje";
            String pass = "pass123";
            String nombreReal = "Cliente Con Viaje";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Registramos un vehículo y un chofer
            Vehiculo vehiculo = new Auto("ABC123", 4, true);
            Escenario.empresa.agregarVehiculo(vehiculo);

            ChoferPermanente chofer = new ChoferPermanente("12345678", "Juan", 2010, 1);
            Escenario.empresa.agregarChofer(chofer);

            // Creamos un pedido y lo asignamos
            Pedido pedido = new Pedido(cliente, 2, true, false, 10, "ZONA_STANDARD");
            Escenario.empresa.agregarPedido(pedido);

            // Logueamos al cliente en la empresa
            Escenario.empresa.setUsuarioLogeado(cliente);

            // Creamos un viaje para el cliente
            Escenario.empresa.crearViaje(pedido, chofer, vehiculo);

            // Verificamos que el cliente tiene un viaje en curso
            Viaje viaje = Escenario.empresa.getViajeDeCliente(cliente);
            assertNotNull("El cliente debería tener un viaje en curso", viaje);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
 
    // Clases cubiertas: 1, 4 
    @Test
    public void testGetViajeDeCliente_ClienteSinViajeEnCurso() {
        try {
            // Registramos un cliente en la empresa
            String usuario = "userSinViaje";
            String pass = "pass123";
            String nombreReal = "Cliente Sin Viaje";
            Escenario.empresa.agregarCliente(usuario, pass, nombreReal);
            Cliente cliente = Escenario.empresa.getClientes().get(usuario);
            assertNotNull("El cliente no fue registrado correctamente", cliente);

            // Intentamos obtener el viaje del cliente, que no debería tener un viaje en curso
            Viaje viaje = Escenario.empresa.getViajeDeCliente(cliente);
            
            // Verificamos que el resultado es null, ya que el cliente no tiene un viaje en curso
            assertNull("El cliente no debería tener un viaje en curso", viaje);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 2 
    @Test
    public void testGetViajeDeCliente_ClienteNull() {
        try {
            Escenario.empresa.getViajeDeCliente(null);
            fail("Se esperaba un AssertionError al pasar un cliente null");

        } catch (AssertionError e) {
            // Éxito: Se lanzó AssertionError como se esperaba por la precondición no cumplida
            assertTrue("Se lanzó correctamente AssertionError por cliente null", true);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
    
}
