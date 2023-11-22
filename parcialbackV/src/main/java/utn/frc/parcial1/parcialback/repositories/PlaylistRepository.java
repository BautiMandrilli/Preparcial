package utn.frc.parcial1.parcialback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.parcial1.parcialback.entities.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
}
