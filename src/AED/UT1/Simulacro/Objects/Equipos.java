package AED.UT1.Simulacro.Objects;

import AED.UT1.Simulacro.Constants.EquipoFutbolCons;

import java.io.Serializable;

public class Equipos implements Serializable {

    private Integer id;
    private StringBuffer nombre;
    private StringBuffer presidente;
    private StringBuffer telefono;
    private StringBuffer localidad;

    public Equipos(Integer id, String nombre, String presidente, String telefono, String localidad) {
        this.id = id;
        this.nombre = new StringBuffer(nombre);
        this.presidente = new StringBuffer(presidente);
        this.telefono = new StringBuffer(telefono);
        this.localidad = new StringBuffer(localidad);
    }

    //Configura los StringBuffers para trabajar con RandomAccessFile
    public void configStringsForRAF() {
        this.nombre.setLength(EquipoFutbolCons.STRINGBUFFER_LENGTH);
        this.presidente.setLength(EquipoFutbolCons.STRINGBUFFER_LENGTH);
        this.telefono.setLength(EquipoFutbolCons.TELEFONO_LENGTH);
        this.localidad.setLength(EquipoFutbolCons.STRINGBUFFER_LENGTH);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StringBuffer getNombre() {
        return nombre;
    }

    public void setNombre(StringBuffer nombre) {
        this.nombre = nombre;
    }

    public StringBuffer getPresidente() {
        return presidente;
    }

    public void setPresidente(StringBuffer presidente) {
        this.presidente = presidente;
    }

    public StringBuffer getTelefono() {
        return telefono;
    }

    public void setTelefono(StringBuffer telefono) {
        this.telefono = telefono;
    }

    public StringBuffer getLocalidad() {
        return localidad;
    }

    public void setLocalidad(StringBuffer localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Equipos{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", presidente=" + presidente +
                ", telefono=" + telefono +
                ", localidad=" + localidad +
                '}';
    }
}
