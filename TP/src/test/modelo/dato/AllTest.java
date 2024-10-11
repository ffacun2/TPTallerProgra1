package test.modelo.dato;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AdministradorTest.class,
	AutoTest_ConMascota.class,
	AutoTest_SinMascota.class,
	ChoferPermanenteTest.class,
	ChoferTemporarioTest.class,
	ClienteTest.class,
	CombiTest_ConMascota.class,
	CombiTest_SinMascota.class,
	MotoTest.class,
	PedidoTest.class,
	ViajeTest_Esc1.class,
	ViajeTest_Esc2.class,
	ViajeTest_Esc3.class
})
public class AllTest {

}
