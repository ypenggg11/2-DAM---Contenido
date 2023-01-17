package AED.UT4;

public class Teams {

    private Integer id;
    private String name;
    private String category;
    private Integer group;

    private String headquarters;
    private String CEO;
    private Integer points;

    public Teams() {

    }

    public Teams(Integer id) {
        this.id = id;
    }

    public Teams(Integer id,String name, String category, Integer group, String headquarters, String CEO, Integer points) {
        this.id  = id;
        this.name = name;
        this.category = category;
        this.group = group;
        this.headquarters = headquarters;
        this.CEO = CEO;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getCEO() {
        return CEO;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "||===============||Team||===============||\n" +
                "Id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Category: " + category + '\n' +
                "Group: " + group +
                "Headquarters: " + headquarters + '\n' +
                "CEO: " + CEO + '\n' +
                "Points: " + points;
    }
}
