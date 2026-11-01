package com.homecoming.backend.infrastructure.persistence;

import com.homecoming.backend.domain.highlight.HomeHighlight;
import com.homecoming.backend.domain.highlight.HomeHighlightRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryHomeHighlightRepository implements HomeHighlightRepository {

    @Override
    public Optional<HomeHighlight> findCurrent() {
        return Optional.of(DemoScheduleSeed.homeHighlight());
    }
}
