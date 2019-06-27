package pl.lenistwo.web.hibernate.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "Grupy")
public class Groups {

    public Groups() {
    }

    public Groups(String name, byte academical_year) {
        this.name = name;
        this.academical_year = academical_year;
    }

    public Groups(String name, Specializations id_spec, byte academical_year) {
        this.name = name;
        this.id_spec = id_spec;
        this.academical_year = academical_year;
    }

    @Id
    @Column(name = "id_g")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_g;

    @Column(name = "nazwa_g")
    private String name;

    @OneToOne
    @JoinColumn(name = "id_spec")
    @Cascade({
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.REMOVE,
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.REFRESH
    })
    private Specializations id_spec;

    @Column(name = "Rok_akad")
    private byte academical_year;

    public int getId_g() {
        return id_g;
    }

    public void setId_g(int id_g) {
        this.id_g = id_g;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specializations getId_spec() {
        return id_spec;
    }

    public void setId_spec(Specializations id_spec) {
        this.id_spec = id_spec;
    }

    public byte getAcademical_year() {
        return academical_year;
    }

    public void setAcademical_year(byte academical_year) {
        this.academical_year = academical_year;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id_g=" + id_g +
                ", name='" + name + '\'' +
                ", id_spec=" + id_spec +
                ", academical_year=" + academical_year +
                '}';
    }
}
