import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "FirstServlet", urlPatterns = "/first_servlet")
public class FirstServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New GET request");
        resp.setHeader("Content-Type", "text/html; charset=utf-8");

        if(req.getParameter("param1").equals("1")){
            //logger.info("User agent: {}", req.getHeader("User-agent"));*/
            //resp.getWriter().printf("<h1>New GET request</h1>");
            resp.getWriter().printf(
                    "<h1>New GET request with parameters" +
                            " param1 = %s; " +
                            "param2 = %s; " +
                            "param3 = %s </h1>",
                    req.getParameter("param1"),
                    req.getParameter("param2"),
                    req.getParameter("param3")
            );
            if(req.getParameter("param3").equals("1")){
                resp.sendRedirect("https://vk.com/adbistju");
            } else if(req.getParameter("param3").equals("2")){
                resp.sendRedirect(req.getContextPath() + "/basic_servlet");
            } else if(req.getParameter("param3").equals("3")){
                getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New POST request");

        resp.getWriter().printf(
                "<h1>New POST request with body %s</h1>",
                readAllLines(req.getReader())
        );
    }

    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }
}
