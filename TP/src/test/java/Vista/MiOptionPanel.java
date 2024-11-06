package Vista;

import java.awt.Component;

import javax.swing.JOptionPane;


public class MiOptionPanel implements InterfazOptionPanel{
	public MiOptionPanel() {
        super();
    }


    public void ShowMessage(Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje);
    }
}
