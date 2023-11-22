package ar.edu.utn.frc.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "albums")
@Getter
@NoArgsConstructor

public class Albums {

    @Column(name = "Title")
    private String title;

    @Column(name = "ArtisId")
    private long artistId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumId")
    private long albumId;
}
