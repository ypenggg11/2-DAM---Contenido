package AED.UT4.Examen;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Examenut4 {
    public static void main(String[] args) {
        //Abre base de datos
        ObjectContainer bbdd= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "src/AED/UT4/Examen/alumnos_db.yap");

        //Insertar alumnos
        bbdd.store(new Alumnos("42300200P", "Shakira", "999A", "600010203"));
        bbdd.store(new Alumnos("45100200P", "Piqué", "666Z", "928010203"));
        bbdd.store(new Alumnos("55300200H", "Bob Esponja", "555L", "922010203"));
        bbdd.store(new Alumnos("45300200T", "Calamardo", "112A", "606010203"));
        bbdd.store(new Alumnos("54900200V", "Patricio", "805P", "928010203"));

        //Ver todos los alumnos
        ObjectSet<Alumnos> objectSet = bbdd.queryByExample(new Alumnos());
        while (objectSet.hasNext()) {
            System.out.println(objectSet.next().toString());
        }

        bbdd.close();
    }
}
