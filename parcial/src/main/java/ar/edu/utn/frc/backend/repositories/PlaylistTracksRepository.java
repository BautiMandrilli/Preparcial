package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.PlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistTracksRepository extends JpaRepository <PlaylistTrack, Long> {
}
