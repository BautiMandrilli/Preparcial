package com.bda.parcial.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "playlists")
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(generator = "playlists")
    @TableGenerator(name = "playlists", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="playlists", allocationSize=1)

    @Column(name = "PlaylistId", nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="playlist_track",
            joinColumns = {@JoinColumn(name = "PlaylistId", referencedColumnName = "PlaylistId")},
            inverseJoinColumns = {@JoinColumn(name = "TrackId", referencedColumnName = "TrackId")})

    @JsonIgnore

    private List<Track> tracks;

    public Playlist(Long id, String  name, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.tracks = tracks;
    }

}
