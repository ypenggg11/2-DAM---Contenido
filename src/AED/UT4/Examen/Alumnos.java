package AED.UT4.Examen;

public class Alumnos {

    private String nif;
    private String nombre;
    private String cial;
    private String telefono;

    public Alumnos(){}

    public Alumnos(String nif, String nombre, String cial, String telefono) {
        this.nif = nif;
        this.nombre = nombre;
        this.cial = cial;
        this.telefono = telefono;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCial() {
        return cial;
    }

    public void setCial(String cial) {
        this.cial = cial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Alumnos{" +
                "nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cial='" + cial + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
