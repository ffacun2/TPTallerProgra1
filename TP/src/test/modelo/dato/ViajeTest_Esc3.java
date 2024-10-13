package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import util.Constantes;

/**
 * Escenario donde pedido es:
 * 						Zona sin asfaltar
 * 						con mascota
 * 						con baul
 * 						3 pasajeros
 * 						en auto
 * (caracteristicas que modifican el valor del viaje)
 */
public class ViajeTest_Esc3 {
	
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
