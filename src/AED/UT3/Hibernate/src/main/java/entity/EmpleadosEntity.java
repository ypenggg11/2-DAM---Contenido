package entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "EMPLEADOS", schema = "AED")
public class EmpleadosEntity {
    @Id
    @Column(name = "ID_EMP")
    private BigInteger idEmp;
    @Basic
    @Column(name = "NOMBRE_EMP")
    private String nombreEmp;
    @Basic
    @Column(name = "CARGO_EMP")
    private String cargoEmp;
    @Basic
    @Column(name = "FECHA_INGRESO")
    private Date fechaIngreso;
    @Basic
    @Column(name = "SALARIO")
    private BigInteger salario;
    @Basic
    @Column(name = "COMISION")
    private BigInteger comision;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DPT_EMP")
    private DepartamentosEntity dptEmp;

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

    public BigInteger getSalario() {
        return salario;
    }

    public void setSalario(BigInteger salario) {
        this.salario = salario;
    }

    public BigInteger getComision() {
        return comision;
    }

    public void setComision(BigInteger comision) {
        this.comision = comision;
    }

    public DepartamentosEntity getDptEmp() {
        return dptEmp;
    }

    public void setDptEmp(DepartamentosEntity dptEmp) {
        this.dptEmp = dptEmp;
    }
}
