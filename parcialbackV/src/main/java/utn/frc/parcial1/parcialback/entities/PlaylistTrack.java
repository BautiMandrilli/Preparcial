package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "playlist_track")
@AllArgsConstructor
@NoArgsConstructor

public class PlaylistTrack {
    @Id
    @Column(name = "PlaylistId", nullable = false)
    private Long playlistId;

    @Id
    @Column(name = "TrackId", nullable = false)
    private Long trackId;

}

