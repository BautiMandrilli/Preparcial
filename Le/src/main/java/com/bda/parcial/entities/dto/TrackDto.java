package com.bda.parcial.entities.dto;


import com.bda.parcial.entities.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {

    private Long id;
    private String name;
    private String composer;
    private int milliSeconds;

}
