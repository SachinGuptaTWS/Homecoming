package com.homecoming.backend.infrastructure.persistence.jpa;

import com.homecoming.backend.domain.highlight.HomeHighlight;
import com.homecoming.backend.domain.highlight.HomeHighlightRepository;
import com.homecoming.backend.infrastructure.persistence.jpa.entity.HomeHighlightEntity;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!inmemory")
class JpaHomeHighlightRepository implements HomeHighlightRepository {

    private final HomeHighlightEntityJpaRepository homeHighlightEntityJpaRepository;

    JpaHomeHighlightRepository(HomeHighlightEntityJpaRepository homeHighlightEntityJpaRepository) {
        this.homeHighlightEntityJpaRepository = homeHighlightEntityJpaRepository;
    }

    @Override
    public Optional<HomeHighlight> findCurrent() {
        return homeHighlightEntityJpaRepository
                .findById(HomeHighlightEntity.SINGLETON_KEY)
                .map(SchedulePersistenceMapper::toDomain);
    }
}
