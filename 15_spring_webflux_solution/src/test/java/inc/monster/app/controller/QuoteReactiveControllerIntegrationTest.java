package inc.monster.app.controller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import inc.monster.app.domain.Quote;
import inc.monster.app.service.QuoteReactiveRepository;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class QuoteReactiveControllerIntegrationTest {

  @Autowired
  private QuoteReactiveRepository quoteReactiveRepository;

  @LocalServerPort
  private int serverPort;

  private WebClient webClient;

  @BeforeEach
  public void setUp() {
    this.webClient = WebClient.create("http://localhost:" + serverPort);
    quoteReactiveRepository.saveAll(Flux.just(
        new Quote(1L, "mock-book", "Quote 1"),
        new Quote(2L, "mock-book", "Quote 2"),
        new Quote(3L, "mock-book", "Quote 3"),
        new Quote(4L, "mock-book", "Quote 4"))
    ).blockLast();
  }

  @AfterEach
  public void tearDown() {
    quoteReactiveRepository.deleteAll().block();
  }

  @Test
  public void simpleGetRequest() {
    Flux<Quote> receivedFlux = webClient.get()
        .uri("/quotes").accept(MediaType.TEXT_EVENT_STREAM)
        .retrieve()
        .bodyToFlux(Quote.class);

    StepVerifier.create(receivedFlux)
        .expectNext(new Quote(1L, "mock-book", "Quote 1"))
        .expectNext(new Quote(2L, "mock-book", "Quote 2"))
        .expectNext(new Quote(3L, "mock-book", "Quote 3"))
        .expectNext(new Quote(4L, "mock-book", "Quote 4"))
        .expectComplete()
        .verify(Duration.ofSeconds(3));
  }

  @Test
  public void pagedGetRequest() {
    Flux<Quote> receivedFlux = webClient.get().uri("/quotes-paged?page=1&size=2")
        .accept(MediaType.TEXT_EVENT_STREAM)
        .retrieve()
        .bodyToFlux(Quote.class);

    StepVerifier.create(receivedFlux)
        .expectNext(new Quote(3L, "mock-book", "Quote 3"))
        .expectNext(new Quote(4L, "mock-book", "Quote 4"))
        .expectComplete()
        .verify();
  }

}
