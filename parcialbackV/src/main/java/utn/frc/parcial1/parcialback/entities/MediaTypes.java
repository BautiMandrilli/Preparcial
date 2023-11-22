package utn.frc.parcial1.parcialback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "media_types")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
public class MediaTypes {
    @Id
    @GeneratedValue(generator = "media_types")
    @TableGenerator(name = "media_types", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="media_types",
            allocationSize=1)
    @Column(name = "MediaTypeId",nullable = false)
    private Long MediaTypeId;

    @Column(name = "Name")
    private String name;
}
