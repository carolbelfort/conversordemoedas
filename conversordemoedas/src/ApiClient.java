import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String API_KEY = "54e8d65cda7b5c7255c05573";

    public String buscarTaxas(String baseCurrency) {
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + baseCurrency;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept", "application/json")
                    .header("User-Agent", "Mozilla/5.0")
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.err.println("Erro na API. Status: " + response.statusCode());
                return null;
            }

        } catch (Exception e) {
            System.err.println("Erro ao acessar API: " + e.getMessage());
            return null;
        }
    }
}
