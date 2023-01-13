package Oracle.model.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Departamentos {
    @Id
    @Column(name = "ID_DPT")
    private BigInteger idDpt;
    @Basic
    @Column(name = "NOMBRE_DPT")
    private String nombreDpt;
    @Basic
    @Column(name = "LOCALIDAD_DPT")
    private String localidadDpt;

    public Departamentos(){}

    public Departamentos(BigInteger idDpt, String nombreDpt, String localidadDpt) {
        this.idDpt = idDpt;
        this.nombreDpt = nombreDpt;
        this.localidadDpt = localidadDpt;
    }

    public BigInteger getIdDpt() {
        return idDpt;
    }

    public void setIdDpt(BigInteger idDpt) {
        this.idDpt = idDpt;
    }

    public String getNombreDpt() {
        return nombreDpt;
    }

    public void setNombreDpt(String nombreDpt) {
        this.nombreDpt = nombreDpt;
    }

    public String getLocalidadDpt() {
        return localidadDpt;
    }

    public void setLocalidadDpt(String localidadDpt) {
        this.localidadDpt = localidadDpt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departamentos that = (Departamentos) o;

        if (idDpt != null ? !idDpt.equals(that.idDpt) : that.idDpt != null) return false;
        if (nombreDpt != null ? !nombreDpt.equals(that.nombreDpt) : that.nombreDpt != null) return false;
        if (localidadDpt != null ? !localidadDpt.equals(that.localidadDpt) : that.localidadDpt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDpt != null ? idDpt.hashCode() : 0;
        result = 31 * result + (nombreDpt != null ? nombreDpt.hashCode() : 0);
        result = 31 * result + (localidadDpt != null ? localidadDpt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Departamentos{" +
                "idDpt=" + idDpt +
                ", nombreDpt='" + nombreDpt + '\'' +
                ", localidadDpt='" + localidadDpt + '\'' +
                '}';
    }
}
