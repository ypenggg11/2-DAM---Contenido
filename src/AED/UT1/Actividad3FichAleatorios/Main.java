package AED.UT1.Actividad3FichAleatorios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//1 byte = 8bits
//Int (32 bits) = 4 bytes (32/8)
//String de 25 de longitud -> char (16 bits) = 50 bytes (25x16/8)
//String de 9 de longitud -> char (16 bits) = 18 bytes (9x16/8)
//Double (64 bits) = 8 bytes (64/8)
//Total = 80 bytes || 640 bits cada registro
//P.ej: Si queremos acceder al cuarto registro -> tamaño registro * (posicionRegistro -1)

//Enum con los valores de los bytes de cada dato dentro de Empleado
enum Bytes {
    INT(4),
    CHAR(2),
    STRING_NOMBRE_EMPLEADO(CHAR.byteSize * Empleado.nombreLength),
    STRING_TELEFONO_EMPLEADO(CHAR.byteSize * Empleado.telefonoLength),
    DOUBLE(8),
    TOTAL(INT.byteSize + STRING_NOMBRE_EMPLEADO.byteSize + STRING_TELEFONO_EMPLEADO.byteSize + DOUBLE.byteSize);

    int byteSize;

    //Constructor del Enum
    Bytes(int byteSize) {
        this.byteSize = byteSize;
    }
}

public class Main {
    private static String path = "./src/AED/Actividad3FichAleatorios/Empleados.dat";
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    //Se usará para guardar la posición del byte cuando encuentre un empleado con el id correspondiente
    private static int auxiliarBytePosition;
    private static Scanner scanner = new Scanner(System.in);

    private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public static void main(String[] args) {

        //Crea los objetos de clase Empleado y los añade al ArrayList
        initListaEmpleados();

        //Escribe los empleados de la lista, en el fichero Empleados.dat (RandomAccessFile)
        writeEmpleados();

        int numEpleado = 0;

        System.out.print("\nIntroduzca un número de empleado: ");
        numEpleado = (int) leeEntradaUsuario(numEpleado);

        //buscaEmpleado() devuelve un boolean (true en caso de que exista)
        //además, imprime ese mismo empleado con todos sus datos por consola
        if (buscaEmpleado(numEpleado)) {

            double importeExtra = 0.0;

            System.out.print("\nIntroduzca un importe extra: ");
            importeExtra = leeEntradaUsuario(importeExtra);

            //Actualiza (sobreescribe) el suelo antiguo + el extra añadido
            actualizaSueldo(importeExtra);

            //De nuevo, imprime el documento Empleados.dat por consola
            buscaEmpleado(numEpleado);
        }
    }

    //Lee la entrada del usuario con la clase Scanner
    private static double leeEntradaUsuario(double dato) {
        try {
            dato = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.err.println("Dato requerido -> Integer");
            System.exit(0);
        }
        return dato;
    }

    private static void actualizaSueldo(double importeExtra) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");

            //Suma la posición donde coincide el id (obtenido anteriormente), con el tamaño de cada atributo,
            //para que nos posicionemos directamente, en el atributo: salario
            int pos = auxiliarBytePosition +
                    Bytes.INT.byteSize + Bytes.STRING_NOMBRE_EMPLEADO.byteSize +
                    Bytes.STRING_TELEFONO_EMPLEADO.byteSize;

            randomAccessFile.seek(pos);

            double salarioAnterior = randomAccessFile.readDouble();

            System.out.println("Salario anterior : " + decimalFormat.format(salarioAnterior) + "€");
            System.out.println("");
            System.out.println("Datos actualizados: ");

            //Refrescamos la posicion, debido a la lectura anterior, ya que esta mueve la posicion
            randomAccessFile.seek(pos);

            //Sobreescribe el dato
            randomAccessFile.writeDouble(salarioAnterior + importeExtra);

            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean buscaEmpleado(int numEmpleado) {

        boolean exists = false;

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
            //Posición inicial en el fichero (por defecto ya empieza en 0)
            //Posicón 0, podría corresponder al registro 1
            int bytePos = 0;

            //.getFilePointer() -> posicion actual en el fichero
            //.length() -> tamaño maximo del fichero (posicion final)
            //Se ejecutará siempre y cuando la posicion actual sea menor o igual al posicion final
            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {

                //Lectura de datos, como empieza en 0, leerá el num del empleado
                int id = randomAccessFile.readInt();

                //Al almacenar anteriormente en chars el nombre y telefono, habrá que recuperarlos
                //de la misma manera
                char[] nombreChars = new char[Empleado.nombreLength];
                char[] telefChars = new char[Empleado.telefonoLength];

                //En el caso de que ambos id del empleado coincidan...
                if (numEmpleado == id) {
                    exists = true;
                    auxiliarBytePosition = bytePos;

                    //Imprime por pantalla el String leido (bucle que recorre una secuencia de chars)
                    printReadedString(randomAccessFile, nombreChars);

                    printReadedString(randomAccessFile, telefChars);

                    //Una vez dentro de un registro, podemos leer los datos de manera secuencial como con
                    //la clase DataInputStream (pero podemos indicar donde empezar con la posicion en bytes)
                    System.out.println(decimalFormat.format(randomAccessFile.readDouble()) + "€");
                }

                //Pasa al siguiente registro, ya que incrementa en el total de bytes de los atributos
                bytePos += Bytes.TOTAL.byteSize;

                //.seek(pos) -> se posiciona en el byte que indiquemos
                randomAccessFile.seek(bytePos);
            }

            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!exists) {
            System.out.println("No existe un empleado asociado a esa id");
            return false;
        } else {
            return true;
        }
    }

    private static void printReadedString(RandomAccessFile randomAccessFile, char[] charArray) throws IOException {
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = randomAccessFile.readChar();
        }
        System.out.println(new String(charArray).trim());
    }

    private static void writeEmpleados() {
        try {
            //rw -> read and write
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");

            for (Empleado empleado : listaEmpleados) {
                //Al escribir sin sobreescribir, no es necesario conocer la posición del byte
                randomAccessFile.writeInt(empleado.getNumEmpleado());
                randomAccessFile.writeChars(empleado.getNombre().toString());
                randomAccessFile.writeChars(empleado.getTelefono().toString());
                randomAccessFile.writeDouble(empleado.getSalario());

                System.out.println("||----------||EMPLEADO CREADO||----------||");
                System.out.print(empleado.toString());
            }

            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initListaEmpleados() {
        for (int i = 0; i < 6; i++) {
            listaEmpleados.add(new Empleado());
        }
    }
}
