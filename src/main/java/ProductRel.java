import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "First", urlPatterns = "/first")
public class ProductRel extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(ProductRel.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListProd listProd = new ListProd();
        logger.info("New GET request");
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.getWriter().printf(
                "<h3>Ваш запрос по параметрам: " +
                        " id = %s; " +
                        "name = %s; " +
                        "</h3>",
                req.getParameter("id"),
                req.getParameter("name")
        );
        resp.getWriter().printf(
                "<h3>"+"Id"+" || "+"Title"+" || "+"Cost"+"</h3>"
        );
        for (int i = 0; i < listProd.getProductList().size(); i++) {
            resp.getWriter().printf(
                    "<h3>"+listProd.getProductIndex(i).getId()+" || "+listProd.getProductIndex(i).getTitle()+" || "+listProd.getProductIndex(i).getCost()+"</h3>"
            );
        }

        resp.getWriter().printf(
                "<hr>"
                        +"<p>Информация о продукте по id</p>"
                +"<h3>"
                        +listProd.getProductIndex(Integer.parseInt(req.getParameter("id"))).getId()
                        +" || "+listProd.getProductIndex(Integer.parseInt(req.getParameter("id"))).getTitle()
                        +" || "+listProd.getProductIndex(Integer.parseInt(req.getParameter("id"))).getCost()
                        +"</h3>"
        );
        int index = listProd.getSearchProductIndex(req.getParameter("name")).getId();
        //resp.getWriter().printf("<p>"+index+"</p>");
        resp.getWriter().printf(
                "<hr>"
                        +"<p>Информация о продукте по имени</p>"
                        +"<h3>"+listProd.getProductIndex(index).getId()
                        +" || "+listProd.getProductIndex(index).getTitle()
                        +" || "+listProd.getProductIndex(index).getCost()
                        +"</h3>"
        );
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
