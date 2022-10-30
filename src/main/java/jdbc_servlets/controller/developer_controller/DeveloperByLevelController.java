package jdbc_servlets.controller.developer_controller;

import jdbc_servlets.config.DatabaseManagerConnector;
import jdbc_servlets.config.PropertiesConfig;
import jdbc_servlets.model.dto.DeveloperDto;
import jdbc_servlets.repository.DeveloperRepository;
import jdbc_servlets.repository.SkillRepository;
import jdbc_servlets.service.DeveloperService;
import jdbc_servlets.service.SkillService;
import jdbc_servlets.service.converter.DeveloperConverter;
import jdbc_servlets.service.converter.SkillConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns = "/developers/lev")
public class DeveloperByLevelController extends HttpServlet {
    private DeveloperService developerService;
    private SkillService skillService;

    @Override
    public void init() throws ServletException {
        String dbPassword = System.getenv("dbpassword");
        String dbUserName = System.getenv("dbusername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, dbUserName, dbPassword);
        DeveloperRepository developerRepository = new DeveloperRepository(dbManager);
        DeveloperConverter developerConverter = new DeveloperConverter();
        developerService = new DeveloperService(developerRepository, developerConverter);
        SkillRepository skillRepository = new SkillRepository(dbManager);
        SkillConverter skillConverter = new SkillConverter();
        skillService = new SkillService(skillRepository, skillConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String level = req.getParameter("level");
        List<DeveloperDto> listDevelopersByLevel = developerService.developersByLevel(level);
        if(!listDevelopersByLevel.isEmpty()) {
            req.setAttribute("developersByLevel", listDevelopersByLevel);
            req.setAttribute("message", "Such developer found");
            req.getRequestDispatcher("/WEB-INF/jsp/developers/developerByLevel.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Such developer didn't find");
            req.getRequestDispatcher("/WEB-INF/jsp/developers/developerByLevel.jsp").forward(req, resp);
        }
    }
}
