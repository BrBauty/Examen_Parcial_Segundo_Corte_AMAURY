package GestionDatosAPP;

public class EmpleadoComision extends Empleado{
    private double sueldoMinimo;
    private double comision;
    private double netoVentas;

    public EmpleadoComision(String nombre, int edad, double sueldoMinimo, double comision, double netoVentas){
        super(nombre, edad);
        this.sueldoMinimo = sueldoMinimo;
        this.comision = comision;
        this.netoVentas = netoVentas;
    }

    //metodo para calcular el sueldo de EmpleadoComision
    @Override
    public double calcularSueldo() {
        return (this.comision * this.netoVentas)+this.sueldoMinimo;
    }

    //toString pra mostrar en Empresa
    @Override
    public String toString() {
        return "=== EMPLEADO POR COMISIÓN ===\n" +
                super.toString() +
                "\n- Sueldo Base: $" + this.sueldoMinimo +
                "\n- Comisión: " + (this.comision * 100) + "%\n" +
                "- Ventas Netas: $" + this.netoVentas +
                "\n=========================";
    }
}
