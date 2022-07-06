package inc.monster.app.controller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import inc.monster.app.domain.Quote;
import inc.monster.app.service.QuoteReactiveRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import reactor.core.publisher.Flux;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class QuoteReactiveControllerIntegrationTest {

  @Autowired
  private QuoteReactiveRepository quoteReactiveRepository;

  @LocalServerPort
  private int serverPort;

  @BeforeEach
  public void setUp() {
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

}
