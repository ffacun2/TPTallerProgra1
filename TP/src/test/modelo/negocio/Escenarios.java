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
        empresa = null;
    }
}