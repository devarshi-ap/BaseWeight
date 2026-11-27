/*
<KITCHEN>
- This is where the work happens and handles business logic.
- It talks to the DB but it knows nothing about HTTP like status codes & URL paths (so you can test it without starting a web server)

note: None of the three files (Controller/Service/Response) needs the public keyword [creates feature boundary]
*/
package io.github.devarshi_ap.baseweight_api.health;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.simple.JdbcClient;

@Service
class HealthService {
    private final JdbcClient jdbcClient;

    // Package-private constructor injection
    HealthService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    HealthResponse checkHealth() {
        long count = jdbcClient
                .sql("SELECT COUNT(*) FROM systems")
                .query(Long.class) // can use Long type for easy mapping from BIGINT returned by COUNT(*)
                .single();
        
        return new HealthResponse("ok", count);
    }
}
