package ar.edu.utn.frc.backend.models.dto;

import lombok.Data;


@Data
public class TracksDto {
    private Long trackId;
    private String name;
    private String composer;
    private Long segundos;

}
