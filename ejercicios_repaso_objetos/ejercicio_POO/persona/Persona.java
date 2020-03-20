package persona;

public class Persona {
	
	private String nombrePersona;
	private int edadPersona;
	private double alturaPersona;
	
	public Persona (String nombreParaAsignar, int edadParaAsignar, double alturaParaAsignar) {
		this.nombrePersona=nombreParaAsignar;
		this.edadPersona=edadParaAsignar;
		this.alturaPersona=alturaParaAsignar;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombrePersona + ", Edad: " + edadPersona + ", Altura: " + alturaPersona + ".";
	}
	
	
	
}
