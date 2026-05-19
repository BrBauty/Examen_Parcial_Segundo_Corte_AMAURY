package GestionDatosAPP;

public class Persona {
    private String nombre;
    private int edad;
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    // Metodo toString para mostrar los datos comunes
    @Override
    public String toString() {
        return "- Nombre: " + this.getNombre() +
                "\n- Edad: " + this.getEdad();
    }
}