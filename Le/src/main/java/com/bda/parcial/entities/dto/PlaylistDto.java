package com.bda.parcial.entities.dto;

import com.bda.parcial.entities.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {


    private Long id;
    private String name;
    private List<Track> tracks;
}
