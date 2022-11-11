package AED.UT2.MongoDB.Tests;

import org.bson.types.ObjectId;

public class Cliente {

    ObjectId _id;
    String Name;
    Integer Age;

    public Cliente(ObjectId id, String Name, Integer Age) {

        this._id =id;
        this.Name =Name;
        this.Age =Age;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "_id=" + _id +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }
}
