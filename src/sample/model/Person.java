package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Arief on 8/27/2017.
 */
public class Person {
    //private SimpleIntegerProperty id = new SimpleIntegerProperty();
    //private SimpleStringProperty name = new SimpleStringProperty();

    private int id;
    private String name;
    private Boolean status;
    private Gender gender;
    private Kategori kategori;

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person() {
    }

    public Person(int id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Person(int id, String name, Boolean status, Gender gender) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.gender = gender;
    }

    public Person(int id, String name, Boolean status, Gender gender, Kategori kategori) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.gender = gender;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", gender=" + gender +
                ", kategori=" + kategori +
                '}';
    }
}
