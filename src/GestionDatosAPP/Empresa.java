package GestionDatosAPP;
import java.util.List;
import java.util.ArrayList;
public class Empresa {
    private String nombre;

    /*Justificación: Estas listas demuestran las relaciones estructurales del uml
    composicion de Empresa con empleado y Agregacion de clientes con empresa*/
    private List<Empleado> empleados;
    private List<Cliente> clientes;

    public Empresa(String nombre) {
        this.nombre = nombre;
        //Se inicalizan las listas para poder recibir datos
        this.empleados = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    //metodos para rellenar las listas de las relaciones de composicion y agregacion
    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void agregarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    //getters
    public String getNombre() {
        return nombre;
    }
    public List<Empleado> getEmpleados() {
        return empleados;
    }
    public List<Cliente> getClientes() {
        return clientes;
    }

    //metodo de mostrar clientes del Punto C
    public void mostrarClientesEmpresa() {
        if (this.clientes==null || this.clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("\n=== LISTA DE CLIENTES ===");
            for (Cliente c : this.clientes) {
                System.out.println(c.toString());
            }
        }
    }

    //metodo de mostrar empleados del Punto C
    public void mostrarEmpleadosEmpresa() {
        if (this.empleados==null || this.empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("\n=== LISTA DE EMPLEADOS ===");
            for (Empleado e : this.empleados) {
                System.out.println(e.toString());
            }
        }
    }

    //Metodo de mostrar el promedio de los subordinados de un directivo del Punto C
    public void mostrarPromedioSubordinados(int categoria, String nombreDirectivo) {
        Directivo directivoEncontrado = null;
        //Buscar al directivo en la lista de empleados
        for (Empleado empleado : this.empleados) {
            // Verificar si es directivo
            if (empleado instanceof Directivo) {
                //Verificar si es el directivo solicitado
                Directivo dir=(Directivo)empleado;
                if (dir.getNombre().equalsIgnoreCase(nombreDirectivo) && dir.getCategoria()==categoria) {
                    directivoEncontrado = dir;
                    break;
                }
            }
        }
        // Mensaje si no se encuentra
        if (directivoEncontrado == null) {
            System.out.println("\n[Error]: No se encontró al Directivo "+nombreDirectivo+" con categoría "+categoria);
            return;
        }
        // Si se encuentra. Usar su lista y calcular promedio
        List<Empleado> subordinados = directivoEncontrado.getSubordinados();
        if (subordinados==null || subordinados.isEmpty()) {
            System.out.println("\nEl directivo "+nombreDirectivo+" no tiene subordinados.");
        } else {
            double sumaSueldos = 0;
            for (Empleado sub : subordinados) {
                sumaSueldos+=sub.calcularSueldo();
            }
            double promedio=sumaSueldos / subordinados.size();
            System.out.println("\nEl promedio de sueldo de los subordinados de "+ nombreDirectivo+" es: $"+promedio);
        }
    }
}
