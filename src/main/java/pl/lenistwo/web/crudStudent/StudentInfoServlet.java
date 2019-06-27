package pl.lenistwo.web.crudStudent;

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

@WebServlet("/studentInfo")
public class StudentInfoServlet extends HttpServlet {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentInfoServlet() {
        studentRepository = new StudentRepository();
        groupRepository = new GroupRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentID = req.getParameter("id");

        if (studentID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }

        StudentsGroup info = studentRepository.getStudentInfo(Integer.parseInt(studentID));

        if (info == null) {
            Students student = studentRepository.getSingleStudent(Integer.parseInt(studentID));

            if (student == null)
                return;

            List<Groups> groups = groupRepository.getAllGroups();

            req.setAttribute("student", student);
            req.setAttribute("group", groups);
            String url = "/view/student/assignStudent.jsp";
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            return;
        }

        req.setAttribute("info", info);

        String url = "/view/student/studentInfo.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer studentID = Integer.parseInt(req.getParameter("id"));
        Integer groupID = Integer.parseInt(req.getParameter("group"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        Students student = studentRepository.getSingleStudent(studentID);
        if (student == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/studentNotFound.jsp").forward(req, resp);
            return;
        }
        Groups group = groupRepository.getSingleGroup(groupID);
        if (group == null){
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/groupNotFound.jsp").forward(req, resp);
            return;
        }
        StudentsGroup studentsGroup = new StudentsGroup(student,group,date);
        studentRepository.saveStudentInfo(studentsGroup);
        getServletContext().getRequestDispatcher("/listOfStudents").forward(req, resp);
    }
}
