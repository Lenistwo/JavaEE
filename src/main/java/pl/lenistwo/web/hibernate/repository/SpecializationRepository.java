package pl.lenistwo.web.hibernate.repository;

import org.hibernate.Session;
import pl.lenistwo.web.hibernate.configuration.HibernateConfig;
import pl.lenistwo.web.hibernate.entities.Specializations;
import pl.lenistwo.web.hibernate.interfaces.SpecializationDAOInterface;

import java.util.List;
import java.util.stream.Collectors;

public class SpecializationRepository implements SpecializationDAOInterface {

    private final HibernateActions actions;

    public SpecializationRepository() {
        Session session = HibernateConfig.getInstanceOfSession();
        actions = new HibernateActions(session);
    }

    @Override
    public List<Specializations> getAllSpecializations() {
        List<Object> groups = actions.getAllObjects("Specializations");
        return groups.parallelStream().map(s -> (Specializations) s).collect(Collectors.toList());
    }

    @Override
    public Specializations getSingleSpecialization(int id) {
        return (Specializations) actions.getSingleObject("Specializations", "id_spec", id);
    }

    @Override
    public void saveSpecialization(Specializations specializations) {
        actions.saveOrUpdateObject(specializations);
    }

    @Override
    public void updateSpecialization(Specializations specializations) {
        actions.saveOrUpdateObject(specializations);
    }

    @Override
    public void deleteSpecialization(int id) {
        actions.deleteObject("Specializations", "id_spec", id);
    }
}
