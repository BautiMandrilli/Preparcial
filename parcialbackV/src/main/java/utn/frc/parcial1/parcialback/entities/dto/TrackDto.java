package utn.frc.parcial1.parcialback.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.parcial1.parcialback.entities.InvoiceItems;
import utn.frc.parcial1.parcialback.entities.Playlist;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    private Long trackId;
    private String name;
    private Long albumId;
    private Long mediaTypeId;
    private Long genreId;
    private int millisecond;
    private double unitPrice;
    private Set<Playlist> playlists;
    private List<InvoiceItems> invoiceLineId;
}
