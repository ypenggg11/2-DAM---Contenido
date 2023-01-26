package AED.UT4.Actividad1;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static ObjectContainer db;
    private static String DB_FILE = "src/AED/UT4/teams_db.yap";

    public static void main(String[] args) {
        initFile();

        insert();

        // Obtener el nombre y los puntos de los equipos de la categoría alevín.
        searchByCategory();

        // Obtener la sede y el presidente de un equipo solicitado
        searchByTeamName();

        // Visualizar todos los datos
        viewAllData();

        db.close();
    }

    private static void viewAllData() {
        ObjectSet<Teams> teams =
                db.queryByExample(new Teams());

        System.out.println("\nAll teams: ");
        while (teams.hasNext()) {
            System.out.println(teams.next().toString());
        }
    }

    private static void searchByTeamName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInsert a team name: ");
        String teamName = scanner.nextLine();

        ObjectSet<Teams> teams =
                db.queryByExample(
                        new Teams(
                                null, teamName, null, null, null, null, null
                        )
                );

        System.out.println("\nTeams with name '" + teamName + "': ");
        while (teams.hasNext()) {
            Teams t = teams.next();

            System.out.println("\n" + "Name: " + t.getName() +
                    "\n" + "Headquarters: " + t.getHeadquarters() +
                    "\nCEO: " + t.getCEO());
        }
    }

    private static void searchByCategory() {
        ObjectSet<Teams> teams =
                db.queryByExample(
                        new Teams(
                                null, null, "alevin", null, null, null, null
                        )
                );

        System.out.println("\nTeams with 'alevin' category: ");
        while (teams.hasNext()) {
            Teams t = teams.next();

            System.out.println("\n" + "Name: " + t.getName() +
                    "\n" + "Points: " + t.getPoints() +
                    "\n" + "Category: " + t.getCategory());
        }
    }


    private static void initFile() {
        File file = new File(DB_FILE);

        if (file.exists()) {
            file.delete();
        }

        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
    }

    private static void insert() {
        db.store(
                new Teams(
                        1,
                        "Playas B",
                        "femepa",
                        2,
                        "Avenida Playa del Mujer",
                        "Boss 1",
                        21
                )
        );

        db.store(
                new Teams(
                        2,
                        "Playas A",
                        "alevin",
                        3,
                        "Avenida Playa del Hombre",
                        "Boss 2",
                        18
                )
        );

        db.store(
                new Teams(
                        3,
                        "Playas C",
                        "alevin",
                        1,
                        "Avenida Avenida",
                        "Boss 3",
                        43
                )
        );
    }
}
