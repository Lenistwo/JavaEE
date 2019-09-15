package pl.lenistwo.web.hibernate.repository;

import org.hibernate.Session;
import pl.lenistwo.web.hibernate.configuration.HibernateConfig;
import pl.lenistwo.web.hibernate.entities.Students;
import pl.lenistwo.web.hibernate.entities.StudentsGroup;
import pl.lenistwo.web.hibernate.interfaces.StudentDAO;

import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository implements StudentDAO {

    private final Session session;
    private final HibernateActions actions;

    public StudentRepository() {
        session = HibernateConfig.getInstanceOfSession();
        actions = new HibernateActions(session);
    }

    @Override
    public List<Students> getAllStudents() {
        List<Object> groups = actions.getAllObjects("Students");
        return groups.parallelStream().map(s -> (Students) s).collect(Collectors.toList());
    }

    @Override
    public void saveStudents(Students students) {
        actions.saveOrUpdateObject(students);
    }

    @Override
    public void updateStudent(Students students) {
        actions.saveOrUpdateObject(students);
    }

    @Override
    public void deleteStudent(int id) {
        actions.deleteObject("Students", "id_s", id);
    }

    @Override
    public Students getSingleStudent(int id) {
        return (Students) actions.getSingleObject("Students", "id_s", id);
    }

    @Override
    public void saveStudentInfo(StudentsGroup info) {
        actions.saveOrUpdateObject(info);
    }

    @Override
    public StudentsGroup getStudentInfo(int id) {
        return session.createQuery("from StudentsGroup where id_s=" + id, StudentsGroup.class).uniqueResult();

    }

}
