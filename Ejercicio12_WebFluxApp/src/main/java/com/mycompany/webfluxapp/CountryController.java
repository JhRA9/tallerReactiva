import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class CountryController {

    @GetMapping("/countries")
    public Flux<String> getCountries() {
        String[] countries = {"Colombia", "Argentina", "México", "Brasil", "Chile", "Perú"};

        return Flux.fromArray(countries)
                .delayElements(Duration.ofSeconds(1)); // Retardo de 1 segundo entre elementos
    }
}