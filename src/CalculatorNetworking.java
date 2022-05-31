import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CalculatorNetworking {
    private String baseUrl;
    private String ApiKey;

    public CalculatorNetworking()
    {
        //https://api.mathjs.org/
        baseUrl = "http://api.mathjs.org/v4/";

    }
    public String makeAPICallForCurrent(String expression)
    {
        String url = baseUrl + "?expr=" + expression;
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
