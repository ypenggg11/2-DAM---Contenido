package AED.Actividad3FichAleatorios;

import java.text.DecimalFormat;
import java.util.Random;

public class Empleado {

    private Integer numEmpleado;
    private StringBuffer nombre;

    //Tamaño máximo de un nombre
    public static final int nombreLength = 25;
    private StringBuffer telefono;

    //Tamaño máximo de un número de un teléfono
    public static final int telefonoLength = 9;
    private Double salario;
    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public Empleado() {
        
        this.numEmpleado = (int)randomNum(false,1,500);
        
        this.nombre = new StringBuffer(getRandomName());

        //Hay q usar StringBuffer y asignarle una dimensión máxima, para saber más adelante
        //el tamaño de bytes que ocupará
        this.nombre.setLength(nombreLength);
        
        this.telefono = new StringBuffer(getRandomPhoneNumber());
        this.telefono.setLength(telefonoLength);
        
        this.salario = randomNum(true,400,4000);
    }

    //Devuelve un nombre aleatorio
    private String getRandomName() {
        String[] NUM_TELEFONOS = new String[]{
                "Mateo",
                "Daniel",
                "Pablo",
                "Álvaro",
                "Adrián",
                "David",
                "Diego",
                "Javier"
        };
        
        return NUM_TELEFONOS[(int)randomNum(false,0,NUM_TELEFONOS.length-1)];
    }

    //Devuelve un número de telefono aleatorio
    private String getRandomPhoneNumber() {
        String[] NUM_TELEFONOS = new String[]{
                "697968295",
                "605733975",
                "710075050",
                "693123705",
                "767184706",
                "782119296",
                "646363070",
                "638936302",
                "792539095",
                "784722667",
                "717859816",
                "697110488",
                "611912100",
                "639260116",
                "683222448",
                "741464179",
                "616311269",
                "774800409",
                "774403250",
                "611492852"

        };

        return NUM_TELEFONOS[(int)randomNum(false,0,NUM_TELEFONOS.length-1)];
    }

    public Integer getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(Integer numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public StringBuffer getNombre() {
        return nombre;
    }

    public void setNombre(StringBuffer nombre) {
        this.nombre = nombre;
    }

    public StringBuffer getTelefono() {
        return telefono;
    }

    public void setTelefono(StringBuffer telefono) {
        this.telefono = telefono;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    //Devuelve un número aleatorio, entre los min y max pasados por parámetro
    private double randomNum(boolean doubleValue,int minvalue,int maxValue){
        Random random = new Random();

        if (doubleValue){
            return random.nextDouble(minvalue,maxValue);
        }else {
            return random.nextInt(minvalue,maxValue);
        }
    }

    @Override
    public String toString() {
        return "Nº empleado: " + this.numEmpleado + '\n' +
                "Nombre: " + this.nombre + '\n' +
                "Telefono: " + this.telefono +'\n'+
                "Salario: " + decimalFormat.format(salario) +
                "€\n";
    }
}
