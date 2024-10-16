package test.modelo.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excepciones.SinViajesException;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Viaje;

public class EmpresaTestEscenario5 {
	
	private Escenario5 escenario = new Escenario5(); 
    
    @Before
    public void setUp() throws Exception {
    	escenario.setUp();
    }

    @After
    public void tearDown() throws Exception {
    	escenario.tearDown();
    }
    
//----- Metodo ArrayList<Viaje> getHistorialViajeCliente(Cliente cliente)				
    
    // Clases cubiertas: 1, 3, 5 
    @Test
    public void testGetHistorialViajeCliente_ConHistorial() {
        try {
        	// Este cliente ya ha realizado 3 viajes
            Cliente cliente = escenario.empresa.getClientes().get("user1");

            // Verificamos que el historial tenga 3 viajes
            ArrayList<Viaje> historialViajes = escenario.empresa.getHistorialViajeCliente(cliente);
            assertNotNull("El historial de viajes no debería ser null", historialViajes);
            assertEquals("El cliente debería tener 2 viajes en su historial", 3, historialViajes.size());

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    // Clases cubiertas: 1, 4, 5 
    @Test
    public void testGetHistorialViajeCliente_SinHistorial() {
        try {
            // Cliente sin viajes
            Cliente cliente = escenario.empresa.getClientes().get("user5");

            // Verificamos que el historial de viajes del cliente esté vacío
            ArrayList<Viaje> historialViajes = escenario.empresa.getHistorialViajeCliente(cliente);
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
            ArrayList<Viaje> historialViajes = escenario.empresa.getHistorialViajeCliente(clienteNoRegistrado);

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
        	// Este chofer ya ha realizado 3 viajes
            Chofer chofer = escenario.empresa.getChoferes().get("11111111");
            
            // Obtenemos el historial de viajes del chofer
            ArrayList<Viaje> historialViajes = escenario.empresa.getHistorialViajeChofer(chofer);

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
        	// Este chofer NO ha realizado ningun viaje
            Chofer chofer = escenario.empresa.getChoferes().get("77777777");
            
            // Obtenemos el historial de viajes del chofer
            ArrayList<Viaje> historialViajes = escenario.empresa.getHistorialViajeChofer(chofer);

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
            ArrayList<Viaje> historial = escenario.empresa.getHistorialViajeChofer(choferNoRegistrado);

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
        	// Este chofer ya ha realizado 3 viajes
            Chofer chofer = escenario.empresa.getChoferes().get("11111111");

            // Llamamos al método para obtener la calificación promedio
            double calificacionPromedio = escenario.empresa.calificacionDeChofer(chofer);

            // Verificamos el promedio esperado (5 + 4 + 3 = 12   Prom = 4)
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
        	// Este chofer ya ha realizado 1 solo viaje
            Chofer chofer = escenario.empresa.getChoferes().get("66666666");

            // Llamamos al método para obtener la calificación promedio
            double calificacionPromedio = escenario.empresa.calificacionDeChofer(chofer);

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
        	// Este chofer ya NO ha realizado ningun viaje
            Chofer chofer = escenario.empresa.getChoferes().get("77777777");

            // Llamamos al método para obtener la calificación promedio
            escenario.empresa.calificacionDeChofer(chofer);

            // Si llegamos aquí, el test debe fallar, ya que se esperaba una excepción
            fail("Se esperaba una SinViajesException al intentar obtener la calificación de un chofer sin viajes");

        } catch (SinViajesException e) {
            // Éxito: Se lanzó SinViajesException como se esperaba
            assertTrue("Se lanzó correctamente SinViajesException para un chofer sin viajes", true);

        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    
    
    

}
