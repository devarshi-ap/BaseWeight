/*
<WAITER>
- The controller handles HTTP routing, delegates work to the service via constructor injection
- It must not contain: SQL or business logic

note: None of the three files (Controller/Service/Response) needs the public keyword [creates feature boundary]
*/
package io.github.devarshi_ap.baseweight_api.health;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/health")
class HealthController {
    private final HealthService healthService;

    // Package-private constructor injection
    HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping
    HealthResponse getHealth() {
        return healthService.checkHealth();
    }
}
