package pl.lenistwo.web.crudStudent;

import pl.lenistwo.web.hibernate.repository.StudentRepository;
import pl.lenistwo.web.hibernate.entities.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/createStudent")
public class CreateStudentServlet extends HttpServlet {

    private final StudentRepository repository;

    public CreateStudentServlet() {
        repository = new StudentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/view/student/createStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String city = req.getParameter("city");
        Students student = new Students(name, surname, date, city);
        repository.saveStudents(student);
        String url = "/view/student/studentList.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
