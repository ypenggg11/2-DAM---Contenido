package AED.PlantillasUT1.TesteoFuncionamiento;

import AED.PlantillasUT1.RandomAccessIO;

import java.util.ArrayList;


public class MainPruebas {

    private static String path = "./src/AED/PlantillasUT1/TesteoFuncionamiento/";
    //Instancia
    private static RandomAccessIO randomAccessIO = new RandomAccessIO(path+"prueba.txt");
    public static void main(String[] args) {

        //Inicio de escritura
        randomAccessIO.initWriter();

        //Escritura registro 1
        randomAccessIO.writeFile(RandomAccessIO.BYTES.CHAR,'H');
        randomAccessIO.writeFile(RandomAccessIO.BYTES.DOUBLE,7.66);
        randomAccessIO.writeFile(RandomAccessIO.BYTES.INT,4);
        randomAccessIO.writeFile(RandomAccessIO.BYTES.STRINGBUFFER,"Hola Mundo");

        //Escritura registro2
        randomAccessIO.writeFile(RandomAccessIO.BYTES.CHAR,'J');
        randomAccessIO.writeFile(RandomAccessIO.BYTES.DOUBLE,8.02);
        randomAccessIO.writeFile(RandomAccessIO.BYTES.INT,5);
        randomAccessIO.writeFile(RandomAccessIO.BYTES.STRINGBUFFER,"Hola Mundo2");

        //Cierre de escritura
        randomAccessIO.closeWriter();

        //Orden en el que fueron escritos los datos de los registros
        RandomAccessIO.BYTES[] bytes = new RandomAccessIO.BYTES[]{
                RandomAccessIO.BYTES.CHAR,
                RandomAccessIO.BYTES.DOUBLE,
                RandomAccessIO.BYTES.INT,
                RandomAccessIO.BYTES.STRINGBUFFER
        };

        //Lectura de tod0 el fichero.
        ArrayList<String> list = randomAccessIO.readAllFile(bytes);
        for (String text: list){
            System.out.println(text);
        }

        //Obtención de posiciones del registro donde el id es 5
        int[] idPos = randomAccessIO.searchPositionById(String.valueOf(5),bytes);

        //Inicio de escritura
        randomAccessIO.initWriter();

        //Escribe/Sobreescribe en la posición pasada por parámetro
        randomAccessIO.writeFileByPos(RandomAccessIO.BYTES.INT,3,idPos[1]);

        //Cierre de escritura
        randomAccessIO.closeWriter();

        //Lee el registro de una posición concreta
        ArrayList<String> listByPos = randomAccessIO.readByPosition(idPos[0],bytes);
        for (String text: listByPos){
            System.out.println(text);
        }
    }
}
