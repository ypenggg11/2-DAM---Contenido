package AED.UT1.Simulacro.Constants;

public class EquipoFutbolCons {

    //StringBuffer lengths.
    public static final int STRINGBUFFER_LENGTH = 40;
    public static final int TELEFONO_LENGTH = 9;

    //Constantes de bytes para la clase RandomAccessFile
    private static final int BYTE = 8; //8 bits
    private static final int CHAR_BYTES = 16/BYTE; //2
    public static final int INTEGER_BYTES = 32/BYTE;
    public static final int STRINGBUFFER_BYTES = STRINGBUFFER_LENGTH*CHAR_BYTES;
    public static final int TELEFONO_BYTES = TELEFONO_LENGTH*CHAR_BYTES;
    public static final int TOTAL_BYTES =INTEGER_BYTES+ STRINGBUFFER_BYTES +STRINGBUFFER_BYTES + TELEFONO_BYTES+STRINGBUFFER_BYTES;

    private EquipoFutbolCons(){}
}
