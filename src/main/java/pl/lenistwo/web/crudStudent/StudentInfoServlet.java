package pl.lenistwo.web.crudStudent;

import pl.lenistwo.web.excpetions.ResourceNotFoundException;
import pl.lenistwo.web.hibernate.repository.StudentRepository;
import pl.lenistwo.web.hibernate.entities.Groups;
import pl.lenistwo.web.hibernate.entities.Students;
import pl.lenistwo.web.hibernate.entities.StudentsGroup;
import pl.lenistwo.web.hibernate.repository.GroupRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/studentInfo")
public class StudentInfoServlet extends HttpServlet {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private Optional<String> studentID;

    public StudentInfoServlet() {
        studentRepository = new StudentRepository();
        groupRepository = new GroupRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentID = Optional.ofNullable(req.getParameter("id"));

        if (!studentID.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        Optional<StudentsGroup> studentInfo = Optional.ofNullable(studentRepository.getStudentInfo(Integer.parseInt(studentID.get())));

        if (!studentInfo.isPresent()) {
            Optional<Students> optionalStudents = Optional.ofNullable(studentRepository.getSingleStudent(Integer.parseInt(studentID.get())));

            if (!optionalStudents.isPresent())
                return;

            List<Groups> groups = groupRepository.getAllGroups();

            req.setAttribute("student", optionalStudents.get());
            req.setAttribute("group", groups);
            String url = "/view/student/assignStudent.jsp";
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            return;
        }

        req.setAttribute("info", studentInfo);

        String url = "/view/student/studentInfo.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentID = Optional.ofNullable(req.getParameter("id"));
        Optional<String> groupID = Optional.ofNullable(req.getParameter("group"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));

        Optional<Students> student = Optional.ofNullable(studentRepository.getSingleStudent(Integer.parseInt(studentID.orElseThrow(ResourceNotFoundException::new))));
        if (!student.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }
        Optional<Groups> group = Optional.ofNullable(groupRepository.getSingleGroup(Integer.parseInt(groupID.orElseThrow(ResourceNotFoundException::new))));
        if (!group .isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/groupNotFound.jsp").forward(req, resp);
            return;
        }
        StudentsGroup studentsGroup = new StudentsGroup(student.get(), group.get(), date);
        studentRepository.saveStudentInfo(studentsGroup);
        getServletContext().getRequestDispatcher("/listOfStudents").forward(req, resp);
    }
}
