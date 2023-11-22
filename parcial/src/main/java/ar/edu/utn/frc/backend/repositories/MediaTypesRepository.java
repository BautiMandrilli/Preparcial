package ar.edu.utn.frc.backend.repositories;

import ar.edu.utn.frc.backend.models.MediaTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaTypesRepository extends JpaRepository<MediaTypes, Long> {
}
