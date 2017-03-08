package Models;

public abstract class Vehiculo implements Arrancable, Movible {
	protected String color;
	protected String matricula;
	protected String marca;
	protected String modelo;
	protected int velocidadMaxima;
	protected float velocidadActual;
	protected int telefonoDueño;
	protected boolean estado;

	public Vehiculo(String color, String matricula, String marca, String modelo, int velocidadMaxima,
			int telefonoDueño) {
		this.color = color;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.velocidadMaxima = velocidadMaxima;
		this.velocidadActual = 0;
		this.telefonoDueño = telefonoDueño;
		this.estado = false;
	}

	@Override
	public void arrancar() {
		if (this.estado) {
			System.out.println("Vehiculo " + this + " ya esta arrancado");
		} else {
			System.out.println("Vehiculo " + this + " arrancado");
			this.estado = !this.estado;
		}

	}

	@Override
	public void parar() {
		if (!this.estado) {
			System.out.println("Vehiculo " + this + " ya esta parado");
		} else {
			System.out.println("Vehiculo " + this + " parado");
			this.estado = !this.estado;
		}
	}

	@Override
	public void acelerar(Double velocidad) {
		if (this.velocidadActual + velocidad <= this.velocidadMaxima) {
			this.velocidadActual += velocidad;
			System.out.println("El vehiculo ha acelerado:" + velocidad + "Km/h\n" + "Ahora va a: "
					+ this.velocidadActual + "Km/h");
		} else {
			System.out.println(this + " no puede superar una velocidad de: " + this.velocidadMaxima + "Km/h.");
		}
	}

	@Override
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
