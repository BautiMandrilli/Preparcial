package utn.frc.parcial1.parcialback.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.parcial1.parcialback.entities.Tracks;

import javax.sound.midi.Track;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {
    private Long playListId;
    private String name;
    private List<Tracks> trackId;

}
