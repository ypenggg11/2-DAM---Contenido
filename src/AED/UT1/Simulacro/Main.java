package AED.UT1.Simulacro;

import AED.UT1.Simulacro.Constants.EquipoFutbolCons;
import AED.UT1.Simulacro.IOManager.Buffered;
import AED.UT1.Simulacro.IOManager.ObjectIO;
import AED.UT1.Simulacro.Objects.EquipoFutbol;
import AED.UT1.Simulacro.Objects.Equipos;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static String path = "./src/AED/UT1/Simulacro/Files/";
    private static ArrayList<EquipoFutbol> equiposFutbol;
    private static Buffered buffered = new Buffered(path + "datosEquipos.txt");
    private static Scanner scanner = new Scanner(System.in);
    private static int auxiliarBytePosition;
    private static ObjectIO objectIO = new ObjectIO(path + "datosEquipos.obj");

    public static void main(String[] args) {
        initEquipos();

        writeEquipos();
        readEquipos();

        int numEquipo = 0;

        System.out.print("\nIntroduzca un número de equipo: ");
        numEquipo = (int) leeEntradaUsuario(numEquipo);

        if (buscaEquipo(numEquipo)) {

            StringBuffer telefonoNuevo;

            System.out.print("\nIntroduzca un teléfono nuevo: ");
            telefonoNuevo = new StringBuffer(scanner.next());

            if (telefonoNuevo.length() < 9) {
                telefonoNuevo = new StringBuffer("000000000");
            }

            telefonoNuevo.setLength(EquipoFutbolCons.TELEFONO_LENGTH);

            actualizaTelefono(telefonoNuevo);
        }

        writeOBJ();

        objectIO.readObject();

        closeFileIO();
    }

    private static void writeOBJ() {

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path + "datosEquipos.dat", "r");

            int bytePos = 0;
            ArrayList<Equipos> equipos = new ArrayList<>();

            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {

                int id = randomAccessFile.readInt();

                char[] stringBufferChars = new char[EquipoFutbolCons.STRINGBUFFER_LENGTH];
                char[] telefChars = new char[EquipoFutbolCons.TELEFONO_LENGTH];

                equipos.add(new Equipos(id,getReadedString(randomAccessFile,stringBufferChars),
                        getReadedString(randomAccessFile,stringBufferChars),
                        getReadedString(randomAccessFile,telefChars),
                        getReadedString(randomAccessFile,stringBufferChars)));

                bytePos += EquipoFutbolCons.TOTAL_BYTES;

                randomAccessFile.seek(bytePos);
            }

            objectIO.writeObject(equipos);

            randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void actualizaTelefono(StringBuffer telefonoNuevo) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path + "datosEquipos.dat", "rw");

            int pos = auxiliarBytePosition +
                    EquipoFutbolCons.INTEGER_BYTES + EquipoFutbolCons.STRINGBUFFER_BYTES + EquipoFutbolCons.STRINGBUFFER_BYTES;

            randomAccessFile.seek(pos);

            char[] sbChars = new char[EquipoFutbolCons.TELEFONO_LENGTH];
            System.out.println("Teléfono antiguo: ");
            printReadedString(randomAccessFile, sbChars);

            randomAccessFile.seek(pos);

            randomAccessFile.writeChars(telefonoNuevo.toString());

            randomAccessFile.seek(auxiliarBytePosition);
            char[] stringBufferChars = new char[EquipoFutbolCons.STRINGBUFFER_LENGTH];
            char[] telefChars = new char[EquipoFutbolCons.TELEFONO_LENGTH];

            System.out.println("Datos actualizados: ");
            System.out.println("Id: " + randomAccessFile.readInt());
            printReadedString(randomAccessFile, stringBufferChars);
            printReadedString(randomAccessFile, stringBufferChars);

            printReadedString(randomAccessFile, telefChars);
            printReadedString(randomAccessFile, stringBufferChars);


            randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double leeEntradaUsuario(double dato) {
        try {
            dato = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.err.println("Dato requerido -> Integer");
            System.exit(0);
        }
        return dato;
    }

    private static boolean buscaEquipo(int numEquipo) {

        boolean exists = false;

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path + "datosEquipos.dat", "r");

            int bytePos = 0;

            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {

                int id = randomAccessFile.readInt();

                char[] stringBufferChars = new char[EquipoFutbolCons.STRINGBUFFER_LENGTH];
                char[] telefChars = new char[EquipoFutbolCons.TELEFONO_LENGTH];

                if (numEquipo == id) {
                    exists = true;
                    auxiliarBytePosition = bytePos;

                    printReadedString(randomAccessFile, stringBufferChars);
                    printReadedString(randomAccessFile, stringBufferChars);

                    printReadedString(randomAccessFile, telefChars);
                    printReadedString(randomAccessFile, stringBufferChars);
                }

                bytePos += EquipoFutbolCons.TOTAL_BYTES;

                randomAccessFile.seek(bytePos);
            }

            randomAccessFile.close();
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

    private static String getReadedString(RandomAccessFile randomAccessFile, char[] charArray) throws IOException {
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = randomAccessFile.readChar();
        }
        return new String(charArray).trim();
    }

    private static void closeFileIO() {
        buffered.closeReader();
        buffered.closeWriter();
    }

    private static void readEquipos() {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(path + "datosEquipos.dat", "rw");

            String[] registros;

            for (String line : buffered.readFile()) {
                registros = line.split("##");

                writeToRAF(registros, randomAccessFile);
            }

            randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToRAF(String[] registros, RandomAccessFile randomAccessFile) {
        try {

            randomAccessFile.writeInt(Integer.parseInt(registros[0]));
            randomAccessFile.writeChars(registros[1]);
            randomAccessFile.writeChars(registros[2]);
            randomAccessFile.writeChars(registros[3]);
            randomAccessFile.writeChars(registros[4]);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeEquipos() {
        String sep = "##";

        for (EquipoFutbol equipo : equiposFutbol) {
            String line = equipo.getId().toString() + sep + equipo.getNombre().toString() + sep +
                    equipo.getPresidente().toString() + sep + equipo.getTelefono().toString() + sep +
                    equipo.getLocalidad().toString() + "\n";

            buffered.writeFile(line, false, true);
        }

        buffered.closeWriter();
    }

    private static void initEquipos() {
        equiposFutbol = new ArrayList<>();

        equiposFutbol.add(new EquipoFutbol(100, "Playa del Hombre", "Messi", "555666777", "Telde"));
        equiposFutbol.add(new EquipoFutbol(101, "Estrella Roja", "Pepito Rojo", "666888777", "Santa Brígida"));
        equiposFutbol.add(new EquipoFutbol(202, "Siete Palmas", "Manuel Feo", "123123123", "Las Palmas de GC"));

        for (EquipoFutbol equipo : equiposFutbol) {
            equipo.configStringsForRAF();
        }
    }
}
