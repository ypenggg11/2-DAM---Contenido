package AED.UT1.Actividad4XML.Objects;

import java.io.Serializable;

public class CentroEducativo implements Serializable {

    private String tipo;
    private String nombre;
    private String codigo;
    private String calle;
    private String codigoPostal;
    private String localidad;
    private String isla;

    public CentroEducativo(String tipo, String nombre, String codigo, String calle, String codigoPostal, String localidad, String isla) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.codigo = codigo;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.isla = isla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getIsla() {
        return isla;
    }

    public void setIsla(String isla) {
        this.isla = isla;
    }
}
