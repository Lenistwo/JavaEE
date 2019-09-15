package pl.lenistwo.web.crudGroup;

import pl.lenistwo.web.excpetions.ResourceNotFoundException;
import pl.lenistwo.web.hibernate.entities.Groups;
import pl.lenistwo.web.hibernate.entities.Specializations;
import pl.lenistwo.web.hibernate.repository.GroupRepository;
import pl.lenistwo.web.hibernate.repository.SpecializationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/createGroup")
public class CreateGroupServlet extends HttpServlet {

    private final GroupRepository groupRepository;
    private final SpecializationRepository specializationRepository;

    public CreateGroupServlet() {
        groupRepository = new GroupRepository();
        specializationRepository = new SpecializationRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Specializations> specializations = specializationRepository.getAllSpecializations();
        req.setAttribute("spec", specializations);
        String url = "/view/group/createGroup.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Byte academicalYear = Byte.parseByte(req.getParameter("academical_year"));
        Integer specializationId = Integer.parseInt(req.getParameter("specialization"));
        Groups groups = new Groups(name, academicalYear);
        Optional<Specializations> specializations = Optional.ofNullable(specializationRepository.getSingleSpecialization(specializationId));
        groups.setId_spec(specializations.orElseThrow(ResourceNotFoundException::new));
        groupRepository.saveGroup(groups);
        String url = "/listOfGroups";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
