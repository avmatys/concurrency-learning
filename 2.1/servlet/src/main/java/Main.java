import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080); 
        ServletContextHandler context = new ServletContextHandler(); 
        context.setContextPath("/"); 
        context.addServlet( new ServletHolder(new StatelessFactorizerServlet()), "/stateless" ); 
        context.addServlet( new ServletHolder(new UnsafeCountServlet()), "/unsafecount" ); 
        context.addServlet( new ServletHolder(new SlowSafeServlet()), "/safeslow" ); 
        context.addServlet( new ServletHolder(new BetterSafeServlet()), "/safebetter" ); 
        server.setHandler(context); 
        server.start(); 
        System.out.println("http://localhost:8080/stateless");
        System.out.println("http://localhost:8080/unsafecount");
        System.out.println("http://localhost:8080/safeslow");
        System.out.println("http://localhost:8080/safebetter");
    }
}
