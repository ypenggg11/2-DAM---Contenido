package entity;

import jakarta.persistence.*;

import java.sql.Date;

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

        EmpleadosEntity empleados = (EmpleadosEntity) o;

        if (idEmp != empleados.idEmp) return false;
        if (Double.compare(empleados.salario, salario) != 0) return false;
        if (Double.compare(empleados.comision, comision) != 0) return false;
        if (idDptEmp != empleados.idDptEmp) return false;
        if (nombreEmp != null ? !nombreEmp.equals(empleados.nombreEmp) : empleados.nombreEmp != null) return false;
        if (cargoEmp != null ? !cargoEmp.equals(empleados.cargoEmp) : empleados.cargoEmp != null) return false;
        if (fechaIngreso != null ? !fechaIngreso.equals(empleados.fechaIngreso) : empleados.fechaIngreso != null)
            return false;

        return true;
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
