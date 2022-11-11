package PGV.UT2.Actividad3FicherosSemaforos;

public class Main {
    public static void main(String[] args) {
        FileBuffer fileBuffer = new FileBuffer("./src/PGV/UT2/Actividad3FicherosSemaforos/text_file.txt");

        for (int i = 0;i<10;i++) {
            Writer writer = new Writer(fileBuffer,"Writer "+i);
            writer.getThread().start();
        }

        for (int i = 0;i<10;i++) {
            Reader reader = new Reader(fileBuffer,"Reader "+i);
            reader.getThread().start();
        }
    }
}
