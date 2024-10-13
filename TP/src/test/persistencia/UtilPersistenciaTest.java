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
import modeloNegocio.Empresa;
import persistencia.EmpresaDTO;
import persistencia.UtilPersistencia;
import util.Constantes;

public class UtilPersistenciaTest {
	
	EmpresaDTO empresaDTO;
	
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
	public void setUp()throws Exception{
		empresaDTO = new EmpresaDTO();
		
		choferes.put(chofer1.getDni(),chofer1);
		clientes.put(cliente1.getNombreUsuario(), cliente1);
		vehiculos.put(auto1.getPatente(),auto1);
		usuarioLogueado = cliente1;
		pedidos.put(cliente1, pedido1);
		viajesIniciados.put(cliente1,viaje1);
		
		Empresa.getInstance().setChoferes(choferes);
		Empresa.getInstance().setChoferesDesocupados(choferDisponible);
		Empresa.getInstance().setClientes(clientes);
		Empresa.getInstance().setPedidos(pedidos);
		Empresa.getInstance().setUsuarioLogeado(usuarioLogueado);
		Empresa.getInstance().setVehiculos(vehiculos);
		Empresa.getInstance().setVehiculosDesocupados(vehiculosDesocupados);
		Empresa.getInstance().setViajesIniciados(viajesIniciados);
		Empresa.getInstance().setViajesTerminados(viajesTerminados);
	}
	
	@After
	public void tearDown() throws Exception{
		choferes.clear();
		choferDisponible.clear();
		clientes.clear();
		pedidos.clear();
		vehiculos.clear();
		vehiculosDesocupados.clear();
		viajesIniciados.clear();
		viajesTerminados.clear();
		
		
		Empresa.getInstance().getChoferes().clear();
		Empresa.getInstance().getChoferesDesocupados();
		Empresa.getInstance().getClientes().clear();;
		Empresa.getInstance().getPedidos().clear();;
		Empresa.getInstance().setUsuarioLogeado(null);;
		Empresa.getInstance().getVehiculos().clear();;
		Empresa.getInstance().getVehiculosDesocupados().clear();;
		Empresa.getInstance().getViajesIniciados().clear();;
		Empresa.getInstance().getViajesTerminados().clear();;
	}

	
	@Test
	public void testEmpresaDTOfromEmpresa() {
		
		empresaDTO = UtilPersistencia.EmpresaDtoFromEmpresa();
		
		assertEquals("LOS CHOFERES NO COINCIDEN",empresaDTO.getChoferes(),Empresa.getInstance().getChoferes());
		assertEquals("LOS CHOFERES DESOCUPADOS NO COINCIDEN",empresaDTO.getChoferesDesocupados(),Empresa.getInstance().getChoferesDesocupados());
		assertEquals("LOS CLIENTES NO COINCIDEN",empresaDTO.getClientes(),Empresa.getInstance().getClientes());
		assertEquals("LOS PEDIDOS NO COINCIDEN",empresaDTO.getPedidos(),Empresa.getInstance().getPedidos());
		assertEquals("EL USUARIO LOGUEADO NO COINCIDE",empresaDTO.getUsuarioLogeado(),Empresa.getInstance().getUsuarioLogeado());
		assertEquals("LOS VEHICULOS NO COINCIDEN",empresaDTO.getVehiculos(),Empresa.getInstance().getVehiculos());
		assertEquals("LOS VEHICULOS DESOCUPADOS NO COINCIDEN",empresaDTO.getVehiculosDesocupados(),Empresa.getInstance().getVehiculosDesocupados());
		assertEquals("LOS VIAJES INICIADOS NO COINCIDEN",empresaDTO.getViajesIniciados(),Empresa.getInstance().getViajesIniciados());
		assertEquals("LOS VIAJES TERMINADOS NO COINCIDEN",empresaDTO.getViajesTerminados(),Empresa.getInstance().getViajesTerminados());
	}

	@Test
	public void testEmpresafromEmpresaDTO() {
		
		empresaDTO.setChoferes(choferes);
		empresaDTO.setChoferesDesocupados(choferDisponible);
		empresaDTO.setClientes(clientes);
		empresaDTO.setPedidos(pedidos);
		empresaDTO.setUsuarioLogeado(usuarioLogueado);
		empresaDTO.setVehiculos(vehiculos);
		empresaDTO.setVehiculosDesocupados(vehiculosDesocupados);
		empresaDTO.setViajesIniciados(viajesIniciados);
		empresaDTO.setViajesTerminados(viajesTerminados);
		
		UtilPersistencia.empresaFromEmpresaDTO(empresaDTO);
		
		assertEquals("LOS CHOFERES NO COINCIDEN",Empresa.getInstance().getChoferes(),empresaDTO.getChoferes());
		assertEquals("LOS CHOFERES DISPONIBLES NO COINCIDEN",Empresa.getInstance().getChoferesDesocupados(),empresaDTO.getChoferesDesocupados());
		assertEquals("LOS CLIENTES NO COINCIDEN",Empresa.getInstance().getClientes(),empresaDTO.getClientes());
		assertEquals("LOS PEDIDOS NO COINCIDEN",Empresa.getInstance().getPedidos(),empresaDTO.getPedidos());
		assertEquals("EL USUARIO LOGUEADO NO COINCIDE",Empresa.getInstance().getUsuarioLogeado(),empresaDTO.getUsuarioLogeado());
		assertEquals("LOS VEHICULOS NO COINCIDEN",Empresa.getInstance().getVehiculos(),empresaDTO.getVehiculos());
		assertEquals("LOS VEHICULOS DISPONIBLES NO COINCIDEN",Empresa.getInstance().getVehiculosDesocupados(),empresaDTO.getVehiculosDesocupados());
		assertEquals("LOS VIAJES INICIADOS NO COINCIDEN",Empresa.getInstance().getViajesIniciados(),empresaDTO.getViajesIniciados());
		assertEquals("LOS VIAJES TERMINADOS NO COINCIDEN",Empresa.getInstance().getViajesTerminados(),empresaDTO.getViajesTerminados());
	}

}
