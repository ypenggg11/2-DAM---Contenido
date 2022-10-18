import java.util.Scanner;

public class Main {
    private final static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        //Lee la línea (el padre usará OutputStream) para la entrada de datos.
        String conectionLine = SCANNER.nextLine();

        //Separá donde encuentre un ' ' (espacio), que separa la dirección física del nombre de transporte.
        String[] splitedLine = conectionLine.split(" ");

        //Obtenemos la dirección física, que será el primero de todos.
        String physicalMacDir = splitedLine[0];

        //Lo mostramos, que será capturado por el InputStream del padre.
        System.out.println(physicalMacDir);
    }
}