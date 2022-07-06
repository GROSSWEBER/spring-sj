package inc.monster.app.controller;

import inc.monster.app.domain.Quote;
import inc.monster.app.service.QuoteReactiveRepository;
import java.time.Duration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class QuoteReactiveController {

  private static final int DELAY_PER_ITEM_MS = 100;

  private final QuoteReactiveRepository quoteMongoReactiveRepository;

  public QuoteReactiveController(final QuoteReactiveRepository quoteReactiveRepository) {
    this.quoteMongoReactiveRepository = quoteReactiveRepository;
  }

  @GetMapping("/quotes")
  public Flux<Quote> getQuotes() {
    return quoteMongoReactiveRepository
        .findAll()
        .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
  }

  @GetMapping("/quotes-paged")
  public Flux<Quote> getQuotesPaged(final @RequestParam(name = "page") int page,
      final @RequestParam(name = "size") int size) {
    return quoteMongoReactiveRepository
        .findBy(PageRequest.of(page, size, Direction.ASC, "id"))
        .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
  }

}
