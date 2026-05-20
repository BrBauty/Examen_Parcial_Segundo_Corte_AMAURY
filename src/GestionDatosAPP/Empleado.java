package GestionDatosAPP;

public abstract class Empleado extends Persona {
    private double sueldo;
    private Directivo jefe;
    private Empresa empresa;
    public Empleado(String nombre, int edad, String idPersona) {
        super(nombre, edad, idPersona);
        this.sueldo=0;
        this.jefe=null;
        this.empresa=null;
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
        String jefe;
        if (this.getJefe() != null) {
            jefe = this.getJefe().getNombre();
        } else {
            jefe="El empleado no tiene un jefe asignado";
        }
        return super.toString() +
                "\n- Sueldo Calculado: $" + this.getSueldo()+
                "\n- Jefe Asignado: " + jefe+
                "\n- Empresa Asignada: " + this.getEmpresa().getNombre();
    }
}
