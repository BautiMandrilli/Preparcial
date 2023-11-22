package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "artist")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class Artists {
    @Id
    @GeneratedValue(generator = "playlists")
    @TableGenerator(name = "playlists", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="playlists",
            allocationSize=1)
    @Column(name = "PlaylistId", nullable = false)
    private Long playlistId;

    @Column(name = "Name")
    private String name;
}
