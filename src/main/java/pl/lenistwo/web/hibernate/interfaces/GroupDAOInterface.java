package pl.lenistwo.web.hibernate.interfaces;

import pl.lenistwo.web.hibernate.entities.Groups;

import java.util.List;

public interface GroupDAOInterface {

    List<Groups> getAllGroups();

    Groups getSingleGroup(int id);

    void saveGroup(Groups groups);

    void updateGroup(Groups groups);

    void deleteGroup(int id);
}
