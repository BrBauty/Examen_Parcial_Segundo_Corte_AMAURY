package GestionDatosAPP;

public class Persona {
    private String nombre;
    private int edad;
    private String idPersona;//para poder buscar empleados y clientes de manera eficiente
    public Persona(String nombre, int edad,  String idPersona) {
        this.nombre = nombre;
        this.edad = edad;
        this.idPersona = idPersona;
    }
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    public String getIdPersona() {return idPersona;}
    // Metodo toString para mostrar los datos comunes
    @Override
    public String toString() {
        return "- Nombre: " + this.getNombre() +
                "\n- Edad: " + this.getEdad()+
                "\n- Id: " + this.getIdPersona();
    }
}