package PGV.UT2.Examen;

import PGV.UT2.Examen.Model.Cliente;
import PGV.UT2.Examen.Model.Comercial;
import PGV.UT2.Examen.Model.Concesionario;

public class Main {

    private static final String[] nombresComercial = new String[]{"Opel","Mercedes","Renault","Ford"};
    private static final int PRODUCERS = 4;
    private static final int CONSUMERS = 20;

    public static void main(String[] args) {
        Concesionario concesionario = new Concesionario();

        for (int i=0;i<PRODUCERS;i++) {
            Comercial comercial = new Comercial(concesionario,nombresComercial[i]);
            comercial.runThread();
        }

        for (int i=0;i<CONSUMERS;i++) {
            Cliente cliente = new Cliente(concesionario,"Cliente "+i);
            cliente.runThread();
        }

    }
}
