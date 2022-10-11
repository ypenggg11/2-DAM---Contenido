package AED.UT1.Examen.Objects;

import AED.UT1.Examen.Utils.Utilities;

import java.io.Serializable;

public class Seguro implements Serializable {

    private Integer poliza;
    private String cliente;
    private String matricula;
    private Double cuota;

    public Seguro() {
        this.poliza = (int) Utilities.randomNum(false,1,9999);
        this.cliente = getRandomName();
        this.matricula = getRandomMatricula();
        this.cuota = Utilities.randomNum(true,400,2000);
    }

    private String getRandomName() {
        String[] NOMBRES = new String[]{
                "Mateo",
                "Daniel",
                "Pablo",
                "Álvaro",
                "Adrián",
                "David",
                "Diego",
                "Javier"
        };

        return NOMBRES[(int)Utilities.randomNum(false,0,NOMBRES.length-1)];
    }

    private String getRandomMatricula() {
        String[] MATRICULAS = new String[]{
                "GC9483",
                "EH3957",
                "LP1234",
                "TF4954",
                "LZ8473",
                "FV3958",
                "LG9584",
                "LG9583"
        };

        return MATRICULAS[(int)Utilities.randomNum(false,0,MATRICULAS.length-1)];
    }

    public Integer getPoliza() {
        return poliza;
    }

    public void setPoliza(Integer poliza) {
        this.poliza = poliza;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    @Override
    public String toString() {
        return "||==========||Seguro||==========||\n" +
                "Poliza: " + poliza +'\n'+
                "Cliente: " + cliente + '\n' +
                "Matricula: " + matricula + '\n' +
                "Cuota: " + cuota;
    }
}
