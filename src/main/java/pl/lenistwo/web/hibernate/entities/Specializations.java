package pl.lenistwo.web.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Specjalizacje")
public class Specializations {

    @Id
    @Column(name = "id_spec")
    private int id_spec;

    @Column(name = "Nazwa_s")
    private String name;

    public Specializations() {
    }

    public Specializations(String name) {
        this.name = name;
    }

    public int getId_spec() {
        return id_spec;
    }

    public void setId_spec(int id_spec) {
        this.id_spec = id_spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specializations{" +
                "id_spec=" + id_spec +
                ", name='" + name + '\'' +
                '}';
    }
}

