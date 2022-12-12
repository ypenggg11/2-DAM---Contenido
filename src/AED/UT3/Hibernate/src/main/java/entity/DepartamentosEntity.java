package entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "departamentos", schema = "public", catalog = "postgres")
public class DepartamentosEntity {

    @Id
    @Column(name = "id_dpt")
    private int idDpt;
    @Basic
    @Column(name = "nombre_dpt")
    private String nombreDpt;
    @Basic
    @Column(name = "localidad_dpt")
    private String localidadDpt;
    @OneToMany(mappedBy = "departamentosByIdDptEmp")
    private Collection<EmpleadosEntity> empleadosByIdDpt;

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

        DepartamentosEntity that = (DepartamentosEntity) o;

        if (idDpt != that.idDpt) return false;
        if (!Objects.equals(nombreDpt, that.nombreDpt)) return false;
        return Objects.equals(localidadDpt, that.localidadDpt);
    }

    @Override
    public int hashCode() {
        int result = idDpt;
        result = 31 * result + (nombreDpt != null ? nombreDpt.hashCode() : 0);
        result = 31 * result + (localidadDpt != null ? localidadDpt.hashCode() : 0);
        return result;
    }

    public Collection<EmpleadosEntity> getEmpleadosByIdDpt() {
        return empleadosByIdDpt;
    }

    public void setEmpleadosByIdDpt(Collection<EmpleadosEntity> empleadosByIdDpt) {
        this.empleadosByIdDpt = empleadosByIdDpt;
    }
}
