package org.example.pollenapi;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class Controller {

    private final PollenService pollenService;

    public Controller(PollenService pollenService) {
        this.pollenService = pollenService;
    }

    @GetMapping
    public Flux<Dto> getPollen() {
       return pollenService.getPollen();
    }
}
