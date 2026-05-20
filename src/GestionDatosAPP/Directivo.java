package GestionDatosAPP;

import java.util.List;
import java.util.ArrayList;

public class Directivo extends Empleado{
    private int categoria;

    /* Justificación del atributo subordinados:
     El enunciado dice que los directivos tienen un conjunto de empleados.
     Además, hay una línea de composicion en el uml con multiplicidad 1..*.
     Esto es basicamente una lista de empleados en Directivo*/
    private List<Empleado> subordinados;

    public Directivo(String nombre, int edad, int categoria, String idPersona) {
        super(nombre, edad,  idPersona);
        this.categoria = categoria;
        this.subordinados = new ArrayList<>(); //se inicaliza la lista vacia, para añadir empleados despues
    }

    // Metodo para cumplir con la composicion 1..* del uml
    public void agregarSubordinado(Empleado empleado) {
        this.subordinados.add(empleado);
    }

    //metodo para calcular el sueldo de Directivo
    @Override
    public double calcularSueldo(){
        double sueldoCalculado=0;
        if (this.categoria == 1) {
            sueldoCalculado=5500000.0;
        } else if (this.categoria == 2) {
            sueldoCalculado=6500000.0;
        } else if (this.categoria == 3) {
            sueldoCalculado=7500000.0;
        }
        return sueldoCalculado;
    }

    //getters
    public int getCategoria() {
        return categoria;
    }
    public List<Empleado> getSubordinados() {
        return subordinados;
    }
    //Metodo para mostrar la lista de subordinados
    public void mostrarSubordinados(){
        System.out.println("=== Subordinados de " + this.getNombre() + " ===");
        if (this.subordinados==null || this.subordinados.isEmpty()) {
            System.out.println("No tiene empleados a cargo.");
        } else {
            for (Empleado emp : this.subordinados) {
                System.out.println(emp.toString());
            }
        }
    }
    //toSring para Directivo el cual será usado en la clase empresa
    @Override
    public String toString() {
        return "=== DIRECTIVO ===\n" +
                super.toString() +
                "\n- Categoría: " + this.getCategoria() +
                "\n- Subordinados a cargo: " + this.subordinados.size() +
                "\n=================";
    }

}
