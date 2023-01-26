package model.entity;

import jakarta.persistence.*;

@Entity
public class Alumnos {

    @Id
    @Column(name = "NIF")
    private String nif;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "CIAL")
    private String cial;
    @Basic
    @Column(name = "TELEFONO")
    private String telefono;

    public Alumnos() {}

    public Alumnos(String nif) {
        this.nif = nif;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alumnos alumnos = (Alumnos) o;

        if (nif != null ? !nif.equals(alumnos.nif) : alumnos.nif != null) return false;
        if (nombre != null ? !nombre.equals(alumnos.nombre) : alumnos.nombre != null) return false;
        if (cial != null ? !cial.equals(alumnos.cial) : alumnos.cial != null) return false;
        if (telefono != null ? !telefono.equals(alumnos.telefono) : alumnos.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nif != null ? nif.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (cial != null ? cial.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n||===============||Alumno||===============||\n" +
                "Nif: " + nif + '\n' +
                "Nombre: " + nombre + '\n' +
                "Cial: " + cial + '\n' +
                "Telefono: " + telefono;
    }
}
