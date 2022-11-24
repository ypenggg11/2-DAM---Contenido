package PGV.UT2.Examen;

public class Coche {

    private String modelo;
    private int stock;

    public Coche(String modelo, int stock){
        this.modelo = modelo;
        this.stock = stock;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getStock() {
        return stock;
    }

    public void addStock(int stock) {
        this.stock += stock;
    }

    public void decrementStock() {
        this.stock--;
    }
}
