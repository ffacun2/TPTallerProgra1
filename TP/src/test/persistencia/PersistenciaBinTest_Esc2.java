package test.persistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
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
			try
			{
				persistencia.cerrarInput();
				archivo.delete();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
	}

	@Test
	public void testEscrituraLectura() {
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
				
				for(Chofer chofer : empresa.getChoferesDesocupados())
				{
					assertTrue("EL CHOFER DISPONIBLE ALMACENADO NO ES EL MISMO QUE EL LEIDO.", serializado.getChoferesDesocupados().contains(chofer));
				}

				for(String patente: empresa.getVehiculos().keySet()) 
				{
					assertTrue("EL VEHICULO ALMACENADO NO ES EL MISMO QUE EL LEIDO.", patente.equals(serializado.getVehiculos().get(patente).getPatente()));
				}
				
				for(Vehiculo vehiculo : empresa.getVehiculosDesocupados())
				{
					assertTrue("EL VEHICULO DISPONIBLE ALMACENADO NO ES EL MISMO QUE EL LEIDO.", serializado.getVehiculosDesocupados().contains(vehiculo));
				}
				
				for (String nombreUsuario : empresa.getClientes().keySet())
				{
					assertTrue("EL CLIENTE NO ES EL MISMO QUE EL ALMACENADO.",nombreUsuario.equals(serializado.getClientes().get(nombreUsuario).getNombreUsuario()));
				}
				
				for(Viaje viaje : empresa.getViajesTerminados())
				{
					assertTrue("EL VIAJE TERMINADO NO ES EL MISMO QUE EL ALMACENADO.",serializado.getViajesTerminados().contains(viaje));
				}
				
				
				//Como comparo los pedidos (key:Cliente,value:Pedido)
				//COmo comparo los viajesIniciados (key:Cliente,value:Viaje)
				
				
				
				assertTrue("EL USUARIO LOGUEADO NO ES EL MISMO ALMACENADO.",serializado.getUsuarioLogeado().getNombreUsuario().equals(empresa.getUsuarioLogeado().getNombreUsuario()));
				
			} 
			catch (ClassNotFoundException e)
			{
			}
			
		}
		catch(IOException e)
		{
			
		}
	}

}
