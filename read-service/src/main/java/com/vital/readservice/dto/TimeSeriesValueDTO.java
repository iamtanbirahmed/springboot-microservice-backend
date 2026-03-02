package com.vital.readservice.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TimeSeriesValueDTO {

    private Long time;
    private Long value;

}
