package Vista;

import java.awt.Component;


public class FalseOptionPanel implements InterfazOptionPanel {
    private String mensaje = null;

    public FalseOptionPanel() {
        super();
    }

    @Override
    public void ShowMessage(Component parent, String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}