package GestionDatosAPP;

public class Cliente extends Persona {
    private  long telefono;
    public Cliente(String nombre, int edad,  long telefono, String idPersona) {
        super(nombre, edad, idPersona);
        this.telefono = telefono;
    }
    public long getTelefono() {
        return telefono;
    }
    //toString para mostrar los datos de cliente, será usado en la clase empresa
    @Override
    public String toString() {
        return "=== CLIENTE ===\n" +
                super.toString() +
                "\n- Teléfono: " + this.getTelefono() +
                "\n===============";
    }
}