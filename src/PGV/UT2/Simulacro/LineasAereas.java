package PGV.UT2.Simulacro;

public class LineasAereas implements Runnable {

    private final Thread thread;
    private final Buffer buffer;
    private String name;

    LineasAereas(String name, Buffer buffer) {
        this.name = name;
        this.thread = new Thread(this);
        this.buffer = buffer;

        this.thread.setName(name);
    }

    @Override
    public void run() {
        try {

            System.out.print("\033[0;31m"); //Color rojo
            System.out.println("Linea aerea " + this.getName() + " pide despegar");

            if (buffer.push(this.getName())) {
                System.out.println("El vuelo " + this.getName() + " se encuentra en pista, posicion " + buffer.getLineasEnCola());

                Main.aeroLineas.replace(this.name, (Main.aeroLineas.get(this.name)) + 1);

                Thread.sleep((long) (Math.random() * 10000));

                System.out.print("\033[0;32m"); //Color verde
                System.out.println(buffer.pull() + " consiguio despegar");

                Main.aeroLineas.replace(this.name, (Main.aeroLineas.get(this.name)) -1);
            } else {
                System.out.print("\033[0m"); //Color default
                System.out.println("Linea aerea " + this.getName() + " no pudo entrar a la pista");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public void startThread() {
        this.thread.start();
    }

    public void setName(String name) {
        this.name = name;
    }
}
