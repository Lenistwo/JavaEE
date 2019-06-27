package pl.lenistwo.web.hibernate.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Student_Grupa")
public class StudentsGroup {

    @Id
    @Column(name = "id_gs")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_gs;

    @OneToOne
    @JoinColumn(name = "id_s")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Students id_s;

    @OneToOne
    @JoinColumn(name = "id_g")
    @Cascade({
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.REFRESH
    })
    private Groups id_g;

    @Column(name = "Data")
    private LocalDate date;

    public StudentsGroup() {
    }

    public StudentsGroup(Students id_s, Groups id_g, LocalDate date) {
        this.id_s = id_s;
        this.id_g = id_g;
        this.date = date;
    }

    public Integer getId_gs() {
        return id_gs;
    }

    public void setId_gs(Integer id_gs) {
        this.id_gs = id_gs;
    }

    public Students getId_s() {
        return id_s;
    }

    public void setId_s(Students id_s) {
        this.id_s = id_s;
    }

    public Groups getId_g() {
        return id_g;
    }

    public void setId_g(Groups id_g) {
        this.id_g = id_g;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "StudentsGroup{" +
                "id_gs=" + id_gs +
                ", id_s=" + id_s +
                ", id_g=" + id_g +
                ", date=" + date +
                '}';
    }
}
