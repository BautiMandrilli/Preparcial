package utn.frc.parcial1.parcialback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "playlists")
@Data
@NoArgsConstructor

public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "playlists", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="playlists",
            allocationSize=1)
    @Column(name = "PlaylistId", nullable = false)
    private Long playlistId;

    @Column(name = "Name")
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="playlist_track",
            joinColumns = {@JoinColumn(name = "PlaylistId", referencedColumnName = "PlaylistId")},
            inverseJoinColumns = {@JoinColumn(name = "TrackId", referencedColumnName = "TrackId")})
    @JsonIgnore
    private List<Tracks> tracks;
    public Playlist(Long playListId, String name, List<Tracks> tracks) {
        this.playlistId=playListId;
        this.name=name;
        this.tracks=tracks;

    }
}