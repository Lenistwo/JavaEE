package pl.lenistwo.web.crudSepcialization;

import pl.lenistwo.web.hibernate.entities.Specializations;
import pl.lenistwo.web.hibernate.repository.SpecializationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateSpecialization")
public class UpdateSpecialization extends HttpServlet {

    private final SpecializationRepository repository;

    public UpdateSpecialization() {
        repository = new SpecializationRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String specializationID = req.getParameter("id");

        if (specializationID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/specializationNotFound.jsp").forward(req, resp);
            return;
        }

        Specializations specializations = repository.getSingleSpecialization(Integer.parseInt(specializationID));
        if (specializations == null) {
            String url = "/listOfSpecializations";
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            return;
        }
        req.setAttribute("spec", specializations);

        req.getRequestDispatcher("/view/specialization/updateSpecialization.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String specializationID = req.getParameter("id");
        String name = req.getParameter("name");
        if (specializationID == null) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/specializationNotFound.jsp").forward(req, resp);
            return;
        }
        Specializations specializations = repository.getSingleSpecialization(Integer.parseInt(specializationID));
        if (specializations == null) {
            String url = "/listOfSpecializations";
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            return;
        }
        specializations.setName(name);
        repository.updateSpecialization(specializations);
        String url = "/listOfSpecializations";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
