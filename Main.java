import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Запускаем сервер на порту 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Создаем endpoint /habits
        server.createContext("/habits", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "{\"habits\": [\"Пить воду\", \"Читать книгу\", \"Делать зарядку\"]}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // Создаем endpoint /health для проверки, что сервер жив
        server.createContext("/health", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "OK";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.setExecutor(null); 
        server.start();
        System.out.println("Сервер запущен на порту 8080...");
    }
}