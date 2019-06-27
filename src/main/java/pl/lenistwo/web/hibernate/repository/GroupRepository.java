package pl.lenistwo.web.hibernate.repository;

import org.hibernate.Session;
import pl.lenistwo.web.hibernate.configuration.HibernateConfig;
import pl.lenistwo.web.hibernate.entities.Groups;
import pl.lenistwo.web.hibernate.interfaces.GroupDAOInterface;

import java.util.List;
import java.util.stream.Collectors;

public class GroupRepository implements GroupDAOInterface {

    private final HibernateActions actions;

    public GroupRepository() {
        Session session = HibernateConfig.getInstanceOfSession();
        actions = new HibernateActions(session);
    }

    @Override
    public List<Groups> getAllGroups() {
        List<Object> groups = actions.getAllObjects("Groups");
        return groups.parallelStream().map(s -> (Groups) s).collect(Collectors.toList());
    }

    @Override
    public Groups getSingleGroup(int id) {
        return (Groups) actions.getSingleObject("Groups", "id_g", id);
    }

    @Override
    public void saveGroup(Groups groups) {
        actions.saveOrUpdateObject(groups);
    }

    @Override
    public void updateGroup(Groups groups) {
        actions.saveOrUpdateObject(groups);

    }

    @Override
    public void deleteGroup(int id) {
        actions.deleteObject("Groups", "id_g", id);
    }
}
