package org.example.pollenapi;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;


@Service
public class PollenService {

    private WebClient webClient;

    public PollenService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Dto> getPollen() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("forecasts")
                .queryParam("region_id", "2a2a2a2a-2a2a-4a2a-aa2a-2a2a2a303a32")
                .queryParam("current", "true")
                .build())
                .retrieve()
                .bodyToFlux(JsonNode.class)
                .map(json -> {
                    JsonNode items = json.path("items");
                    for (JsonNode item : items) {
                        JsonNode levelSeries = item.path("levelSeries");
                        for (JsonNode levelEntry : levelSeries) {
                            String pollenId = levelEntry.path("pollenId").asText();
                            if ("2a2a2a2a-2a2a-4a2a-aa2a-2a313a323332".equals(pollenId)) {
                                int level = levelEntry.path("level").asInt();
                                return new Dto(level);
                            }
                        }
                    }
                    return new Dto(99);
                });
    }
}

