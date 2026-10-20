import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicLong;

import java.math.BigInteger;

public class BetterSafeServlet extends HttpServlet {

    private long count = 0;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized(this) {
            count++; 
        }
        response.getWriter().println("Count: " + count);
    }

}

