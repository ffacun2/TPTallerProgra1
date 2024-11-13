import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	controlador.AllTestController.class,
	modelo.dato.AllTestDatos.class,
	modelo.negocio.EmpresaTestSuite.class,
	persistencia.AllTestPersistencia.class,
})
public class AllTest {

}
