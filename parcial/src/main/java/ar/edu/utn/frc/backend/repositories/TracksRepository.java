package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Tracks;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TracksRepository extends JpaRepository <Tracks, Long> {
    Optional<Tracks> findByTrackIdAndGenreId(Long trackId, Long genreId);
    List<Tracks> findByTrackIdIn(List<Long> trackIds);
}
