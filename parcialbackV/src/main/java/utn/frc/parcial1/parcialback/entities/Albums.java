package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "albums")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class Albums {
    @Id
    @GeneratedValue(generator = "albums")
    @TableGenerator(name = "albums", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="albums",
            allocationSize=1)
    @Column(name = "AlbumId", nullable = false)
    private Long albumId;

    @Column(name = "Title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "ArtistId")
    private Artists artistId;
}
