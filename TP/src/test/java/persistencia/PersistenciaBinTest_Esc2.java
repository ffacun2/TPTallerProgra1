package persistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.Cliente;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Viaje;
import persistencia.EmpresaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;


/**
 * Escenario donde se escribe un archivo  y se corrobora que la lectura sea la misma.
 */
public class PersistenciaBinTest_Esc2 {
	
	IPersistencia persistencia = new PersistenciaBIN();
	String nombreArchivo = "datos.xml";
	File archivo = new File(nombreArchivo);
	EmpresaDTO empresa;
	Escenario escenario;

	@Before
	public void setUp() throws Exception {
		if(!archivo.exists())
			archivo.createNewFile();
		empresa = new EmpresaDTO();
		escenario = new Escenario();
	}

	@After
	public void tearDown() {
		if(archivo.exists())
			archivo.delete();
	}

	@Test
	public void testEscrituraLecturaChoferes() {
		EmpresaDTO serializado;
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				for(String dniChofer: empresa.getChoferes().keySet()) 
				{
					assertTrue("EL CHOFER ALMACENADO NO ES EL MISMO QUE EL LEIDO.", dniChofer.equals(serializado.getChoferes().get(dniChofer).getDni()));
				}
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
		}
		catch(IOException e)
		{
		}
	}
	
	@Test
	public void testEscrituraLecturaChoferesDesocupados() {
		EmpresaDTO serializado;
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				for(Chofer chofer : empresa.getChoferesDesocupados())
				{
					assertTrue("EL CHOFER DISPONIBLE ALMACENADO NO ES EL MISMO QUE EL LEIDO.", serializado.getChoferesDesocupados().contains(chofer));
				}
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
		}
		catch(IOException e)
		{
		}
	}
	
	@Test
	public void testEscrituraLecturaVehiculos() {
		EmpresaDTO serializado;
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				

				for(String patente: empresa.getVehiculos().keySet()) 
				{
					assertTrue("EL VEHICULO ALMACENADO NO ES EL MISMO QUE EL LEIDO.", patente.equals(serializado.getVehiculos().get(patente).getPatente()));
				}
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
		}
		catch(IOException e)
		{
		}
	}
	
	@Test
	public void testEscrituraLecturaVehiculosDesocupados() {
		EmpresaDTO serializado;
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				for(Vehiculo vehiculo : empresa.getVehiculosDesocupados())
				{
					assertTrue("EL VEHICULO DISPONIBLE ALMACENADO NO ES EL MISMO QUE EL LEIDO.", serializado.getVehiculosDesocupados().contains(vehiculo));
				}
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
		}
		catch(IOException e)
		{
		}
	}
	
	@Test
	public void testEscrituraLecturaClientes() {
		EmpresaDTO serializado;
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				for (String nombreUsuario : empresa.getClientes().keySet())
				{
					assertTrue("EL CLIENTE NO ES EL MISMO QUE EL ALMACENADO.",nombreUsuario.equals(serializado.getClientes().get(nombreUsuario).getNombreUsuario()));
				}
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
		}
		catch(IOException e)
		{
		}
	}

	
	@Test
	public void testEscrituraLecturaViajesTerminados() {
		EmpresaDTO serializado;
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				for(Viaje viaje : empresa.getViajesTerminados())
				{
					assertTrue("EL VIAJE TERMINADO NO ES EL MISMO QUE EL ALMACENADO.",serializado.getViajesTerminados().contains(viaje));
				}
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
		}
		catch(IOException e)
		{
		}
	}
	
	@Test
	public void testEscrituraLecturaUsuarioLogueado() {
		EmpresaDTO serializado;
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				assertTrue("EL USUARIO LOGUEADO NO ES EL MISMO ALMACENADO.",serializado.getUsuarioLogeado().getNombreUsuario().equals(empresa.getUsuarioLogeado().getNombreUsuario()));
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
			
		}
		catch(IOException e)
		{
		}
	}

	//HashMap<Cliente,Pedido> pedidos;
	@Test
	public void testEscrituraLecturaPedidos() {
		EmpresaDTO serializado;
		Pedido pedido1, pedido2;
		boolean condicion = true;
		
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				HashMap<Cliente,Pedido> map1 = empresa.getPedidos();
				HashMap<Cliente,Pedido> map2 = serializado.getPedidos();
		
				if( map1.size() == map2.size())
				{
				for(Cliente cliente1: map1.keySet())
				{
					pedido1 = map1.get(cliente1);
					
					for(Cliente cliente2: map2.keySet())
					{
						pedido2 = map2.get(cliente2);
						
						if(!cliente1.getNombreUsuario().equals(cliente2.getNombreUsuario())
							&& !pedido1.getCliente().getNombreUsuario().equals(pedido2.getCliente().getNombreUsuario()))
						{
							condicion = false;
						}
					}
				}
				}
				else
					condicion = false;
				
				assertTrue("DEBERIA SER TRUE",condicion);
				
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
			
		}
		catch(IOException e)
		{
		}
	}
	
	
	// HashMap<Cliente,Viaje> viajesTerminados
	@Test
	public void testEscrituraLecturaViajesIniciados() {
		EmpresaDTO serializado;
		boolean condicion = true;
		Viaje valor1;
		Viaje valor2;
		
		try
		{
			escenario.getEscenario(empresa);
			persistencia.abrirOutput(nombreArchivo);
			persistencia.escribir(empresa);
			persistencia.cerrarOutput();
			try {
				persistencia.abrirInput(nombreArchivo);
				serializado = (EmpresaDTO) persistencia.leer();
				persistencia.cerrarInput();
				
				HashMap<Cliente,Viaje> map1 = empresa.getViajesIniciados();
				HashMap<Cliente,Viaje> map2 = serializado.getViajesIniciados();
				
				if(map1.size() == map2.size())
				{
					for(Cliente cliente1 : map1.keySet()) 
					{
						 valor1 = map1.get(cliente1);
						 
						 for(Cliente cliente2: map2.keySet())
						 {
							 valor2 = map2.get(cliente2);
							 if(!cliente1.getNombreUsuario().equals(cliente2.getNombreUsuario()) 
									 && !valor1.getPedido().getCliente().getNombreUsuario().equals(valor2.getPedido().getCliente().getNombreUsuario()))
							 {
								 condicion = false;
							 }
						 }
					}
				}
				else
					condicion = false;
				
				assertTrue("DEBERIA SER TRUE",condicion);
				
			} 
			catch (ClassNotFoundException e)
			{
				fail("NO DEBERIA LANZAR CLASSNOTFOUND.");
			}
			
		}
		catch(IOException e)
		{
		}
	}
	
	
}
