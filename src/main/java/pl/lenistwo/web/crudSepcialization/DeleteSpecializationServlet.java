package pl.lenistwo.web.crudSepcialization;

import pl.lenistwo.web.hibernate.repository.SpecializationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSpecialization")
public class DeleteSpecializationServlet extends HttpServlet {

    private final SpecializationRepository repository;

    public DeleteSpecializationServlet() {
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

        repository.deleteSpecialization(Integer.valueOf(specializationID));

        String url = "/view/specialization/specializationList.jsp";

        req.getRequestDispatcher(url).forward(req, resp);
    }
}
