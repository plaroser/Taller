package Models;

public class Coche extends Vehiculo {
	private String ventanillas;
	
	
	public Coche(String color, String matricula, String marca, String modelo, int velocidadMaxima,
			int telefonoDue�o) {
		super(color, matricula, marca, modelo, velocidadMaxima, telefonoDue�o);
		subirVentanillas();
	}
	public void subirVentanillas(){
		this.ventanillas="subidas";
	}
	public void bajarVentanillas(){
		this.ventanillas="bajadas";
	}
}