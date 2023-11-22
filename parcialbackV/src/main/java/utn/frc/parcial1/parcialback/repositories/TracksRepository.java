package utn.frc.parcial1.parcialback.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.parcial1.parcialback.entities.Tracks;

import java.util.List;

@Repository
public interface TracksRepository extends JpaRepository<Tracks, Long> {
    //List<Tracks> findByIdIn(List<Long> trackIds);
}
