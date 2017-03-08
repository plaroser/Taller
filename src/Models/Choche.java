package Models;

public class Choche extends Vehiculo {
	private String ventanillas;
	
	public Choche(){
		subirVentanillas();
	}
	public void subirVentanillas(){
		this.ventanillas="subidas";
	}
	public void bajarVentanillas(){
		this.ventanillas="bajadas";
	}
}
