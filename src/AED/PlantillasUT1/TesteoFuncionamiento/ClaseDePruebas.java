package AED.PlantillasUT1.TesteoFuncionamiento;

import java.io.Serializable;

public class ClaseDePruebas implements Serializable {

    private String att1;
    private String att2;
    private Integer att3Int;

    public ClaseDePruebas(String att1, String att2, Integer att3Int) {
        this.att1 = att1;
        this.att2 = att2;
        this.att3Int = att3Int;
    }

    public String getAtt1() {
        return att1;
    }

    public void setAtt1(String att1) {
        this.att1 = att1;
    }

    public String getAtt2() {
        return att2;
    }

    public void setAtt2(String att2) {
        this.att2 = att2;
    }

    public Integer getAtt3Int() {
        return att3Int;
    }

    public void setAtt3Int(Integer att3Int) {
        this.att3Int = att3Int;
    }

    @Override
    public String toString() {
        return "ClaseDePruebas{" +
                "att1='" + att1 + '\'' +
                ", att2='" + att2 + '\'' +
                ", att3Int=" + att3Int +
                '}';
    }
}
