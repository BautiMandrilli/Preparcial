package utn.frc.parcial1.parcialback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracks")
public class Tracks {
    @Id
    @GeneratedValue(generator = "tracks")
    @TableGenerator(name = "tracks", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="tracks",
            allocationSize=1)
    @Column(name = "TrackId", nullable = false)
    private Long trackId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "AlbumId")
    private Long albumId;

    @Column(name = "MediaTypeId", nullable = false)
    private Long mediaTypeId;

    @Column(name = "GenreId",nullable = false)
    private Long genreId;

    @Column(name = "Composer")
    private String composer;

    @Column(name = "Milliseconds", nullable = false)
    private int milliseconds;

    @Column(name = "Bytes")
    private int bytes;

    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;

    @ManyToMany(mappedBy="tracks",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Playlist> playlists;

    @OneToMany(mappedBy = "tracks", fetch = FetchType.LAZY)
    private List<InvoiceItems> invoiceLineId;
    public Tracks(Long trackId, String name, Long albumId, Long mediaTypeId, Long genreId,
                  int millisecond, double unitPrice, Set<Playlist> playlists, List<InvoiceItems> invoiceLineId) {
        this.trackId=trackId;
        this.name=name;
        this.albumId=albumId;
        this.mediaTypeId=mediaTypeId;
        this.genreId=genreId;
        this.milliseconds=millisecond;
        this.unitPrice=unitPrice;
        this.playlists=playlists;
        this.invoiceLineId=invoiceLineId;
    }
}
