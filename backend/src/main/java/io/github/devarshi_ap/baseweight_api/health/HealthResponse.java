/*
<PLATE>
- Response DTO (Data Transfer Object) is the shape of the answer.
- The class in here defines the exact JSON structure sent back to the client
- It's a Java record (class that's just data: you list the fields in one line, and Java generates the constructor, getters, equals and toString for you)

note: None of the three files (Controller/Service/Response) needs the public keyword [creates feature boundary]
*/

package io.github.devarshi_ap.baseweight_api.health;

// use Java record as transparent & simple data-holding class for Response DTO JSON
record HealthResponse(
    String status,
    Long systems
) {}