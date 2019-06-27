package pl.lenistwo.web.crudGroup;

import pl.lenistwo.web.hibernate.repository.GroupRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {

    private final GroupRepository repository;

    public DeleteGroupServlet() {
        repository = new GroupRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupID = req.getParameter("id");

        if (groupID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/groupNotFound.jsp").forward(req, resp);
            return;
        }

        repository.deleteGroup(Integer.valueOf(groupID));
        String url = "/listOfGroups";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
