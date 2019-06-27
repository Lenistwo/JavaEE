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

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    private final StudentRepository repository;

    public UpdateStudentServlet() {
        repository = new StudentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentID = req.getParameter("id");

        if (studentID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        Students student = repository.getSingleStudent(Integer.parseInt(studentID));

        if (student == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("student", student);

        req.getRequestDispatcher("/view/student/updateStudent.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentID = req.getParameter("id");
        if (studentID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String city = req.getParameter("city");

        Students student = repository.getSingleStudent(Integer.parseInt(studentID));

        if (student == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        student.setName(name);
        student.setSurname(surname);
        student.setDate(date);
        student.setCity(city);

        repository.updateStudent(student);
        String url = "/view/student/studentList.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
