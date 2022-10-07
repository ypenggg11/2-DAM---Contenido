package AED.PlantillasUT1.TesteoFuncionamiento;

import AED.PlantillasUT1.RandomAccessIO;


public class MainPruebas {

    private static String path = "./src/AED/PlantillasUT1/TesteoFuncionamiento/";
    public static void main(String[] args) {
        RandomAccessIO randomAccessIO = new RandomAccessIO(path+"prueba.txt");

        randomAccessIO.initWriter();
        randomAccessIO.writeFile(RandomAccessIO.BYTES.CHAR,'H');
        randomAccessIO.writeFile(RandomAccessIO.BYTES.DOUBLE,7.66);
        randomAccessIO.writeFile(RandomAccessIO.BYTES.INT,2);
        randomAccessIO.writeFile(RandomAccessIO.BYTES.INT,4);
        randomAccessIO.closeWriter();

        for (String text: randomAccessIO.readAllFile(new RandomAccessIO.BYTES[]{RandomAccessIO.BYTES.CHAR, RandomAccessIO.BYTES.DOUBLE, RandomAccessIO.BYTES.INT, RandomAccessIO.BYTES.INT})){
            System.out.println(text);
        }

        randomAccessIO.closeWriter();
    }
}
