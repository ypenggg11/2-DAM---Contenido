package PGV.UT2.Actividad3FicherosSemaforos;

import PGV.UT2.Actividad3FicherosSemaforos.producer_consumer_model.FileBuffer;
import PGV.UT2.Actividad3FicherosSemaforos.producer_consumer_model.Reader;
import PGV.UT2.Actividad3FicherosSemaforos.producer_consumer_model.Writer;

public class Main {

    private final static String FILE_PATH = "./src/PGV/UT2/Actividad3FicherosSemaforos/text_file.txt";
    private final static int WRITER_AMOUNT = 5;
    private final static int READER_AMOUNT = 10;
    public static void main(String[] args) {
        FileBuffer fileBuffer = new FileBuffer(FILE_PATH);

        for (int i = 0;i<READER_AMOUNT;i++) {
            Reader reader = new Reader(fileBuffer,"Reader "+i);
            reader.getThread().start();
        }

        for (int i = 0;i<WRITER_AMOUNT;i++) {
            Writer writer = new Writer(fileBuffer,"Writer "+i);
            writer.getThread().start();
        }
    }
}
