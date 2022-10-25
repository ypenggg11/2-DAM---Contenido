package PGV.UT2;

public class Hilo extends Thread{
    public Hilo(String name,int priority) {
        this.setName(name);
        this.setPriority(priority);
    }

    @Override
    public void run() {
        super.run();

        int numIt = 10;

        for (int i=1;i<=numIt;i++) {
            System.out.println(this.getName() +" con prioridad: "+this.getPriority());

            if (i == 5) {
                if (this.getPriority()==Thread.MIN_PRIORITY) {
                    this.setPriority(MAX_PRIORITY);
                }else if (this.getPriority()==Thread.MAX_PRIORITY){
                    this.setPriority(MIN_PRIORITY);
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
