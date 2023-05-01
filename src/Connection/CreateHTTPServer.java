package Connection;

import DAO.UserDAO;
import com.google.gson.Gson;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class CreateHTTPServer {

    public static void Methots () {
        HttpHandler handler = Handlers.routing()
                .get("/users", new ListarUsersHandler())
                .get("/users/{id}", new ObtenerUserHandler())
                .post("/users", new CrearUserHandler())
                .put("/users/{id}", new ActualizarUserHandler())
                .delete("/users/{id}", new BorrarUserHandler());
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(handler)
                .build();
        server.start();
    }


    static class ListarUsersHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {


        }
    }

    static class ObtenerUserHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            UserDAO user = new UserDAO();
            Gson gson = new Gson();
            Integer id = Integer.parseInt(String.valueOf(exchange.getPathParameters().get("id")));
            user.leer(id);
            String json = gson.toJson(user);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send(json);

        }
    }

    static class CrearUserHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            // Implementar el método para crear una user
        }
    }

    static class ActualizarUserHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            // Implementar el método para actualizar una user
        }
    }

    static class BorrarUserHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            // Implementar el método para borrar una user
        }

    }
}
