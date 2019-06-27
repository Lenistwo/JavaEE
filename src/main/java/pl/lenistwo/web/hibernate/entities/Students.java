package pl.lenistwo.web.hibernate.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Studenci")
public class Students {

    public Students() {
    }

    public Students(String name, String surname, LocalDate date, String city) {
        this.surname = surname;
        this.name = name;
        this.date_u = date;
        this.city = city;
    }

    @Id
    @Column(name = "Id_s")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_s;

    @Column(name = "Nazwisko")
    private String surname;

    @Column(name = "Imie")
    private String name;

    @Column(name = "Data_u")
    private LocalDate date_u;

    @Column(name = "Miasto")
    private String city;

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id) {
        this.id_s = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date_u;
    }

    public void setDate(LocalDate date) {
        this.date_u = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id_s=" + id_s +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", date_u=" + date_u +
                ", city='" + city + '\'' +
                '}';
    }
}