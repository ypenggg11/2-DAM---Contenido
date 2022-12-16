package entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS", schema = "AED")
public class DepartamentosEntity {
    @Id
    @Column(name = "ID_DPT")
    private BigInteger idDpt;
    @Basic
    @Column(name = "NOMBRE_DPT")
    private String nombreDpt;
    @Basic
    @Column(name = "LOCALIDAD_DPT")
    private String localidadDpt;

    @OneToMany(mappedBy = "dptEmp",orphanRemoval = true)
    private List<EmpleadosEntity> empleados = new ArrayList<>();

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

    public List<EmpleadosEntity> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadosEntity> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "DepartamentosEntity{" +
                "idDpt=" + idDpt +
                ", nombreDpt='" + nombreDpt + '\'' +
                ", localidadDpt='" + localidadDpt + '\'' +
                '}';
    }
}
