package pl.lenistwo.web.crudSepcialization;

import pl.lenistwo.web.hibernate.entities.Specializations;
import pl.lenistwo.web.hibernate.repository.SpecializationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/updateSpecialization")
public class UpdateSpecialization extends HttpServlet {

    private final SpecializationRepository repository;
    private Optional<String> specializationID;

    public UpdateSpecialization() {
        repository = new SpecializationRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        specializationID = Optional.ofNullable(req.getParameter("id"));

        if (!specializationID.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/specializationNotFound.jsp").forward(req, resp);
            return;
        }

        Optional<Specializations> specializations = Optional.ofNullable(repository.getSingleSpecialization(Integer.parseInt(specializationID.get())));
        if (specializations.isPresent()) {
            String url = "/listOfSpecializations";
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            return;
        }
        req.setAttribute("spec", specializations);

        req.getRequestDispatcher("/view/specialization/updateSpecialization.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        specializationID = Optional.ofNullable(req.getParameter("id"));
        String name = req.getParameter("name");
        if (!specializationID.isPresent()) {
            resp.setStatus(404);
            getServletContext().getRequestDispatcher("/view/error/specializationNotFound.jsp").forward(req, resp);
            return;
        }
        Optional<Specializations> specializations = Optional.ofNullable(repository.getSingleSpecialization(Integer.parseInt(specializationID.get())));
        if (!specializations.isPresent()) {
            String url = "/listOfSpecializations";
            getServletContext().getRequestDispatcher(url).forward(req, resp);
            return;
        }
        specializations.get().setName(name);
        repository.updateSpecialization(specializations.get());
        String url = "/listOfSpecializations";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
