package pl.lenistwo.web.crudGroup;

import pl.lenistwo.web.hibernate.repository.GroupRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {

    private final GroupRepository repository;

    public DeleteGroupServlet() {
        repository = new GroupRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> groupID = Optional.ofNullable(req.getParameter("id"));

        if (!groupID.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/groupNotFound.jsp").forward(req, resp);
            return;
        }

        repository.deleteGroup(Integer.valueOf(groupID.get()));
        String url = "/listOfGroups";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
