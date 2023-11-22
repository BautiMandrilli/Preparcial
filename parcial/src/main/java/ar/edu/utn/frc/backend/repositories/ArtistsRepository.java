package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Artists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistsRepository extends JpaRepository <Artists, Long> {
}
