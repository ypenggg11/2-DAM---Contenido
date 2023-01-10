package model.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Empleados {
    @Id
    @Column(name = "ID_EMP")
    private BigInteger idEmp;
    @Basic
    @Column(name = "NOMBRE_EMP")
    private String nombreEmp;
    @Basic
    @Column(name = "SALARIO_EMP")
    private double salarioEmp;
    @Basic
    @Column(name = "ID_DPT_EMP")
    private BigInteger idDptEmp;
    @ManyToOne
    @JoinColumn(name = "ID_DPT_EMP", referencedColumnName = "ID_DPT", nullable = false,insertable=false, updatable=false)
    private Departamentos departamentosByIdDptEmp;

    public BigInteger getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(BigInteger idEmp) {
        this.idEmp = idEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public double getSalarioEmp() {
        return salarioEmp;
    }

    public void setSalarioEmp(double salarioEmp) {
        this.salarioEmp = salarioEmp;
    }

    public BigInteger getIdDptEmp() {
        return idDptEmp;
    }

    public void setIdDptEmp(BigInteger idDptEmp) {
        this.idDptEmp = idDptEmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleados empleados = (Empleados) o;

        if (Double.compare(empleados.salarioEmp, salarioEmp) != 0) return false;
        if (idEmp != null ? !idEmp.equals(empleados.idEmp) : empleados.idEmp != null) return false;
        if (nombreEmp != null ? !nombreEmp.equals(empleados.nombreEmp) : empleados.nombreEmp != null) return false;
        if (idDptEmp != null ? !idDptEmp.equals(empleados.idDptEmp) : empleados.idDptEmp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idEmp != null ? idEmp.hashCode() : 0;
        result = 31 * result + (nombreEmp != null ? nombreEmp.hashCode() : 0);
        temp = Double.doubleToLongBits(salarioEmp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (idDptEmp != null ? idDptEmp.hashCode() : 0);
        return result;
    }

    public Departamentos getDepartamentosByIdDptEmp() {
        return departamentosByIdDptEmp;
    }

    public void setDepartamentosByIdDptEmp(Departamentos departamentosByIdDptEmp) {
        this.departamentosByIdDptEmp = departamentosByIdDptEmp;
    }
}
