package com.bda.parcial.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tracks")
public class Track {

    @Id
    @GeneratedValue(generator = "tracks")
    @TableGenerator(name = "tracks", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="tracks", allocationSize=1)

    @Column(name = "TrackId", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "AlbumId")
    private Long albumId;

    @Column(name = "MediaTypeId", nullable = false)
    private Long mediaTypeId;

    @Column(name = "GenreId")
    private Long genreId;

    @Column(name = "Composer")
    private String composer;

    @Column(name = "Milliseconds", nullable = false)
    private int milliSeconds;

    @Column(name = "Bytes")
    private int bytes;

    @Column(name = "UnitPrice", nullable = false)
    private float unitPrice;

    @ManyToMany(mappedBy = "tracks", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Playlist> playlists;

    public Track(Long id, String name, String composer, int milliSeconds) {
        this.id = id;
        this.name = name;
        this.composer = composer;
        this.milliSeconds = milliSeconds;

    }
}