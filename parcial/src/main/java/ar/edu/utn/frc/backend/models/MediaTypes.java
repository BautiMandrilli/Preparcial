package ar.edu.utn.frc.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "media_types")
@Getter
@NoArgsConstructor

public class MediaTypes {

    @Column(name = "Name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaTypeId")
    private long mediaTypeId;
}
