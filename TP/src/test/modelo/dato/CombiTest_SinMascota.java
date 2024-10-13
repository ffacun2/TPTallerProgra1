package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import util.Constantes;

/**'
 * Testeo clase combi donde se permite el viaje con mascota, verifico si el puntaje obtenido es el correcto.
 */
public class CombiTest_SinMascota {
		
		Vehiculo combi;
		
		@Before
		public void setUp() throws Exception {
			combi = new Combi("asd 123",6,false);
		}
		
		@Test
		public void testGetPuntajePedido() {
			Pedido pedido = new Pedido(new Cliente("usuario1","password","mi nombre"),3,true,true,10,Constantes.ZONA_STANDARD);
			
			assertEquals("Error en el calculo de Pedido.Deberia retornar null.",null,combi.getPuntajePedido(pedido));
		}
	

}
