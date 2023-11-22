package com.bda.parcial.request;

import com.bda.parcial.entities.dto.PlaylistDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistCreationRequest {
    private String name;
    private Long customerId;
    private Long genreId;
    private String composerFilter;
    private int duracionMaxima;

}


