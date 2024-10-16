package test.persistencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Usuario;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import persistencia.EmpresaDTO;
import util.Constantes;

public class EmpresaDTOTest {
	
	EmpresaDTO empresa;
	
	HashMap<String,Chofer> choferes = new HashMap<String, Chofer>();
	ArrayList<Chofer> choferDisponible = new ArrayList<Chofer>();
	HashMap<String,Cliente> clientes = new HashMap<String, Cliente>();
	HashMap<Cliente,Pedido> pedidos = new HashMap<Cliente, Pedido>();
	Usuario usuarioLogueado;
	HashMap<String,Vehiculo> vehiculos = new HashMap<String, Vehiculo>();
	ArrayList<Vehiculo> vehiculosDesocupados = new ArrayList<Vehiculo>();
	HashMap<Cliente,Viaje> viajesIniciados = new HashMap<Cliente, Viaje>();
	ArrayList<Viaje> viajesTerminados = new ArrayList<Viaje>();
	
	Chofer chofer1 = new ChoferTemporario("1234","primer chofer");
	Cliente cliente1 = new Cliente("primer usuario","1234","nombre real");
	Vehiculo auto1 = new Auto("asd 123",4,true);
	Pedido pedido1 = new Pedido(cliente1,3,false,true,10,Constantes.ZONA_SIN_ASFALTAR);
	Viaje viaje1 = new Viaje(pedido1, chofer1, auto1);

	@Before
	public void setUp() throws Exception {
		
		empresa = new EmpresaDTO();
		
		choferes.put(chofer1.getDni(),chofer1);
		choferDisponible.add(chofer1);
		clientes.put(cliente1.getNombreUsuario(), cliente1);
		vehiculos.put(auto1.getPatente(),auto1);
		vehiculosDesocupados.add(auto1);
		usuarioLogueado = cliente1;
		pedidos.put(cliente1, pedido1);
		viajesIniciados.put(cliente1,viaje1);
		viajesTerminados.add(viaje1);

		empresa.setChoferes(choferes);
		empresa.setChoferesDesocupados(choferDisponible);
		empresa.setClientes(clientes);
		empresa.setVehiculos(vehiculos);
		empresa.setVehiculosDesocupados(vehiculosDesocupados);
		empresa.setUsuarioLogeado(cliente1);
		empresa.setPedidos(pedidos);
		empresa.setViajesIniciados(viajesIniciados);
		empresa.setViajesTerminados(viajesTerminados);
		
	}

	@After
	public void tearDown() throws Exception {
		empresa.getChoferes().clear();
		empresa.getChoferesDesocupados().clear();
		empresa.getClientes().clear();
		empresa.getVehiculos().clear();
		empresa.getVehiculosDesocupados().clear();
		usuarioLogueado = null;
		pedidos.clear();
		viajesIniciados.clear();
		viajesTerminados.clear();
	}

	@Test
	public void testGetChofer() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE CHOFERES.",empresa.getChoferes(),choferes);
	}
	@Test
	public void testGetChoferDisponible() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE CHOFERES DISPONIBLES", empresa.getChoferesDesocupados(), choferDisponible);
	}
	@Test
	public void testGetVehiculos() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE VEHICULOS", empresa.getVehiculos(), vehiculos);
	}
	@Test
	public void testGetVehiculosDesocupados() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE VEHICULOS DESOCUPADOS", empresa.getVehiculosDesocupados(), vehiculosDesocupados);
	}
	@Test
	public void testGetCliente() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE CLIENTES", empresa.getClientes(), clientes);
	}
	@Test
	public void testGetUsuarioLogueado() {
		assertEquals("NO SE ASIGNA EL MISMO USUARIO LOGUEADO", empresa.getUsuarioLogeado(), usuarioLogueado);
	}
	@Test
	public void testGetPedidos() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE PEDIDOS", empresa.getPedidos(), pedidos);
	}
	@Test
	public void testGetViajesTerminados() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE VIAJES INICIADOS", empresa.getViajesIniciados(), viajesIniciados);
	}
	@Test
	public void testGetViajesIniciados() {
		assertEquals("NO SE ASIGNA LA MISMA LIST DE VIAJES TERMINADOS", empresa.getViajesTerminados(), viajesTerminados);
	}

}
