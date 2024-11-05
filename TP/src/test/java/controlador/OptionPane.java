package controlador;

import vista.IOptionPane;

public class OptionPane implements IOptionPane {

	private String msj = null;
	
	public OptionPane(){
		super();
	}

	@Override
	public void ShowMessage(String arg0) {
		this.msj = arg0;
	}
	
	public String getMsj() {
		return msj;
	}
	
}
