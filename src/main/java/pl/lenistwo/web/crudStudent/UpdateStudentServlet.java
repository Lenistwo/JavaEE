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
import java.util.Optional;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    private final StudentRepository repository;
    private Optional<String> studentID;

    public UpdateStudentServlet() {
        repository = new StudentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentID = Optional.ofNullable(req.getParameter("id"));

        if (!studentID.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        Optional<Students> student = Optional.ofNullable(repository.getSingleStudent(Integer.parseInt(studentID.get())));

        if (!student.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("student", student);

        req.getRequestDispatcher("/view/student/updateStudent.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentID = Optional.ofNullable(req.getParameter("id"));
        if (!studentID.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        String city = req.getParameter("city");

        Optional<Students> student = Optional.ofNullable(repository.getSingleStudent(Integer.parseInt(studentID.get())));

        if (!student.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        student.get().setName(name);
        student.get().setSurname(surname);
        student.get().setDate(date);
        student.get().setCity(city);

        repository.updateStudent(student.get());
        String url = "/view/student/studentList.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
