package pl.lenistwo.web.crudGroup;

import pl.lenistwo.web.hibernate.entities.Groups;
import pl.lenistwo.web.hibernate.repository.GroupRepository;
import pl.lenistwo.web.hibernate.repository.SpecializationRepository;
import pl.lenistwo.web.hibernate.entities.Specializations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateGroup")
public class UpdateGroupServlet extends HttpServlet {

    private final GroupRepository groupRepository;
    private final SpecializationRepository specializationRepository;

    public UpdateGroupServlet() {
        groupRepository = new GroupRepository();
        specializationRepository = new SpecializationRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupID = req.getParameter("id");

        if (groupID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/groupNotFound.jsp").forward(req, resp);
            return;
        }
        Groups groups = groupRepository.getSingleGroup(Integer.valueOf(groupID));
        List<Specializations> specializations = specializationRepository.getAllSpecializations();
        Specializations spec = groups.getId_spec();
        specializations.remove(spec);
        req.setAttribute("current", spec);
        req.setAttribute("group", groups);
        req.setAttribute("spec", specializations);

        getServletContext().getRequestDispatcher("/view/group/updateGroup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupID = req.getParameter("id");
        String name = req.getParameter("name");
        byte academicalYear = Byte.parseByte(req.getParameter("academical_year"));
        Integer specializationId = Integer.parseInt(req.getParameter("specialization"));
        if (groupID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/groupNotFound.jsp").forward(req, resp);
            return;
        }
        Groups group = groupRepository.getSingleGroup(Integer.valueOf(groupID));

        if (group == null) {
            String url = "/listOfGroups";
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            return;
        }

        group.setName(name);
        group.setAcademical_year(academicalYear);
        Specializations spec = specializationRepository.getSingleSpecialization(specializationId);
        group.setId_spec(spec);

        groupRepository.updateGroup(group);
        String url = "/listOfGroups";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
