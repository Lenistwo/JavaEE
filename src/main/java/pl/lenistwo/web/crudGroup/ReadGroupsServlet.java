package pl.lenistwo.web.crudGroup;

import pl.lenistwo.web.hibernate.entities.Groups;
import pl.lenistwo.web.hibernate.repository.GroupRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listOfGroups")
public class ReadGroupsServlet extends HttpServlet {

    private final GroupRepository repository;

    public ReadGroupsServlet() {
        repository = new GroupRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Groups> groups = repository.getAllGroups();
        req.setAttribute("group", groups);
        String url = "/view/group/groupList.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
