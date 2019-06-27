package pl.lenistwo.web.crudSepcialization;

import pl.lenistwo.web.hibernate.entities.Specializations;
import pl.lenistwo.web.hibernate.repository.SpecializationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listOfSpecializations")
public class ReadSpecializationsServlet extends HttpServlet {

    private final SpecializationRepository repository;

    public ReadSpecializationsServlet() {
        repository = new SpecializationRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Specializations> list = repository.getAllSpecializations();
        req.setAttribute("spec", list);
        String url = "/view/specialization/specializationList.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
