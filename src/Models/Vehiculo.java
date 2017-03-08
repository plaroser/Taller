package Models;

public abstract class Vehiculo implements Arrancable, Movible {
	private String color;
	private String matricula;
	private String marca;
	private String modelo;
	private int velocidadMaxima;
	private float velocidadActual;
	private int telefonoDueño;
	private boolean estado;

	public void arrancar() {
		if (this.estado) {
			System.out.println("Vehiculo " + this + " ya esta arrancado");
		} else {
			System.out.println("Vehiculo " + this + " arrancado");
			this.estado = !this.estado;
		}

	}

	public void parar() {
		if (!this.estado) {
			System.out.println("Vehiculo " + this + " ya esta parado");
		} else {
			System.out.println("Vehiculo " + this + " parado");
			this.estado = !this.estado;
		}
	}

	public void acelerar(Double velocidad) {
		if (this.velocidadActual + velocidad <= this.velocidadMaxima) {
			this.velocidadActual += velocidad;
			System.out.println("El vehiculo ha acelerado:" + velocidad + "Km/h\n" + "Ahora va a: "
					+ this.velocidadActual + "Km/h");
		} else {
			System.out.println(this + " no puede superar una velocidad de: " + this.velocidadMaxima + "Km/h.");
		}
	}

	public void frenar(Double velocidad) {
		if (this.velocidadActual - velocidad < 0) {
			this.velocidadActual -= this.velocidadActual;
		} else {
			this.velocidadActual -= velocidad;
		}
	}

	@Override
	public String toString() {
		return marca + " " + modelo + " con matricula " + matricula;
	}

}
