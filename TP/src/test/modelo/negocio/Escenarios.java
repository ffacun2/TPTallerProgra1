package test.modelo.negocio;

import modeloNegocio.Empresa;

public class Escenarios {
    
    Empresa empresa;
    
    public Escenarios() 
    {
    }

    public void setUp() 
    {
    	empresa = Empresa.getInstance();
    }

    public void tearDown() 
    {
    	empresa.getChoferes().clear();     
        empresa.getChoferesDesocupados().clear();   
        empresa.getClientes().clear();           
        empresa.getVehiculos().clear();        
        empresa.getVehiculosDesocupados().clear();  
        empresa.getPedidos().clear();           
        empresa.getViajesIniciados().clear();       
        empresa.getViajesTerminados().clear(); 
    }
}