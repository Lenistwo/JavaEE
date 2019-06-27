package pl.lenistwo.web.crudSepcialization;

import pl.lenistwo.web.hibernate.entities.Specializations;
import pl.lenistwo.web.hibernate.repository.SpecializationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createSpecialization")
public class CreateSpecializationServlet extends HttpServlet {

    private final SpecializationRepository repository;

    public CreateSpecializationServlet() {
        repository = new SpecializationRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/view/specialization/createSpecialization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Specializations specialization = new Specializations(name);
        repository.saveSpecialization(specialization);
        String url = "/listOfSpecializations";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
