package GestionDatosAPP;

public class Cliente extends Persona {
    private  int telefono;
    public Cliente(String nombre, int edad,  int telefono) {
        super(nombre, edad);
        this.telefono = telefono;
    }
    public int getTelefono() {
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