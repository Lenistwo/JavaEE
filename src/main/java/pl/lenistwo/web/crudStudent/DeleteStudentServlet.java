package pl.lenistwo.web.crudStudent;

import pl.lenistwo.web.hibernate.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    private final StudentRepository repository;

    public DeleteStudentServlet() {
        repository = new StudentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> studentID = Optional.ofNullable(req.getParameter("id"));
        if (!studentID.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }
        repository.deleteStudent(Integer.parseInt(studentID.get()));
        String url = "/listOfStudents";
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }

}
