package jdbc_servlets.controller.developer_controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;

@WebServlet(urlPatterns = "/developers/create")
public class CreateDeveloperFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/developers/createDeveloperForm.jsp").forward(req, resp);
    }
}
