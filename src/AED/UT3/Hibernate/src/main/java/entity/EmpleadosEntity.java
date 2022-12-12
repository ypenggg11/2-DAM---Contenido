package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "empleados", schema = "public", catalog = "postgres")
public class EmpleadosEntity {
    @Id
    @Column(name = "id_emp")
    private int idEmp;
    @Basic
    @Column(name = "nombre_emp")
    private String nombreEmp;
    @Basic
    @Column(name = "cargo_emp")
    private String cargoEmp;
    @Basic
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;
    @Basic
    @Column(name = "salario")
    private double salario;
    @Basic
    @Column(name = "comision")
    private double comision;
    @Basic
    @Column(name = "id_dpt_emp",insertable=false, updatable=false)
    private int idDptEmp;
    @ManyToOne
    @JoinColumn(name = "id_dpt_emp", referencedColumnName = "id_dpt", nullable = false)
    private DepartamentosEntity departamentosByIdDptEmp;

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

    public String getCargoEmp() {
        return cargoEmp;
    }

    public void setCargoEmp(String cargoEmp) {
        this.cargoEmp = cargoEmp;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
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

        EmpleadosEntity that = (EmpleadosEntity) o;

        if (idEmp != that.idEmp) return false;
        if (Double.compare(that.salario, salario) != 0) return false;
        if (Double.compare(that.comision, comision) != 0) return false;
        if (idDptEmp != that.idDptEmp) return false;
        if (!Objects.equals(nombreEmp, that.nombreEmp)) return false;
        if (!Objects.equals(cargoEmp, that.cargoEmp)) return false;
        return Objects.equals(fechaIngreso, that.fechaIngreso);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idEmp;
        result = 31 * result + (nombreEmp != null ? nombreEmp.hashCode() : 0);
        result = 31 * result + (cargoEmp != null ? cargoEmp.hashCode() : 0);
        result = 31 * result + (fechaIngreso != null ? fechaIngreso.hashCode() : 0);
        temp = Double.doubleToLongBits(salario);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(comision);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + idDptEmp;
        return result;
    }

    public DepartamentosEntity getDepartamentosByIdDptEmp() {
        return departamentosByIdDptEmp;
    }

    public void setDepartamentosByIdDptEmp(DepartamentosEntity departamentosByIdDptEmp) {
        this.departamentosByIdDptEmp = departamentosByIdDptEmp;
    }
}
