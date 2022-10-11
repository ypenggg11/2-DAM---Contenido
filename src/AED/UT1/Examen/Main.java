package AED.UT1.Examen;

import AED.UT1.Examen.FileIOManagement.ObjectIO;
import AED.UT1.Examen.FileIOManagement.RandomAccessIO;
import AED.UT1.Examen.Objects.Seguro;
import AED.UT1.Examen.Utils.Utilities;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //Path desde mi carpeta IdeaProjects2 (cambiar según la ubicación del archivo)
    private final static String path = "./src/AED/UT1/Examen/Files/";
    //Clase personalizado
    private final static ObjectIO objectIO = new ObjectIO(path + "Seguros.obj");
    //Clase personalizado
    private final static RandomAccessIO randomAccessIO = new RandomAccessIO(path + "SegurosDirecto.dat");
    private final static Scanner userInput = new Scanner(System.in);
    //Array de tipos y orden de bytes en cada registro, utilizado en los ficheros de acceso aleatorio.
    //(BYTES es un enum de la RandomAccessIO, con los valores por defecto de bytes de cada valor)
    private final static RandomAccessIO.BYTES[] segurosBytes = new RandomAccessIO.BYTES[]{
            RandomAccessIO.BYTES.INT,
            RandomAccessIO.BYTES.STRINGBUFFER,
            RandomAccessIO.BYTES.STRINGBUFFER,
            RandomAccessIO.BYTES.DOUBLE
    };

    public static void main(String[] args) {

        //Apartado 1 y 2
        //
        //Lee el número de seguros que se quieren crear
        int numSeguros = leeNumSeguros();
        //Escribe en el fichero el número de seguros indicado por parámetro
        //(Los valores del atributo de asignan automaticamente en la misma clase)
        segurosIO(numSeguros);

        //Apartado 3
        //
        //Lee los objetos del fichero y los escribe con RandomAccessFile en otro fichero.
        escribeFicheroAlea();

        //Apartado 4
        int poliza = leePoliza(numSeguros);
        //Busca en el fichero aleatorio, el lugar donde concuerda el id.
        int[] seguroPos = actualizaCuota(poliza);
        //Lee únicamente el contenido de ese registro actualizado.
        muestraActualizado(seguroPos);

        //Apartado 5
        leeTodoAlea();
    }

    private static void muestraActualizado(int[] seguroPos) {
        System.out.println("\n||========||Actualizado||========||");
        for (String dato: randomAccessIO.readByPosition(seguroPos[0], segurosBytes)) {
            System.out.println(dato);
        }
    }

    private static void leeTodoAlea() {
        System.out.println();
        int aux = 4;
        for (String dato: randomAccessIO.readAllFile(segurosBytes)) {
            if (aux == 4) {
                System.out.println("||==========||Seguro||==========||");
                aux = 0;
            }

            System.out.println(dato);
            aux++;
        }
    }

    @NotNull
    private static int[] actualizaCuota(int poliza) {
        int[] seguroPos = randomAccessIO.searchPositionById(String.valueOf(poliza), segurosBytes);

        //Lee el registro entero donde entró la concordancia y devuelve la lista de sus atributos.
        //seguroPos[0] (posición inicial del registro entero)
        ArrayList<String> datosRegistro = randomAccessIO.readByPosition(seguroPos[0], segurosBytes);

        randomAccessIO.initWriter();
        randomAccessIO.writeFileByPos(segurosBytes[3], calculaDescuento(datosRegistro), getCuotaPos(seguroPos));
        randomAccessIO.closeWriter();

        return seguroPos;
    }

    private static int getCuotaPos(int[] seguroPos) {
        //Establecemos la posición en el que se encuentra la cuota de ese registro.
        //seguroPos[1] (posición exacta de donde encontró la concordancia, en este caso, es igual al [0])
        return seguroPos[1] + segurosBytes[0].byteSize + segurosBytes[1].byteSize + segurosBytes[2].byteSize;
    }

    private static double calculaDescuento(ArrayList<String> datosRegistro) {
        int porcentajeDescuento = 15, posCuota = 3;

        return Double.parseDouble(datosRegistro.get(posCuota)) -
                Double.parseDouble(datosRegistro.get(posCuota)) *
                        porcentajeDescuento/100;
    }

    private static int leePoliza(int numSeguros) {
        boolean rep2 = true;
        System.out.print("\nIntroduzca la poliza que quieras para actualizar su cuota (0 para salir): ");

        int poliza = 0;

        while (rep2) {
            try {
                poliza = userInput.nextInt();

                if (poliza == 0) {
                    System.err.println("Saliendo del programa...");
                    break;
                }

                if (!Utilities.NegativeNumberCheck(String.valueOf(numSeguros))) {
                    System.err.println("Introduzca un numero de poliza valido.");
                } else {
                    rep2 = false;
                }
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un numero de poliza valido.");
                userInput.next();
            }
        }
        return poliza;
    }

    private static void escribeFicheroAlea() {
        //Devuelve lo leido en una lista de objetos
        ArrayList<Seguro> seguros = objectIO.readObjectToList();

        //Inicia la escritura
        randomAccessIO.initWriter();

        //Escribe registro por registro
        for (Seguro seguro : seguros) {
            randomAccessIO.writeFile(segurosBytes[0], seguro.getPoliza());
            randomAccessIO.writeFile(segurosBytes[1], seguro.getCliente());
            randomAccessIO.writeFile(segurosBytes[2], seguro.getMatricula());
            randomAccessIO.writeFile(segurosBytes[3], seguro.getCuota());
        }
        //Cierra la escritura
        randomAccessIO.closeWriter();
    }

    private static void segurosIO(int numSeguros) {
        ArrayList<Seguro> segurosList = new ArrayList<>();

        for (int i = 0; i < numSeguros; i++) {
            segurosList.add(new Seguro());
        }

        //Escribe la lista de objetos en el fichero
        objectIO.writeObject(segurosList);
        //Apartado 2
        //
        //Lee y muestra por consola el .toString() de cada objeto
        objectIO.readObject();
    }

    private static int leeNumSeguros() {
        System.out.print("Introduzca el numero de seguros que quiera crear: ");

        int numSeguros = 0;
        boolean rep = true;

        while (rep) {
            try {
                numSeguros = userInput.nextInt();

                if (!Utilities.NegativeNumberCheck(String.valueOf(numSeguros))) {
                    System.err.println("Introduzca un numero valido.");
                } else {
                    rep = false;
                }
            } catch (InputMismatchException e) {
                System.err.println("Introduzca un numero valido.");
                userInput.next();
            }
        }

        return numSeguros;
    }
}
