package com.homecoming.backend.application.highlight;

import com.homecoming.backend.domain.exception.ResourceNotFoundException;
import com.homecoming.backend.domain.highlight.HomeHighlight;
import com.homecoming.backend.domain.highlight.HomeHighlightRepository;
import org.springframework.stereotype.Service;

@Service
public final class HomeHighlightQueryService {

    private final HomeHighlightRepository homeHighlightRepository;

    public HomeHighlightQueryService(HomeHighlightRepository homeHighlightRepository) {
        this.homeHighlightRepository = homeHighlightRepository;
    }

    public HomeHighlight getCurrent() {
        return homeHighlightRepository
                .findCurrent()
                .orElseThrow(() -> new ResourceNotFoundException("Home highlight is not configured"));
    }
}
