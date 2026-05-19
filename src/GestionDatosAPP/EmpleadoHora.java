package GestionDatosAPP;

public class EmpleadoHora extends Empleado{
    private double horasTrabajadas;
    private double valorHora;

    public EmpleadoHora(String nombre, int edad, double horasTrabajadas, double valorHora) {
        super(nombre, edad);
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }
    //metodo para calcular sueldo de EmpleadoHora
    @Override
    public double calcularSueldo() {
        return this.horasTrabajadas * this.valorHora;
    }
    //toString para mostrar en la clase empresa
    @Override
    public String toString() {
        return "=== EMPLEADO POR HORA ===\n" +
                super.toString() +
                "\n- Horas Trabajadas: " + this.horasTrabajadas +
                "\n- Valor por Hora: $" + this.valorHora +
                "\n=========================";
    }
}
