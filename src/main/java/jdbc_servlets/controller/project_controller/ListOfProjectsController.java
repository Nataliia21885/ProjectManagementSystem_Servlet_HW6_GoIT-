package jdbc_servlets.controller.project_controller;

import jdbc_servlets.config.DatabaseManagerConnector;
import jdbc_servlets.config.PropertiesConfig;
import jdbc_servlets.repository.ProjectRepository;
import jdbc_servlets.service.ProjectService;
import jdbc_servlets.service.converter.ProjectConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns = "/projects/list")
public class ListOfProjectsController extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        String dbPassword = System.getenv("dbpassword");
        String dbUserName = System.getenv("dbusername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, dbUserName, dbPassword);
        ProjectRepository projectRepository = new ProjectRepository(dbManager);
        ProjectConverter projectConverter = new ProjectConverter();
        projectService = new ProjectService(projectRepository, projectConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<List<String>> projectsList = projectService.listOfProjects();
        req.setAttribute("projects", projectsList);
        req.getRequestDispatcher("/WEB-INF/jsp/projects/listOfProjects.jsp").forward(req, resp);
        if (projectsList.isEmpty()) {
            req.setAttribute("message", "You enter wrong format. Please, try again");
            req.getRequestDispatcher("//WEB-INF/jsp/projects/listOfProjects.jsp").forward(req, resp);
        }
    }
}
