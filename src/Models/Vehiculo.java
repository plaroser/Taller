package Models;

public abstract class Vehiculo implements Arrancable, Movible {
	private String color;
	private String matricula;
	private String marca;
	private String modelo;
	private String averia;
	private float precioReparacion;
	private float totalPagado;
	private int velocidadMaxima;
	private float velocidadActual;
	private int telefonoDueño;
	private boolean estado;

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
		this.averia = null;
		this.precioReparacion = 0;
		this.totalPagado = 0;
	}

	public Vehiculo(String color, String matricula, String marca, String modelo, int velocidadMaxima, int telefonoDueño,
			String averia) {
		this(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño);
		this.averia = averia;
	}

	/**
	 * Realiza un aumento a total pagado No podra ser iferior a 0
	 * 
	 * @param pago
	 *            a realizar.
	 */
	public void pagarReparacion(float pago) {
		if (pago > 0) {
			if (this.totalPagado < this.precioReparacion) {
				this.totalPagado += pago;
			} else {
				if ((this.precioReparacion - this.totalPagado) >= pago) {
					this.totalPagado += pago;
					System.out.println("Pago realizado con exito.");
				} else {
					float restante = this.precioReparacion - this.totalPagado;
					this.totalPagado += restante;
					System.out.println("Pago realizado con exito.");
					System.out.println("Le sobran: " + (pago - restante) + " Eur.");
				}
			}
		} else {
			System.out.println("[ERROR]: El importe a pagar no puede ser inferior o igual a 0.");
		}
	}

	public float getPrecioReparacion() {
		return precioReparacion;
	}

	public float getTotalPagado() {
		return totalPagado;
	}

	/**
	 * Establece un presupuesto de la reparacion del vehiculo
	 * 
	 * @param coste
	 *            de la reparacion
	 */
	public void darPrecioReparacion(float precio) {
		this.precioReparacion = precio;
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

	public String getAveria() {
		return averia;
	}

	public String getColor() {
		return color;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public int getVelocidadMaxima() {
		return velocidadMaxima;
	}

	public float getVelocidadActual() {
		return velocidadActual;
	}

	public int getTelefonoDueño() {
		return telefonoDueño;
	}

	public boolean isEstado() {
		return estado;
	}

	@Override
	public String toString() {
		return marca + " " + modelo + " con matricula " + matricula;
	}

}
