import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigInteger;

public class StatelessFactorizerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("number");
        if (value == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing number parameter");
            return;
        }
        try {
            BigInteger number = new BigInteger(value);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().println("Number: " + number);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid integer");
        }
    }

}

