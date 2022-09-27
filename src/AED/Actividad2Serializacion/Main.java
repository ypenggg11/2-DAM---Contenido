package AED.Actividad2Serializacion;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

//Apartado 1
public class Main {

    private static ArrayList<String> nombre = new ArrayList<>(),dni = new ArrayList<>();
    private static ArrayList<Integer> edad=new ArrayList<>();
    private static ArrayList<Double> salario = new ArrayList<>();

    private static String[] NOMBRES,DNI;

    private static String path = "./src/AED/Actividad2Serializacion/Persona.obj";

    public static void main(String[] args) {
        initNombresDNI();

        infoEmpleados(6);

        creaEmpleados(dni.size());

        leeEmpleados();
    }

    private static void leeEmpleados() {

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);

            try {
                while (true) {
                    System.out.println(objectInputStream.readObject());
                }
            }catch (EOFException e) {}

            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void creaEmpleados(int numEmpleados) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (int i = 0;i<numEmpleados;i++) {
                objectOutputStream.writeObject(new Persona(nombre.get(i),dni.get(i),edad.get(i),salario.get(i) ));
            }

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    private static void initNombresDNI() {
        NOMBRES = new String[]{
                "Mateo",
                "Daniel",
                "Pablo",
                "Álvaro",
                "Adrián",
                "David",
                "Diego",
                "Javier"
        };

        DNI = new String[] {
                "74622651W",
                "22449058T",
                "88660453F",
                "32901964G",
                "51450994V",
                "27207248L",
                "09621301X",
                "15185955K"
        };
    }

    private static double randomNum(boolean doubleValue,int minvalue,int maxValue){
        Random random = new Random();

        if (doubleValue){
            return random.nextDouble(minvalue,maxValue);
        }else {
            return random.nextInt(minvalue,maxValue);
        }
    }

    private static void infoEmpleados(int numEmpleados) {
        for (int i = 0;i<numEmpleados;i++) {
            nombre.add(NOMBRES[(int) randomNum(false,0,8)]);
            dni.add(DNI[(int) randomNum(false,0,8)]);

            int edadAux = (int) randomNum(false,0,64);
            edad.add(edadAux);

            if (edadAux<16) {
                salario.add((double) 0);
            }else {
                salario.add(randomNum(true, 0, 4000));
            }
        }
    }
}
