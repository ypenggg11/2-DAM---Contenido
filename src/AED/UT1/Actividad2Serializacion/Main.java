package AED.UT1.Actividad2Serializacion;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

//Apartado 1
public class Main {

    private static ArrayList<String> nombre = new ArrayList<>(),dni = new ArrayList<>();
    private static ArrayList<Integer> edad=new ArrayList<>();
    private static ArrayList<Double> salario = new ArrayList<>();

    //Nombres y DNIs por defectos
    private static String[] NOMBRES,DNI;

    private static String path = "./src/AED/Actividad2Serializacion/Persona.obj";

    public static void main(String[] args) {
        //Inicializa los nombres y DNIs por defecto
        initNombresDNI();

        //Genera datos de las personas y los almacena en su correspondiente lista (ArrayList)
        infoPersonas(6);

        //Crea las personas a partir de las listas de datos y los guarda en el fichero Persona.obj (ObjectOutputStream)
        creaPersonas(dni.size());

        //Lee las personas del fichero anteriormente guardados
        leePersonas();
    }

    private static void leePersonas() {

        FileInputStream fileInputStream;
        //Permite la lectura de objetos
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

    private static void creaPersonas(int numEmpleados) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            //Permite la escritura de objetos
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

    //Devuelve un número aleatorio, entre los min y max pasados por parámetro
    private static double randomNum(boolean doubleValue,int minvalue,int maxValue){
        Random random = new Random();

        if (doubleValue){
            return random.nextDouble(minvalue,maxValue);
        }else {
            return random.nextInt(minvalue,maxValue);
        }
    }

    private static void infoPersonas(int numEmpleados) {

        for (int i = 0;i<numEmpleados;i++) {
            nombre.add(NOMBRES[(int) randomNum(false,0,8)]);
            dni.add(DNI[(int) randomNum(false,0,8)]);

            int edadAux = (int) randomNum(false,0,64);
            edad.add(edadAux);

            //Los menores de 16, no tendrán un salario
            if (edadAux<16) {
                salario.add((double) 0);
            }else {
                salario.add(randomNum(true, 0, 4000));
            }
        }
    }
}
