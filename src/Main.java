import GestionDatosAPP.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //metodo para pausar pantalla
    public static void pausa(Scanner sc) {
        System.out.print("\nPresione ENTER para continuar...");
        sc.nextLine();
    }
    //metodo para validar la existencia de la empresa
    public static boolean validarEmpresa(List<Empresa> empresas, String nombre) {
        for(Empresa empresa: empresas) {
            if(nombre.equalsIgnoreCase(empresa.getNombre())) {
                return true;
            }
        }
        return false;
    }
    //metodo para agregar una empresa a la lista  del main
    public static void agregarEmpresa(List<Empresa> empresas, Scanner sc) {
        boolean existe;
        System.out.print("Ingrese el nombre del empresa a agregar: ");
        String nombre=sc.nextLine();
        if(empresas.isEmpty()) {
            empresas.add(new Empresa(nombre));
            System.out.println("Registro de empresa Exitoso....");
            pausa(sc);
        }else{
            //se verifica si esa misma empresa ya existe en la lista usando el metodo
            existe=validarEmpresa(empresas, nombre);
            if(existe){
                System.out.println("La Empresa " + nombre +  "ya existe en el sistema.");
                pausa(sc);
            }else{
                empresas.add(new Empresa(nombre));
                System.out.println("Registro de empresa Exitoso....");
                pausa(sc);
            }
        }
    }
    //metodo para agregar clientes a una emppresa de la lista
    public static void agregarCliente(List<Empresa> empresas, Scanner sc) {
        //se verifica la existencia de la empresa en la lista
        System.out.println("Ingrese el nombre de la empresa a la que va a agregar el cliente: ");
        Empresa empresaElegida=null;
        String nombre = sc.nextLine();
        for(Empresa empresa: empresas) {
            if(nombre.equalsIgnoreCase(empresa.getNombre())) {
                empresaElegida = empresa;
                break;
            }
        }
        //si no existe
        if(empresaElegida==null) {
            System.out.println("La empresa "+nombre+" no existe en el sistema.");
            pausa(sc);
        }else{//si existe sé instancia y agrega el cliente a la lista de clientes de esa empresa
            System.out.print("Ingrese el nombre del cliente: ");
            String nombreCliente=sc.nextLine();
            int edadCliente, telefonoCliente;
            do{
                System.out.print("Ingrese la edad del cliente: ");
                edadCliente=sc.nextInt();
                sc.nextLine();
                if(edadCliente<=0){
                    System.out.println("La edad no puede ser 0, ni negativa....");
                    pausa(sc);
                }
            }while(edadCliente<=0);
            do{
                System.out.print("Ingrese el telefono del cliente: ");
                telefonoCliente = sc.nextInt();
                sc.nextLine();
                if(telefonoCliente<=0){
                    System.out.println("El telefono no puede ser 0, ni negativo....");
                    pausa(sc);
                }
            }while(telefonoCliente<=0);
            //se verifica que el cliente aún no exista
            for(Cliente cliente: empresaElegida.getClientes()) {
                if(nombreCliente.equalsIgnoreCase(cliente.getNombre()) &&  edadCliente==cliente.getEdad() && telefonoCliente==cliente.getTelefono()) {
                    System.out.println("Ese cliente YA EXISTE en el sistema");
                    pausa(sc);
                    return;
                }
            }
            empresaElegida.agregarCliente(new Cliente(nombreCliente, edadCliente, telefonoCliente));
            System.out.println("Registro de cliente Exitoso....");
            pausa(sc);
        }
    }
    //metodo para agregar un directivo a una empresa ya seleccionada de la lista del main
    //sé instancia el atributo especial de los directivos y se agrega a la lista
    //se verifica si ya existe un empleado igual en esa lista
    public static void agregarDirectivo(Empresa seleccionada,  Scanner sc, String nombre, int edad) {
        int categoria;
        boolean empleadoExistente = false;
        do {
            System.out.print("Ingrese la categoria del directivo (1-3): ");
            categoria = sc.nextInt();
            sc.nextLine();
            if (categoria < 1 || categoria > 3) {
                System.out.println("[Error] Categoría no valida.");
                pausa(sc);
            }
        } while(categoria < 1 || categoria > 3);

        // Validar existencia de ese directivo en la empresa
        for(Empleado empleado: seleccionada.getEmpleados()) {
            if(empleado.getNombre().equalsIgnoreCase(nombre) && empleado.getEdad() == edad) {
                empleadoExistente=true;
                break;
            }
        }
        if(empleadoExistente){
            System.out.println("El empleado " + nombre + " YA EXISTE en esta empresa.");
            pausa(sc);
        } else {
            Directivo nuevo = new Directivo(nombre, edad, categoria);
            nuevo.setSueldo(nuevo.calcularSueldo());
            seleccionada.agregarEmpleado(nuevo);
            nuevo.setEmpresa(seleccionada);
            System.out.println("Directivo registrado exitosamente");
            pausa(sc);
        }
    }
    //metodo para agregar un  empleado por hora
    public static void agregarEmpleadoHora(Empresa seleccionada,  Scanner sc, String nombre, int edad){
        double horasTrabajadas,  valorHora;
        boolean empleadoExistente = false;
        do {
            System.out.print("Ingrese las horas trabajas por el empleado: ");
            horasTrabajadas = sc.nextDouble();
            sc.nextLine();
            if (horasTrabajadas<=0.0) {
                System.out.println("[Error] Parametro no valido [mayor que 0].");
                pausa(sc);
            }
        } while(horasTrabajadas<=0.0);
        do{
            System.out.print("Ingrese el valor x hora para el empleado: ");
            valorHora = sc.nextDouble();
            sc.nextLine();
            if(valorHora<=0){
                System.out.println("[Error] Parametro no valido [mayor que 0].");
                pausa(sc);
            }
        }while(valorHora<=0);
        for(Empleado empleado: seleccionada.getEmpleados()) {
            if(empleado.getNombre().equalsIgnoreCase(nombre) && empleado.getEdad() == edad) {
                empleadoExistente=true;
                break;
            }
        }
        if(empleadoExistente){
            System.out.println("El empleado " + nombre + " YA EXISTE en el sistema.");
            pausa(sc);
        }else{
            EmpleadoHora nuevo= new EmpleadoHora(nombre, edad, horasTrabajadas, valorHora);
            nuevo.setSueldo(nuevo.calcularSueldo());
            seleccionada.agregarEmpleado(nuevo);
            nuevo.setEmpresa(seleccionada);
            System.out.println("Empleado por hora registrado exitosamente");
            pausa(sc);
        }
    }
    //metodo para agregar un empleado por comision
    public static void agregarEmpleadoComision(Empresa seleccionada,  Scanner sc, String nombre, int edad) {
        double sueldoMinimo, comision, netoVentas;
        boolean empleadoExistente = false;
        do{
            System.out.print("Ingrese el sueldo minimo del empleado: ");
            sueldoMinimo = sc.nextDouble();
            sc.nextLine();
            if(sueldoMinimo<=0){
                System.out.println("[Error] Parametro no valido [mayor que 0].");
                pausa(sc);
            }
        }while(sueldoMinimo<=0);
        do{
            System.out.print("Ingrese el valor de la comision (porcentaje) del empleado[0,0--1,0]: ");
            comision = sc.nextDouble();
            sc.nextLine();
            if(comision<=0.0 || comision>1.0){
                System.out.println("[Error] Parametro no valido [mayor que 0 y menor o igual a 1].");
                pausa(sc);
            }
        }while(comision<=0.0 || comision>1.0);
        do{
            System.out.print("Ingrese el neto de ventas del empleado [mayor que 0]: ");
            netoVentas = sc.nextDouble();
            sc.nextLine();
            if(netoVentas<=0){
                System.out.println("[Error] Parametro no valido [mayor que 0].");
                pausa(sc);
            }
        }while(netoVentas<=0);
        for(Empleado empleado: seleccionada.getEmpleados()) {
            //se evalua si el empleado está en la lista de la empresa
            if(empleado.getNombre().equalsIgnoreCase(nombre) && (empleado.getEdad() == edad)) {
               empleadoExistente=true;
               break;
            }
        }
        if(empleadoExistente){
            System.out.println("El empleado " + nombre + " YA EXISTE en el sistema");
            pausa(sc);
        }else{
            EmpleadoComision nuevo = new EmpleadoComision(nombre, edad, sueldoMinimo, comision, netoVentas);
            nuevo.setSueldo(nuevo.calcularSueldo());
            seleccionada.agregarEmpleado(nuevo);
            nuevo.setEmpresa(seleccionada);
            System.out.println("Empleado por comision registrado exitosamente");
            pausa(sc);
        }

    }
    //metodo para agregar un empleado, se piden los atributos comunes, se verifica la existencia de la empresa
    //y se pide que tipo de empleado se va a agregar
    public static void agregarEmpleado(List<Empresa> empresas, Scanner sc) {
        int op;
        System.out.println("Ingrese el nombre de la empresa a la que va a agregar a el Empleado: ");
        Empresa empresaElegida=null;
        String nombre = sc.nextLine();
        for(Empresa empresa: empresas) {
            if(nombre.equalsIgnoreCase(empresa.getNombre())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa "+nombre+" no existe en el sistema.");
            pausa(sc);
        }else{
            String nombreEmpleado;
            int edadEmpleado;
            System.out.print("Ingrese el nombre del Empleado: ");
            nombreEmpleado=sc.nextLine();
            do{
                System.out.print("Ingrese la edad del empleado: ");
                edadEmpleado = sc.nextInt();
                sc.nextLine();
                if(edadEmpleado <=0){
                    System.out.println("La edad no puede ser 0, ni negativa....");
                    pausa(sc);
                }
            }while(edadEmpleado <=0);
            //validar si el empleado ya hace parte de otra empresa
            for(Empresa empresa: empresas) {
                for(Empleado empleado: empresa.getEmpleados()) {
                    if(empleado.getNombre().equalsIgnoreCase(nombreEmpleado) &&  (empleado.getEdad() == edadEmpleado)) {
                        if(!(empleado.getEmpresa().equals(empresaElegida))){
                            System.out.println("El empleado ya hace parte de una empresa");
                            pausa(sc);
                            return;
                        }
                        break;
                    }
                }
            }
            do{
                System.out.println("""
                =============================
                      TIPOS DE EMPLEADOS
                =============================
                1. Directivo
                2. Empleado Por Hora
                3. Empleado Por Comision
                =============================
                Ingrese el tipo de empleado que desea agregar: \s""");
                op=sc.nextInt();
                sc.nextLine();
                if(op<1 || op>3){
                    System.out.println("[ERROR] Ingrese un parametro valido [1-3]");
                    pausa(sc);
                }
            }while(op<1 || op>3);
            switch (op) {
                case 1:
                    agregarDirectivo(empresaElegida, sc, nombreEmpleado, edadEmpleado);
                    break;
                case 2:
                    agregarEmpleadoHora(empresaElegida, sc, nombreEmpleado, edadEmpleado);
                    break;
                case 3:
                    agregarEmpleadoComision(empresaElegida, sc, nombreEmpleado, edadEmpleado);
                    break;

            }

        }

    }
    //Metodo donde se busca la empresa donde se encuentra el directivo que se necesita
    //De ahi se muestra la lista de empleados de esa empresa para que se seleccione uno para asignarle al directivo
    public static void asignarSubordinado(List<Empresa> empresas, Scanner sc) {
        //validamos la existencia de la empresa
        System.out.print("Ingrese el nombre de la empresa donde esta el director a buscar: ");
        String nombreEmpresa = sc.nextLine();
        Empresa empresaElegida=null;
        for(Empresa empresa: empresas) {
            if(nombreEmpresa.equalsIgnoreCase(empresa.getNombre())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa "+nombreEmpresa+" no existe en el sistema");
            pausa(sc);
        }else{
            //Si la empresa existe se solicita los datos del directivo y se valida su existencia en esa empresa
            String nombreDirectivo;
            int edadDirectivo;
            int indiceEscogido=-1;
            Directivo directivoEncontrado=null;
            System.out.print("Ingrese el nombre del empleado: ");
            nombreDirectivo=sc.nextLine();
            do{
                System.out.print("Ingrese la edad del empleado: ");
                edadDirectivo = sc.nextInt();
                sc.nextLine();
                if(edadDirectivo <=0){
                    System.out.println("La edad no puede ser 0, ni negativa....");
                    pausa(sc);
                }
            }while(edadDirectivo <=0);
            //validacion de existencia en la empresa
            for(Empleado empleado: empresaElegida.getEmpleados()){
                if(nombreDirectivo.equalsIgnoreCase(empleado.getNombre()) &&  edadDirectivo==empleado.getEdad() && empleado instanceof Directivo) {
                    directivoEncontrado=(Directivo)empleado;
                    break;
                }
            }
            if(directivoEncontrado==null){
                System.out.println("El directivo " + nombreDirectivo + " no existe en la empresa " + nombreEmpresa);
                pausa(sc);
            }else{
                //Cuando ya se encuentra el directivo se muestra la lista de empleados de la empresa
                int contador=0;
                System.out.println("Ingrese el subordinado de la empresa del directivo");
                System.out.println("\n=============== EMPLEADOS DE "+ nombreEmpresa.toUpperCase() + " ================");
                for(Empleado empleado: empresaElegida.getEmpleados()){
                    System.out.println("---------------------EMPLEADO #"+(++contador)+"---------------------");
                    if(empleado.equals(directivoEncontrado)) {
                        indiceEscogido=(contador);
                        System.out.println("Directivo Escogido");//si el empleado a mostrar es el directivo mostramos que es directivo escogido
                    }else{
                        System.out.println(empleado);//caso contrario se muestra el toString del empleado
                    }
                    System.out.println("------------------------------------------------------------------");
                }
                System.out.println("==================================================================");
                int numero;
                //se valida la eleccion del subordinado
                //la eleccion del subordinado debe estar dentro de la lista y no puede ser el directivo en cuestion
                if(empresaElegida.getEmpleados().size()==1){//evalua sí hay más de un empleado
                    System.out.println("Necesita al menos dos empleados distintos registrados en la empresa: ");
                    pausa(sc);
                }else{
                    do{
                        System.out.print("Elija el numero del empleado a seleccionar como subordinado de " + nombreDirectivo + ": ");
                        numero=sc.nextInt();
                        sc.nextLine();
                        if(numero<=0 || numero>(empresaElegida.getEmpleados().size()) || numero == indiceEscogido){
                            System.out.println("Ingrese un parametro valido [1-"+empresaElegida.getEmpleados().size()+"] y el indice de un empleado distinto al directivo escogido");
                        }
                    }while(numero<=0 || numero>(empresaElegida.getEmpleados().size()) || numero == indiceEscogido);
                    //evalua si el subordinado ya tiene jefe
                    if(empresaElegida.getEmpleados().get(numero-1).getJefe()==null){//si no tiene jefe
                            Empleado subordinado=empresaElegida.getEmpleados().get(numero-1);
                            directivoEncontrado.agregarSubordinado(subordinado);//se asigan el subordinado dentro de la lista del directivo
                            subordinado.setJefe(directivoEncontrado);
                            System.out.println(" Empleado "+subordinado.getNombre()+" asignado exitosamente a "+nombreDirectivo);
                            pausa(sc);
                    }else{//si tiene un jefe se comprueba si es el directivo elegido o es otro
                        //avisa que ya tiene un jefe
                        if(directivoEncontrado.equals(empresaElegida.getEmpleados().get(numero-1).getJefe())){
                            System.out.println("El empleado ya ha sido asignado como subordinado a "+directivoEncontrado.getNombre());
                            pausa(sc);//muestra que el directivo escogido ya tiene este subordinado
                        }else{//muestra que otro directivo tiene este subordinado
                            System.out.println("El empleado ya es subordinado de otro directivo");
                            pausa(sc);
                        }
                    }
                }
            }
        }
    }
    //metodo para mostrar los datos de los empleados de una empresa
    public static void consultarEmpleados(List<Empresa> empresas, Scanner sc){
        System.out.print("Ingrese el nombre de la empresa para ver sus empleados: ");
        String nombreEmpresa = sc.nextLine();

        for (Empresa empresa : empresas) {
            if (nombreEmpresa.equalsIgnoreCase(empresa.getNombre())) {
                System.out.println("\n=============== EMPLEADOS DE " + empresa.getNombre().toUpperCase() + " ===============");
                empresa.mostrarEmpleadosEmpresa();
                pausa(sc);
                return;
            }
        }
        System.out.println("La empresa " + nombreEmpresa + " no existe en el sistema.");
        pausa(sc);
    }

    //metodo para mostrar los datos de los clientes de una empresa
    public static void consultarClientes(List<Empresa> empresas, Scanner sc){
        System.out.print("Ingrese el nombre de la empresa para ver sus clientes: ");
        String nombreEmpresa = sc.nextLine();
        for (Empresa empresa : empresas) {
            if (nombreEmpresa.equalsIgnoreCase(empresa.getNombre())) {
                System.out.println("\n=============== CLIENTES DE " + empresa.getNombre().toUpperCase() + " ===============");
                empresa.mostrarClientesEmpresa();
                pausa(sc);
                return;
            }
        }
        System.out.println("La empresa " + nombreEmpresa + " no existe en el sistema.");
        pausa(sc);
    }

    //metodo para mostrar todos los subordinados de un directivo de una empresa
    public static void consultarSubordinados(List<Empresa> empresas, Scanner sc) {
        //validamos la existencia de la empresa
        System.out.print("Ingrese el nombre de la empresa donde esta el director a buscar: ");
        String nombreEmpresa = sc.nextLine();
        Empresa empresaElegida=null;
        for(Empresa empresa: empresas) {
            if(nombreEmpresa.equalsIgnoreCase(empresa.getNombre())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa "+nombreEmpresa+" no existe en el sistema");
            pausa(sc);
        }else{
            //Si la empresa existe se solicita los datos del directivo y se valida su existencia en esa empresa
            String nombreDirectivo;
            int edadDirectivo;
            Directivo directivoEncontrado=null;
            System.out.print("Ingrese el nombre del empleado: ");
            nombreDirectivo=sc.nextLine();
            do{
                System.out.print("Ingrese la edad del empleado: ");
                edadDirectivo = sc.nextInt();
                sc.nextLine();
                if(edadDirectivo <=0){
                    System.out.println("La edad no puede ser 0, ni negativa....");
                    pausa(sc);
                }
            }while(edadDirectivo <=0);
            //validacion de existencia en la empresa
            for(Empleado empleado: empresaElegida.getEmpleados()){
                if(nombreDirectivo.equalsIgnoreCase(empleado.getNombre()) &&  edadDirectivo==empleado.getEdad() && empleado instanceof Directivo) {
                    directivoEncontrado=(Directivo)empleado;
                    break;
                }
            }
            if(directivoEncontrado==null){
                System.out.println("El directivo " + nombreDirectivo + " no existe en la empresa " + nombreEmpresa);
                pausa(sc);
            }else{
                //Cuando ya se encuentra el directivo se muestra su lista de subordinados
                directivoEncontrado.mostrarSubordinados();
                pausa(sc);
            }
        }
    }
    //metodo para mostrar el sueldo promedio de los subordinados de un directivo
    public static void consultarSubordinadosPromedios(List<Empresa> empresas, Scanner sc){
        System.out.print("Ingrese el nombre de la empresa donde esta el director a buscar: ");
        String nombreEmpresa = sc.nextLine();
        Empresa empresaElegida=null;
        for(Empresa empresa: empresas) {
            if(nombreEmpresa.equalsIgnoreCase(empresa.getNombre())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa "+nombreEmpresa+" no existe en el sistema");
            pausa(sc);
        }else{
            //Si la empresa existe se solicita los datos del directivo
            //se valida su existencia dentro del metodo
            String nombreDirectivo;
            int categoriaDirectivo;
            System.out.print("Ingrese el nombre del empleado: ");
            nombreDirectivo=sc.nextLine();
            do{
                System.out.print("Ingrese la categoria del empleado: ");
                categoriaDirectivo = sc.nextInt();
                sc.nextLine();
                if(categoriaDirectivo <1 || categoriaDirectivo > 3){
                    System.out.println("[ERROR] Categoria no valida (1-3)....");
                    pausa(sc);
                }
            }while(categoriaDirectivo <1 || categoriaDirectivo > 3);
            //validacion de existencia en la empresa usando el metodo de empresa y muestra de promedio
            empresaElegida.mostrarPromedioSubordinados(categoriaDirectivo, nombreDirectivo);
            pausa(sc);
        }
    }

    //submenu de consultas
    public static void consultas(List<Empresa> empresas, Scanner sc){
        int ele;
        boolean sal=false;
        do{
            do{
                System.out.print("""
                ================================================================
                                      MENU DE CONSULTAS
                ================================================================
                1. Consultar Empleados de una empresa
                2. Consultar Clientes de una empresa
                3. Consultar subordinados de un directivo
                4. Consultar sueldo promedio de los subordinados de un Directivo
                5. salir del submenu
                =================================================================
                Eleccion: \s""");
                ele=sc.nextInt();
                sc.nextLine();
                if(ele<1 || ele>5){
                    System.out.println("Ingrese un numero valido [1-5]");
                }
            }while(ele<1 || ele>5);
            switch(ele){
                case 1:
                    consultarEmpleados(empresas, sc);
                    break;
                case 2:
                    consultarClientes(empresas, sc);
                    break;
                case 3:
                    consultarSubordinados(empresas, sc);
                    break;
                case 4:
                    consultarSubordinadosPromedios(empresas, sc);
                    break;
                case 5:
                    sal=true;
                    System.out.println("Saliendo del submenu CONSULTAS....");
                    pausa(sc);
                    break;
            }
        }while(!sal);

    }

    public static void main(String[] args) {
        List<Empresa> empresas=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        boolean salir=false;
        int opcion;
        do{
            do{
                System.out.print("""
                        ==============================
                            SISTEMA DE GESTIÓN
                        ==============================
                        1. Registrar Empresa
                        2. Registrar Cliente
                        3. Registrar Empleado
                        4. Asignar Subordinado
                        5. Consultas
                        6. Salir
                        ==============================
                        Elige una opcion: \s""");
                opcion=sc.nextInt();
                sc.nextLine();
                if(opcion<1 || opcion>6){
                    System.out.println("Ingrese un numero valido [1-6]");
                    pausa(sc);
                }
            }while(opcion<1 || opcion>6);
            switch (opcion){
                case 1:
                    agregarEmpresa(empresas, sc);
                    break;
                case 2:
                    agregarCliente(empresas, sc);
                    break;
                case 3:
                    agregarEmpleado(empresas, sc);
                    break;
                case 4:
                    asignarSubordinado(empresas, sc);
                    break;
                case 5:
                    consultas(empresas, sc);
                    break;
                case 6:
                    salir=true;
                    System.out.println("Saliendo del sistema....");
                    break;
            }
        }while(!salir);
        System.out.println("Salida Exitosa....");
        pausa(sc);
    }
}
