package com.bda.parcial.repositories;

import com.bda.parcial.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByIdIn(List<Long> trackIds);
    List<Track> findTracksByGenreId(Long genreId);

}
