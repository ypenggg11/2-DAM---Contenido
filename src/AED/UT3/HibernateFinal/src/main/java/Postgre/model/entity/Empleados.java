package Postgre.model.entity;

import jakarta.persistence.*;

@Entity
public class Empleados {
    @Id
    @Column(name = "ID_EMP")
    private int idEmp;
    @Basic
    @Column(name = "NOMBRE_EMP")
    private String nombreEmp;
    @Basic
    @Column(name = "SALARIO_EMP")
    private double salarioEmp;
    @Basic
    @Column(name = "ID_DPT_EMP")
    private int idDptEmp;
    @ManyToOne
    @JoinColumn(name = "ID_DPT_EMP", referencedColumnName = "ID_DPT", nullable = false,insertable=false, updatable=false)
    private Departamentos departamentosByIdDptEmp;

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
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

    public int getIdDptEmp() {
        return idDptEmp;
    }

    public void setIdDptEmp(int idDptEmp) {
        this.idDptEmp = idDptEmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empleados empleados = (Empleados) o;

        if (idEmp != empleados.idEmp) return false;
        if (Double.compare(empleados.salarioEmp, salarioEmp) != 0) return false;
        if (idDptEmp != empleados.idDptEmp) return false;
        if (nombreEmp != null ? !nombreEmp.equals(empleados.nombreEmp) : empleados.nombreEmp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idEmp;
        result = 31 * result + (nombreEmp != null ? nombreEmp.hashCode() : 0);
        temp = Double.doubleToLongBits(salarioEmp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + idDptEmp;
        return result;
    }

    public Departamentos getDepartamentosByIdDptEmp() {
        return departamentosByIdDptEmp;
    }

    public void setDepartamentosByIdDptEmp(Departamentos departamentosByIdDptEmp) {
        this.departamentosByIdDptEmp = departamentosByIdDptEmp;
    }
}
