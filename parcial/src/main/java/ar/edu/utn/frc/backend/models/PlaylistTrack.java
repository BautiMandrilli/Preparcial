package ar.edu.utn.frc.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "playlist_track")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PlaylistTrack {
    @Embeddable
    public static class PlaylistTrackId implements Serializable {
        @Column(name = "PlaylistId")
        private long playlistId;
        @Column(name = "TrackId")
        private long trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistTrackId that = (PlaylistTrackId) o;
        return getId().playlistId == that.playlistId &&
                getId().trackId == that.trackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId().playlistId, getId().trackId);
    }

    @EmbeddedId
    private PlaylistTrackId id;
}
