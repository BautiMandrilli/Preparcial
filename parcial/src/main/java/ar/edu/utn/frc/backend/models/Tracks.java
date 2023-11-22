package ar.edu.utn.frc.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tracks")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Tracks {

    @Column(name = "Name")
    private String name;

    @Column(name = "AlbumId")
    private long albumId;

    @Column(name = "MediaTypeId")
    private long mediaTypeId;

    @Column(name = "GenreId")
    private long genreId;

    @Column(name = "Composer")
    private String composer;

    @Column(name = "Milliseconds")
    private long milliseconds;

    @Column(name = "Bytes")
    private long bytes;

    @Column(name = "UnitPrice")
    private double unitPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrackId")
    private long trackId;
}
