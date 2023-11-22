package com.bda.parcial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlist_track")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlaylistTrack {
    @Id
    @GeneratedValue(generator = "playlist_track")
    @TableGenerator(name = "playlist_track", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="playlist_track", allocationSize=1)
    
    @Column(name = "PlaylistId")
    private long id;

    @Column(name = "TrackId")
    private Long trackId;

}
