package Postgre.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados", catalog = "Hibernate")
public class PostgreEmpleados {
    @Id
    @Column(name = "id_emp")
    private int idEmp;
    @Basic
    @Column(name = "nombre_emp")
    private String nombreEmp;
    @Basic
    @Column(name = "salario_emp")
    private double salarioEmp;
    @Basic
    @Column(name = "id_dpt_emp")
    private int idDptEmp;
    @ManyToOne
    @JoinColumn(name = "id_dpt_emp", referencedColumnName = "id_dpt", nullable = false,insertable = false,updatable = false)
    private PostgreDepartamentos departamentosByIdDptEmp;

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

        PostgreEmpleados that = (PostgreEmpleados) o;

        if (idEmp != that.idEmp) return false;
        if (Double.compare(that.salarioEmp, salarioEmp) != 0) return false;
        if (idDptEmp != that.idDptEmp) return false;
        if (nombreEmp != null ? !nombreEmp.equals(that.nombreEmp) : that.nombreEmp != null) return false;

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

    public PostgreDepartamentos getDepartamentosByIdDptEmp() {
        return departamentosByIdDptEmp;
    }

    public void setDepartamentosByIdDptEmp(PostgreDepartamentos departamentosByIdDptEmp) {
        this.departamentosByIdDptEmp = departamentosByIdDptEmp;
    }

    @Override
    public String toString() {
        return "PostgreEmpleados{" +
                "idEmp=" + idEmp +
                ", nombreEmp='" + nombreEmp.trim() + '\'' +
                ", salarioEmp=" + salarioEmp +
                ", idDptEmp=" + idDptEmp +
                ", departamentosByIdDptEmp=" + departamentosByIdDptEmp +
                '}';
    }
}
