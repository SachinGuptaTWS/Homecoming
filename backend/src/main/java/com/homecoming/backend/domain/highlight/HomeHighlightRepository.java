package com.homecoming.backend.domain.highlight;

import java.util.Optional;

public interface HomeHighlightRepository {

    Optional<HomeHighlight> findCurrent();
}
