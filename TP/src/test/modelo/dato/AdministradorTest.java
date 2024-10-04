package test.modelo.dato;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloDatos.Administrador;

public class AdministradorTest {
	
	Administrador admin;

	@Before
	public void setUp() throws Exception {
		admin = Administrador.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		admin = Administrador.getInstance();
	}

	@Test
	public void testSingletonAdministrador() {
		assertEquals("LAS REFERECIAS DE ADMINISTRADOR NO SON LAS MISMAS",Administrador.getInstance(),admin);
	}
	
	@Test
	public void testAtributosAdministrador() {
		assertEquals("NOMBRE USUARIO ADMINISTRADOR DISTINTO DE admin","admin",admin.getNombreUsuario());
		assertEquals("CONTRASEÑA ADMINISTRADOR DISTINTO DE admin","admin",admin.getPass());
	}

}
