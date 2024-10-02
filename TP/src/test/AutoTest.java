package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import modeloDatos.Auto;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import util.Constantes;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AutoTest.AutoConMascota.class,
	AutoTest.AutoSinMascota.class
})
public class AutoTest{
	

/**
 * Escenario donde el auto permite el acceso con mascota
 */
public static class  AutoConMascota {

	Auto auto;
	
	@Before
	public void setUp() throws Exception {
		auto = new Auto("asd 123",3,true);
	}
	
	@After
	public void tearDown() throws Exception {
		auto = new Auto("asd 123",3,true);
	}
	
	/**
	 * Verifico que el constructor asigne correctamente los atributos validos en la clase Auto
	 */
	@Test
	public void testConstructorAuto1() {
		assertEquals("Error en el constructor. Patente distantas",auto.getPatente(),"asd 123");
		assertEquals("Error en el constructor. Plazas distantas",auto.getCantidadPlazas(),3);
		assertEquals("Error en el constructor. Estado mascota distantas",auto.isMascota(),true);
	}
	
	
	/**
	 * Verifico que el metodo getPuntajePedido calcule correctamente el puntaje de acuerdo las 
	 * caracteristicas del pedido valido.
	 * El valor del puntaje depende solo de si se usa baul o no.
	 * 
	 * Pedido1 = pedido valido, con baul, retorna 80pts
	 * Pedido2 = pedido valido, sin baul, retorna 60pts
	 * Pedido3 = pedido invalido, retorna null
	 */
	@Test
	public void testGetPuntajePedido1() {
		Pedido pedido1 = new Pedido(new Cliente("usuario1","password","mi nombre"),2,true,true,10,Constantes.ZONA_PELIGROSA);
		Pedido pedido2 = new Pedido(new Cliente("usuario1","password","mi nombre"),2,false,false,10,Constantes.ZONA_PELIGROSA);
		Pedido pedido3 = new Pedido(new Cliente("usuario1","password","mi nombre"),5,true,true,10,Constantes.ZONA_PELIGROSA);
		
		assertEquals("Error en el calculo de puntaje pedido con baul.",auto.getPuntajePedido(pedido1),Integer.valueOf(80));
		assertEquals("Error en el calculo de puntajet pedido sin baul.",auto.getPuntajePedido(pedido2),Integer.valueOf(60));
		assertEquals("Error en el calculo de puntaje pedido.",auto.getPuntajePedido(pedido3),null);
	}
	
}

/**
 * Escenario donde el auto no permite el acceso con mascotas
 */
public static class AutoSinMascota {
	
	Auto auto;
	
	@Before
	public void setUp() throws Exception {
		auto = new Auto("asd 123",3,false);
	}
	
	@After
	public void tearDown() throws Exception {
		auto = new Auto("asd 123",3,false);
	}
	
	/**
	 * Verifico que los parametros pasados en el constructor constructor se
	 * asignen correctamente 
	 */
	@Test
	public void testConstructorAuto() {
		assertEquals("Error en el constructor. La patente no se asigna correctamente.",auto.getPatente(),"asd 123");
		assertEquals("Error en el constructor. La cantidad de plazas no se asgina correctamente.",auto.getCantidadPlazas(),3);
		assertEquals("Error en el constructor. El uso de mascota no se asigna correctamente.",auto.isMascota(),false);
	}
	
	/**
	 * Pedido valido en donde el auto no lo satisface, no permite mascota.
	 */
	@Test
	public void testGetPuntajePedido() {
		Pedido pedido = new Pedido(new Cliente("usuario1","password","mi nombre"),3,true,true,10,Constantes.ZONA_STANDARD);
		
		assertEquals("Error en el calculo de Pedido.Deberia retornar null.",auto.getPuntajePedido(pedido),null);
	}
}

}
