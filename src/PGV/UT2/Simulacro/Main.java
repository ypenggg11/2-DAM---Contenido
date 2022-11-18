package PGV.UT2.Simulacro;

import java.util.HashMap;

public class Main {

    static String[] lineasAereas = {
            "Iberia","AirBerlin","Binter","RyanAir","Vueling","SpanAir","Lufthansa","Condor","SwissAir","CanaryFly"
    };

    static HashMap<String,Integer> aeroLineas = new HashMap<>();

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        for (String aeroL: lineasAereas) {
            aeroLineas.put(aeroL,0);
        }

        while (true) {
            String aeroLinea = lineasAereas[(int) (Math.random()*10)];

            if (aeroLineas.get(aeroLinea) < 10) {
                new LineasAereas(aeroLinea, buffer).startThread();
            }else {
                System.out.println("Ya existe 10 aviones en la cola de "+aeroLinea);
            }

            try {
                Thread.sleep((long) (Math.random()*800));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
