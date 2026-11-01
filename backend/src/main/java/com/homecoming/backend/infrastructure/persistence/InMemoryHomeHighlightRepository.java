package com.homecoming.backend.infrastructure.persistence;

import com.homecoming.backend.domain.highlight.HomeHighlight;
import com.homecoming.backend.domain.highlight.HomeHighlightRepository;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("inmemory")
class InMemoryHomeHighlightRepository implements HomeHighlightRepository {

    @Override
    public Optional<HomeHighlight> findCurrent() {
        return Optional.of(DemoScheduleSeed.homeHighlight());
    }
}
