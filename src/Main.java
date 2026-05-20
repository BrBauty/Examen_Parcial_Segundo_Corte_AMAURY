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
    public static boolean validarEmpresa(List<Empresa> empresas, String idEmpresa) {
        for(Empresa empresa: empresas) {
            if(idEmpresa.equals(empresa.getIdEmpresa())) { //compara los ID para que no existan dos empresas iguales
                return true;
            }
        }
        return false;
    }
    //metodo para agregar una empresa a la lista del main
    public static void agregarEmpresa(List<Empresa> empresas, Scanner sc) {
        boolean existe;
        //se registran los datos de la empresa a agregar
        System.out.print("Ingrese el nombre del empresa a agregar: ");
        String nombre=sc.nextLine();
        String idEmpresa;
        do{
            //se valida el registro del ID de la empresa
            System.out.print("Ingrese el ID de la empresa a agregar [Debe Empezar por E]: ");
            idEmpresa=sc.nextLine();
            idEmpresa = idEmpresa.trim();
            if(idEmpresa.charAt(0)!='E' ||  idEmpresa.length()<2) {
                System.out.println("[ERROR] ID no válido.");
                System.out.println("El ID debe empezar obligatoriamente con 'E' mayúscula seguida de más caracteres.");
                pausa(sc);
            }
        }while(idEmpresa.charAt(0)!='E' || idEmpresa.length()<2);
        if(empresas.isEmpty()) {//si en el main la lista de empresas esta vacia se agrega directamente
            empresas.add(new Empresa(nombre, idEmpresa));
            System.out.println("Registro de empresa Exitoso....");
            pausa(sc);
        }else{
            //Caso contrario se verifica si esa misma empresa ya existe en la lista usando el metodo validar empresa
            existe=validarEmpresa(empresas, idEmpresa);
            if(existe){
                System.out.println("Ya existe una empresa con el ID " + idEmpresa);
                pausa(sc);
            }else{
                empresas.add(new Empresa(nombre, idEmpresa));
                System.out.println("Registro de empresa Exitoso....");
                pausa(sc);
            }
        }
    }
    //metodo para agregar clientes a una empresa de la lista
    public static void agregarCliente(List<Empresa> empresas, Scanner sc) {
        //se verifica la existencia de la empresa en la lista de empresas
        System.out.print("Ingrese el ID de la empresa a la que va a agregar el cliente: ");
        Empresa empresaElegida=null;
        String idEmpresa = sc.nextLine();
        for(Empresa empresa: empresas) {
            if(idEmpresa.equals(empresa.getIdEmpresa())) {//si se encuentra se registra la empresa elegida
                empresaElegida = empresa;
                break;
            }
        }
        //si no existe
        if(empresaElegida==null) {
            System.out.println("La empresa no existe en el sistema.");
            pausa(sc);
        }else{//si existe sé instancia y agrega el cliente a la lista de clientes de esa empresa
            String idCliente;
            Cliente clienteElegido=null;
            do{
                System.out.print("Ingrese El ID del cliente [Debe empezar con C]: ");
                idCliente=sc.nextLine().trim();
                if(idCliente.charAt(0)!='C' || idCliente.length()<2) {
                    System.out.println("[ERROR] ID no válido.");
                    System.out.println("El ID debe empezar obligatoriamente con 'C' mayúscula seguida de más caracteres.");
                    pausa(sc);
                }
            }while(idCliente.charAt(0)!='C' ||  idCliente.length()<2);
            //Se verifica si ya existe ese cliente
            for(Empresa empresa: empresas) {
                for(Cliente cliente: empresa.getClientes()) {
                    if(idCliente.equals(cliente.getIdPersona())) {
                        clienteElegido=cliente;
                        break;
                    }
                }
            }
            //si existe entonces
            if(clienteElegido!=null) {
                //se verifica que el cliente aún no exista en la empresa elegida
                for(Cliente cliente: empresaElegida.getClientes()) {
                    if(idCliente.equals(cliente.getIdPersona())) {
                        System.out.println("Ese cliente YA EXISTE en la empresa " + empresaElegida.getNombre());
                        pausa(sc);
                        return;
                    }
                }
                //Si aún no existe se agrega en la empresa
                empresaElegida.agregarCliente(new Cliente(clienteElegido.getNombre(), clienteElegido.getEdad(), clienteElegido.getTelefono(), clienteElegido.getIdPersona()));
                System.out.println("El cliente ya existía en el sistema. Vinculado a " + empresaElegida.getNombre() + " exitosamente.");
                pausa(sc);
            }else{//Sino se solicitan y validan los datos para agregarlo a la lista
                System.out.print("Ingrese el nombre del cliente: ");
                String nombreCliente=sc.nextLine();
                int edadCliente;
                long telefonoCliente;
                //Se toman los datos del cliente y se valida la entrada de estos mismos datos
                do{
                    System.out.print("Ingrese la edad del cliente: ");
                    edadCliente=sc.nextInt();
                    sc.nextLine();
                    if(edadCliente<=15 || edadCliente>120){
                        System.out.println("[ERROR] Edad no valida [16-120]....");
                        pausa(sc);
                    }
                }while(edadCliente<=15 || edadCliente>120);//para efectos del programa los clientes deben tener entres 16 y 120 años
                do{
                    System.out.print("Ingrese el telefono del cliente: ");
                    telefonoCliente = sc.nextLong();
                    sc.nextLine();
                    if(telefonoCliente<=0){
                        System.out.println("El telefono no puede ser 0, ni negativo....");
                        pausa(sc);
                    }
                }while(telefonoCliente<=0);
                //Si aún no existe se agrega en la empresa
                empresaElegida.agregarCliente(new Cliente(nombreCliente, edadCliente, telefonoCliente, idCliente));
                System.out.println("Registro de nuevo cliente Exitoso....");
                pausa(sc);
            }

        }
    }
    //metodo para agregar un directivo a una empresa ya seleccionada de la lista del main
    //sé instancia el atributo especial de los directivos y se agrega a la lista
    //se verifica si ya existe un empleado igual en esa lista
    public static void agregarDirectivo(Empresa seleccionada,  Scanner sc, String nombre, int edad, String idEmpleado) {
        int categoria;
        //ingresar y validar datos del directivo
        do {
            System.out.print("Ingrese la categoria del directivo (1-3): ");
            categoria = sc.nextInt();
            sc.nextLine();
            if (categoria < 1 || categoria > 3) {
                System.out.println("[Error] Categoría no valida.");
                pausa(sc);
            }
        } while(categoria < 1 || categoria > 3);
        //se agrega a la empresa debido a que ya se validó que aún no exista en una empresa en agregarEmpleado
        Directivo nuevo = new Directivo(nombre, edad, categoria, idEmpleado);
        nuevo.setSueldo(nuevo.calcularSueldo());
        seleccionada.agregarEmpleado(nuevo);
        nuevo.setEmpresa(seleccionada);//se le asigna al atributo del empleado que ya tiene empresa
        System.out.println("Directivo registrado exitosamente");
        pausa(sc);
    }
    //metodo para agregar un  empleado por hora
    public static void agregarEmpleadoHora(Empresa seleccionada,  Scanner sc, String nombre, int edad, String idEmpleado){
        double horasTrabajadas,  valorHora;
        //Ingreso de datos y validacion de datos
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
        //se agrega a la empresa debido a que ya se validó que aún no exista en una empresa en agregarEmpleado
        //y se le pone en su atributo que ya pertenece a esta empresa
        EmpleadoHora nuevo= new EmpleadoHora(nombre, edad, horasTrabajadas, valorHora, idEmpleado);
        nuevo.setSueldo(nuevo.calcularSueldo());
        seleccionada.agregarEmpleado(nuevo);
        nuevo.setEmpresa(seleccionada);
        System.out.println("Empleado por hora registrado exitosamente");
        pausa(sc);
    }
    //metodo para agregar un empleado por comision
    public static void agregarEmpleadoComision(Empresa seleccionada,  Scanner sc, String nombre, int edad, String idEmpleado) {
        double sueldoMinimo, comision, netoVentas;
        //agregacion y validacion de datos
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
        //se agrega a la empresa debido a que ya se validó que aún no exista en una empresa en agregarEmpleado
        EmpleadoComision nuevo = new EmpleadoComision(nombre, edad, sueldoMinimo, comision, netoVentas, idEmpleado);
        nuevo.setSueldo(nuevo.calcularSueldo());
        seleccionada.agregarEmpleado(nuevo);
        nuevo.setEmpresa(seleccionada);
        System.out.println("Empleado por comision registrado exitosamente");
        pausa(sc);
    }
    //metodo para agregar un empleado, se piden los atributos comunes, se verifica la existencia de la empresa
    //y se pide que tipo de empleado se va a agregar
    public static void agregarEmpleado(List<Empresa> empresas, Scanner sc) {
        int op;
        //se agrega el id de la empresa a evaluar
        System.out.print("Ingrese el ID de la empresa a la que va a agregar a el Empleado: ");
        Empresa empresaElegida=null;
        String id = sc.nextLine();
        for(Empresa empresa: empresas) {
            if(id.equals(empresa.getIdEmpresa())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa "+ id +" no existe en el sistema.");
            pausa(sc);
        }else{
            //Se ingresan y validan datos comunes del empleado
            String nombreEmpleado, idEmpleado;
            int edadEmpleado;
            System.out.print("Ingrese el nombre del Empleado: ");
            nombreEmpleado=sc.nextLine();
            //para efectos del sistema y que sea realista la edad debe estar entre 16 y 120
            do{
                System.out.print("Ingrese la edad del empleado: ");
                edadEmpleado = sc.nextInt();
                sc.nextLine();
                if(edadEmpleado <=15 || edadEmpleado>120){
                    System.out.println("[ERROR] Edad no valida [16-120]....");
                    pausa(sc);
                }
            }while(edadEmpleado <=15 || edadEmpleado>120);
            //validacion del id
            do{
                System.out.print("Ingrese el ID del empleado (Debe empezar por 'EPL'): ");
                idEmpleado = sc.nextLine().trim();
                if(!idEmpleado.startsWith("EPL") || idEmpleado.length() < 4){
                    System.out.println("[ERROR] ID no válido.");
                    System.out.println("El ID debe empezar obligatoriamente con 'EPL' en mayúscula seguida de más caracteres.");
                    pausa(sc);
                }
            }while(!idEmpleado.startsWith("EPL") || idEmpleado.length() < 4);
            //validar si el empleado ya hace parte de otra empresa
            for(Empresa empresa: empresas) {
                //se busca en la lista de empleados de cada empresa, un empleado con el mismo ID
                for(Empleado empleado: empresa.getEmpleados()) {
                    //si se encuentra el empleado se compara si ya está en otra empresa
                    if(empleado.getIdPersona().equals(idEmpleado)) {
                        //si no está en la empresa elegida, entonces está en otra empresa
                        if(!empleado.getEmpresa().equals(empresaElegida)){
                            System.out.println("El empleado ya hace parte de una empresa");
                        }else{//caso contrario si ya existe un empleado con ese id entonces ya existe dentro de la empresa seleccionada
                            System.out.println("El empleado "+nombreEmpleado+" ya hace parte de la empresa "+empresaElegida.getNombre());
                        }
                        pausa(sc);
                        return;
                    }
                }
            }
            //Se desplega un menu para elegir el tipo de empleado a ingresar
            do{
                System.out.println("""
                =============================
                      TIPOS DE EMPLEADOS
                =============================
                1. Directivo
                2. Empleado Por Hora
                3. Empleado Por Comision
                =============================
                Ingrese el tipo de empleado que desea agregar:\s""");
                op=sc.nextInt();
                sc.nextLine();
                if(op<1 || op>3){
                    System.out.println("[ERROR] Ingrese un parametro valido [1-3]");
                    pausa(sc);
                }
            }while(op<1 || op>3);
            //una vez elegido se procede a uno de los tres metodo de agregar empleados
            switch (op) {
                case 1:
                    agregarDirectivo(empresaElegida, sc, nombreEmpleado, edadEmpleado, idEmpleado);
                    break;
                case 2:
                    agregarEmpleadoHora(empresaElegida, sc, nombreEmpleado, edadEmpleado, idEmpleado);
                    break;
                case 3:
                    agregarEmpleadoComision(empresaElegida, sc, nombreEmpleado, edadEmpleado, idEmpleado);
                    break;
            }
        }
    }
    //Metodo donde se busca la empresa donde se encuentra el directivo que se necesita
    //De ahi se muestra la lista de empleados de esa empresa para que se seleccione uno para asignarle al directivo
    public static void asignarSubordinado(List<Empresa> empresas, Scanner sc) {
        //validamos la existencia de la empresa
        System.out.print("Ingrese el ID de la empresa donde esta el director a buscar: ");
        String idEmpresa = sc.nextLine().trim();
        Empresa empresaElegida=null;
        for(Empresa empresa: empresas) {
            if(idEmpresa.equals(empresa.getIdEmpresa())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa no existe en el sistema");
            pausa(sc);
        }else{
            //Si la empresa existe se solicita el ID del directivo y se valida su existencia en esa empresa
            String idDirectivo;
            int indiceEscogido=-1;
            Directivo directivoEncontrado=null;
            System.out.print("Ingrese el ID del Directivo al cual le va a asignar un subordinado: ");
            idDirectivo =sc.nextLine().trim();
            //validacion de existencia del Directivo a evaluar en la empresa
            for(Empleado empleado: empresaElegida.getEmpleados()){
                if(idDirectivo.equals(empleado.getIdPersona())&& empleado instanceof Directivo) {
                    directivoEncontrado=(Directivo)empleado;
                    break;
                }
            }
            if(directivoEncontrado==null){ //si no se encuentra en la empresa
                System.out.println("El directivo con ID: " + idDirectivo + " no existe en la empresa " + empresaElegida.getNombre());
                pausa(sc);
            }else{
                //Si se encuentra:
                //Cuando ya se encuentra el directivo se muestra la lista de empleados de la empresa
                int contador=0;
                System.out.println("Ingrese el subordinado de la empresa del directivo");
                System.out.println("\n=============== EMPLEADOS DE "+ empresaElegida.getNombre().toUpperCase() + " ================");
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
                if(empresaElegida.getEmpleados().size()==1){
                    //evalua sí hay más de un empleado, sinó entonces el unico empleado debe ser el Directivo a evaluar
                    System.out.println("Necesita al menos dos empleados distintos registrados en la empresa: ");
                    pausa(sc);
                }else{
                    Empleado subordinado;
                    do{
                        //Se valida la eleccion del subordinado para que el indice exista dentro de la lista
                        System.out.print("Elija el numero del empleado a seleccionar como subordinado de " + idDirectivo + ": ");
                        numero=sc.nextInt();
                        sc.nextLine();
                        if(numero<=0 || numero>(empresaElegida.getEmpleados().size()) || numero == indiceEscogido){
                            System.out.println("Ingrese un parametro valido [1-"+empresaElegida.getEmpleados().size()+"] y el indice de un empleado distinto al directivo escogido");
                        }
                    }while(numero<=0 || numero>(empresaElegida.getEmpleados().size()) || numero == indiceEscogido);
                    subordinado=empresaElegida.getEmpleados().get(numero-1);
                    //antes de cualquier cosa verificar que el posible subordinado NO SEA otro DIRECTIVO
                    if(subordinado instanceof Directivo) {
                        System.out.println("Un directivo no puede ser Jefe de otro directivo");
                        pausa(sc);
                        return;
                    }
                    //Una vez elegido el indice del subordinado.
                    //evalua si el subordinado ya tiene jefe (Otro directivo al mando)
                    if(subordinado.getJefe()==null){//si no tiene jefe
                            directivoEncontrado.agregarSubordinado(subordinado);//se asigna el subordinado dentro de la lista del directivo elegido
                            subordinado.setJefe(directivoEncontrado);
                            System.out.println(" Empleado "+subordinado.getNombre()+" asignado exitosamente a "+ directivoEncontrado.getNombre());
                            pausa(sc);
                    }else{//si tiene un jefe se comprueba si es el directivo elegido o es otro
                        //avisa que ya tiene un jefe
                        if(directivoEncontrado.equals(subordinado.getJefe())){
                            //muestra que el directivo escogido ya tiene este subordinado
                            System.out.println("El empleado ya ha sido asignado como subordinado a "+directivoEncontrado.getNombre());
                            pausa(sc);
                        }else{
                            //muestra que otro directivo tiene este subordinado
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
        //Se valida la empresa. Si se encuentra se muestra su lista sino, se avisa que no existe.
        System.out.print("Ingrese el ID de la empresa para ver sus empleados: ");
        String idEmpresa = sc.nextLine().trim();

        for (Empresa empresa : empresas) {
            if (idEmpresa.equals(empresa.getIdEmpresa())) {
                System.out.println("\n=============== EMPLEADOS DE " + empresa.getNombre().toUpperCase() + " ===============");
                empresa.mostrarEmpleadosEmpresa();
                pausa(sc);
                return;
            }
        }
        System.out.println("La empresa no existe en el sistema.");
        pausa(sc);
    }

    //metodo para mostrar los datos de los clientes de una empresa
    public static void consultarClientes(List<Empresa> empresas, Scanner sc){
        //Se valida y muestra igual que en consultarEmpleados
        System.out.print("Ingrese el ID de la empresa para ver sus clientes: ");
        String idEmpresa = sc.nextLine().trim();
        for (Empresa empresa : empresas) {
            if (idEmpresa.equals(empresa.getIdEmpresa())) {
                System.out.println("\n=============== CLIENTES DE " + empresa.getNombre().toUpperCase() + " ===============");
                empresa.mostrarClientesEmpresa();
                pausa(sc);
                return;
            }
        }
        System.out.println("La empresa no existe en el sistema.");
        pausa(sc);
    }

    //metodo para mostrar todos los subordinados de un directivo de una empresa
    public static void consultarSubordinados(List<Empresa> empresas, Scanner sc) {
        //validamos la existencia de la empresa
        System.out.print("Ingrese el ID de la empresa donde esta el director a buscar: ");
        String idEmpresa = sc.nextLine().trim();
        Empresa empresaElegida=null;
        for(Empresa empresa: empresas) {
            if(idEmpresa.equals(empresa.getIdEmpresa())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa no existe en el sistema");
            pausa(sc);
        }else{
            //Si la empresa existe se solicita los datos del directivo y se valida su existencia en esa empresa
            String idDirectivo;
            Directivo directivoEncontrado=null;
            System.out.print("Ingrese el ID del Directivo a evaluar: ");
            idDirectivo =sc.nextLine().trim();
            //validacion de la existencia del Directivo elegido en la empresa
            for(Empleado empleado: empresaElegida.getEmpleados()){
                if(idDirectivo.equals(empleado.getIdPersona()) && empleado instanceof Directivo) {
                    directivoEncontrado=(Directivo)empleado;
                    break;
                }
            }
            if(directivoEncontrado==null){
                System.out.println("El directivo no existe en la empresa " + idEmpresa);
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
        //validacion de la existencia de la empresa
        System.out.print("Ingrese el ID de la empresa donde esta el director a buscar: ");
        String idEmpresa = sc.nextLine().trim();
        Empresa empresaElegida=null;
        for(Empresa empresa: empresas) {
            if(idEmpresa.equals(empresa.getIdEmpresa())) {
                empresaElegida = empresa;
                break;
            }
        }
        if(empresaElegida==null) {
            System.out.println("La empresa no existe en el sistema");
            pausa(sc);
        }else{
            //Si la empresa existe se solicita los datos del directivo
            //se valida su existencia dentro del metodo
            String nombreDirectivo;
            int categoriaDirectivo;
            System.out.print("Ingrese el nombre del Directivo a evaluar: ");
            nombreDirectivo=sc.nextLine();
            do{
                System.out.print("Ingrese la categoria del Directivo a evaluar: ");
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
                        Elige una opcion:\s""");
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
