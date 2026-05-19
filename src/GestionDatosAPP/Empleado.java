package GestionDatosAPP;

public abstract class Empleado extends Persona {
    private double sueldo;
    private Directivo jefe=null;
    private Empresa empresa=null;
    public Empleado(String nombre, int edad) {
        super(nombre, edad);
        this.sueldo=0;
    }
    public abstract double calcularSueldo();

    /*Justificacion: Se agregan Getters y Setters para que las clases hijas,
    que implementan calcularSueldo(), puedan guardar el resultado en esta variable privada.
    El proceso para setSueldo se calcula en las clases hijas y debe unificarse con el main
    para que setSueldo le  otorgue un valor a sueldo*/
    public double getSueldo() {
        return sueldo;
    }
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Directivo getJefe() {
        return jefe;
    }

    public void setJefe(Directivo jefe) {
        this.jefe = jefe;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    // toString para mostar los datos de un empleado cualquiera, sera usado en la clase empresa
    @Override
    public String toString() {
        return super.toString() +
                "\n- Sueldo Calculado: $" + this.getSueldo();
    }
}
