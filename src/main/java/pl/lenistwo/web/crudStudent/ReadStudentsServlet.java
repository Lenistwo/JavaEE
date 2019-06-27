package pl.lenistwo.web.crudStudent;

import pl.lenistwo.web.hibernate.repository.StudentRepository;
import pl.lenistwo.web.hibernate.entities.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listOfStudents")
public class ReadStudentsServlet extends HttpServlet {

    private final StudentRepository repository;

    public ReadStudentsServlet() {
        repository = new StudentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Students> studentsList = repository.getAllStudents();
        req.setAttribute("student", studentsList);
        getServletContext().getRequestDispatcher("/view/student/studentList.jsp").forward(req, resp);
    }

}
