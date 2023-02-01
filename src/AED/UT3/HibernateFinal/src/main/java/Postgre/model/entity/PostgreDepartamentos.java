package Postgre.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "departamentos", catalog = "Hibernate")
public class PostgreDepartamentos {

    @Id
    @Column(name = "id_dpt")
    private int idDpt;
    @Basic
    @Column(name = "nombre_dpt")
    private String nombreDpt;
    @Basic
    @Column(name = "localidad_dpt")
    private String localidadDPt;

    public int getIdDpt() {
        return idDpt;
    }

    public void setIdDpt(int idDpt) {
        this.idDpt = idDpt;
    }

    public String getNombreDpt() {
        return nombreDpt;
    }

    public void setNombreDpt(String nombreDpt) {
        this.nombreDpt = nombreDpt;
    }

    public String getLocalidadDPt() {
        return localidadDPt;
    }

    public void setLocalidadDPt(String localidadDPt) {
        this.localidadDPt = localidadDPt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostgreDepartamentos that = (PostgreDepartamentos) o;

        if (idDpt != that.idDpt) return false;
        if (nombreDpt != null ? !nombreDpt.equals(that.nombreDpt) : that.nombreDpt != null) return false;
        if (localidadDPt != null ? !localidadDPt.equals(that.localidadDPt) : that.localidadDPt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDpt;
        result = 31 * result + (nombreDpt != null ? nombreDpt.hashCode() : 0);
        result = 31 * result + (localidadDPt != null ? localidadDPt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostgreDepartamentos{" +
                "idDpt=" + idDpt +
                ", nombreDpt='" + nombreDpt.trim() + '\'' +
                ", localidadDPt='" + localidadDPt.trim() + '\'' +
                '}';
    }


}
