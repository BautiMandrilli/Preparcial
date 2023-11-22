package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Playlists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistsRepository extends JpaRepository <Playlists, Long> {
}
