package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "genres")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class Genres {
    @Id
    @GeneratedValue(generator = "genres")
    @TableGenerator(name = "genres", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="genres",
            allocationSize=1)
    @Column(name = "GenreId", nullable = false)
    private Long genreId;

    @Column(name = "Name")
    private String name;
}
