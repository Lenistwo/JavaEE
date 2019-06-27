package pl.lenistwo.web.hibernate.interfaces;

import pl.lenistwo.web.hibernate.entities.Students;
import pl.lenistwo.web.hibernate.entities.StudentsGroup;

import java.util.List;

public interface StudentDAOInterface {

    List<Students> getAllStudents();

    Students getSingleStudent(int id);

    StudentsGroup getStudentInfo(int id);

    void saveStudentInfo(StudentsGroup info);

    void saveStudents(Students students);

    void updateStudent(Students students);

    void deleteStudent(int id);

}
