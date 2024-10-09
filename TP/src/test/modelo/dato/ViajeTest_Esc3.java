package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import util.Constantes;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ViajeTest.ViajeTest_Escenario1.class,
	ViajeTest.ViajeTest_Escenario2.class,
	ViajeTest.ViajeTest_Escenario3.class,
})


public class ViajeTest {
	
	/**
	 * Escenario donde pedido es:
	 * 						Zona estandar
	 * 						sin mascota
	 * 						sin baul
	 * 						2 pasajeros
	 * 						en moto
	 * (caracteristicas que modifican el valor del viaje)
	 */
	public static class ViajeTest_Escenario1{
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
		public void testConstructorViaje() {
			assertEquals("Error al asignar el pedido.",pedido,viaje.getPedido());
			assertEquals("Error al asignar el chofer.",chofer,viaje.getChofer());
			assertEquals("Error al asignar el vehiculo.",vehiculo,viaje.getVehiculo());
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
	
	
	/**
	 * Escenario donde pedido es:
	 * 						Zona peligrosa
	 * 						con mascota
	 * 						sin baul
	 * 						6 pasajeros
	 * 						en combi
	 * (caracteristicas que modifican el valor del viaje)
	 */
	public static class ViajeTest_Escenario2{
		Viaje viaje;
		Pedido pedido;
		Usuario cliente;
		Chofer chofer;
		Vehiculo vehiculo;

		@Before
		public void setUp() throws Exception {
			cliente = new Cliente("usuario1","password","mi nombre");
			pedido = new Pedido((Cliente)cliente,6, true, false, 10, Constantes.ZONA_PELIGROSA);
			chofer = new ChoferPermanente("123","mi nombre",2000,2);
			vehiculo = new Combi("asd 123",10,true);
			viaje = new Viaje(pedido,chofer, vehiculo);
		}

		@After
		public void tearDown() throws Exception {
			cliente = new Cliente("usuario1","password","mi nombre");
			pedido = new Pedido((Cliente)cliente,6, true, false, 10, Constantes.ZONA_PELIGROSA);
			chofer = new ChoferPermanente("123","mi nombre",2000,2);
			vehiculo = new Combi("asd 123",10,true);
			viaje = new Viaje(pedido,chofer, vehiculo);
		}
		
		@Test
		public void testGetValor() {
			Viaje.setValorBase(1000.0);
			double base = Viaje.getValorBase();
			double valor = base;
			int cant_pasajeros = viaje.getPedido().getCantidadPasajeros();
			int cant_km = viaje.getPedido().getKm();
			
			//por zona
			valor += base*(cant_pasajeros*0.1 + cant_km*0.2);
			//por mascota
			valor += (base*(cant_pasajeros*0.1 + cant_km*0.2));
			assertEquals(valor,viaje.getValor(),1.0);
		}
		
	}
	
	
	/**
	 * Escenario donde pedido es:
	 * 						Zona sin asfaltar
	 * 						con mascota
	 * 						con baul
	 * 						3 pasajeros
	 * 						en auto
	 * (caracteristicas que modifican el valor del viaje)
	 */
	public static class ViajeTest_Escenario3{
		Viaje viaje;
		Pedido pedido;
		Usuario cliente;
		Chofer chofer;
		Vehiculo vehiculo;

		@Before
		public void setUp() throws Exception {
			cliente = new Cliente("usuario1","password","mi nombre");
			pedido = new Pedido((Cliente)cliente,3, true, true, 10, Constantes.ZONA_SIN_ASFALTAR);
			chofer = new ChoferPermanente("123","mi nombre",2000,2);
			vehiculo = new Auto("asd 123",4,true);
			viaje = new Viaje(pedido,chofer, vehiculo);
		}

		@After
		public void tearDown() throws Exception {
			cliente = new Cliente("usuario1","password","mi nombre");
			pedido = new Pedido((Cliente)cliente,3, true, true, 10, Constantes.ZONA_SIN_ASFALTAR);
			chofer = new ChoferPermanente("123","mi nombre",2000,2);
			vehiculo = new Auto("asd 123",4,true);
			viaje = new Viaje(pedido,chofer, vehiculo);
		}
		
		@Test
		public void testGetValor() {
			Viaje.setValorBase(1000.0);
			double base = Viaje.getValorBase();
			double valor = base;
			int cant_pasajeros = viaje.getPedido().getCantidadPasajeros();
			int cant_km = viaje.getPedido().getKm();
			
			//por zona
			valor += base*(cant_pasajeros*0.2 + cant_km*0.15);
			//por mascota
			valor += base*(cant_pasajeros*0.1+cant_km*0.2);
			//por baul
			valor += base*(cant_pasajeros*0.1 + cant_km*0.05);
			assertEquals(valor,viaje.getValor(),1.0);
		}
		
	}
}
