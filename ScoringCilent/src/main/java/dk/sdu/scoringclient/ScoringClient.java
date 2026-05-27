package dk.sdu.scoringclient;

import dk.sdu.commonscore.ScoreSPI;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScoringClient implements ScoreSPI {

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public void addScore(long points) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/score?point=" + points))
                    .GET()
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Score service not available");
        }
    }

    @Override
    public long getScore() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/score/current"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());
            return Long.parseLong(response.body());
        } catch (Exception e) {
            return 0L;
        }
    }
}