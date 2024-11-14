package modelo.dato;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import util.Constantes;

/**
 * Escenario donde pedido es:
 * 						Zona estandar
 * 						sin mascota
 * 						sin baul
 * 						2 pasajeros
 * 						en moto
 * 						10km
 * (caracteristicas que modifican el valor del viaje)
 */
public class ViajeTest_Esc1 {

	
		Viaje viaje;
		Pedido pedido;
		Usuario cliente;
		Chofer chofer;
		Vehiculo vehiculo;

		@Before
		public void setUp() throws Exception {
			cliente = new Cliente("usuario1","password","mi nombre");
			pedido = new Pedido((Cliente)cliente,3, false, false, 10, Constantes.ZONA_STANDARD);
			chofer = new ChoferPermanente("123","mi nombre",2000,2);
			vehiculo = new Moto("asd 123");
			viaje = new Viaje(pedido,chofer, vehiculo);
		}

		@After
		public void tearDown() throws Exception {
			cliente = new Cliente("usuario1","password","mi nombre");
			pedido = new Pedido((Cliente)cliente,3, false, false, 10, Constantes.ZONA_STANDARD);
			chofer = new ChoferPermanente("123","mi nombre",2000,2);
			vehiculo = new Moto("asd 123");
			viaje = new Viaje(pedido,chofer, vehiculo);
		}

		@Test
		public void testConstructorPedido() {
			assertEquals("Error al asignar el pedido.",pedido,viaje.getPedido());
		}
		@Test
		public void testConstructorChofer() {
			assertEquals("Error al asignar el chofer.",chofer,viaje.getChofer());
		}
		@Test
		public void testConstructorVehiculo() {
			assertEquals("Error al asignar el vehiculo.",vehiculo,viaje.getVehiculo());
		}
		
		@Test
		public void testConstructorCalificacion() {
			assertEquals(0,viaje.getCalificacion());
		}
		
		@Test
		public void testConstructorFinalizado() {
			assertFalse(viaje.isFinalizado());
		}

		
		@Test
		public void testSetValorBase() {
			Viaje.setValorBase(1000.0);
			assertEquals(1000.0,Viaje.getValorBase(),1.0);
		}
		
		@Test
		public void testFinalizarViaje() {
			viaje.finalizarViaje(5);
			assertEquals("Error al asignar la clasificacion del viaje.",5,viaje.getCalificacion());
			assertEquals("Error al preguntar si el viaje esta finalizado.",true,viaje.isFinalizado());
		}
		
		@Test
		public void testGetValor() {
			Viaje.setValorBase(1000.0);
			double base = Viaje.getValorBase();
			double valor = base;
			int cant_pasajeros = viaje.getPedido().getCantidadPasajeros();
			int cant_km = viaje.getPedido().getKm();
			
			//por zona
			valor += base*(cant_pasajeros+cant_km)*0.1;
			assertEquals(valor,viaje.getValor(),1.0);
		}
		
}
