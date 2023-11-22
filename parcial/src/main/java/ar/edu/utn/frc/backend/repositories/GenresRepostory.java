package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepostory extends JpaRepository<Genres, Long> {
}
