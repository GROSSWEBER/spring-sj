package inc.monster.app.service;

import inc.monster.app.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface QuoteReactiveRepository extends ReactiveSortingRepository<Quote, Long> {

  Flux<Quote> findBy(Pageable pageable);
}
