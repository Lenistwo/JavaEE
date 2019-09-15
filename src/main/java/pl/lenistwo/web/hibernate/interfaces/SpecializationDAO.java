package pl.lenistwo.web.hibernate.interfaces;

import pl.lenistwo.web.hibernate.entities.Specializations;

import java.util.List;

public interface SpecializationDAO {

    List<Specializations> getAllSpecializations();

    Specializations getSingleSpecialization(int id);

    void saveSpecialization(Specializations specializations);

    void updateSpecialization(Specializations specializations);

    void deleteSpecialization(int id);
}
