import model.manager.JPA_Manager;

public class Main {
    private static JPA_Manager jpaManager;
    public static void main(String[] args) {
        jpaManager = new JPA_Manager("examen");

        /* Actions */


        jpaManager.close();
    }
}
