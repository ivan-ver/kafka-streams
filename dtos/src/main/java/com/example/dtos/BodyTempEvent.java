package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BodyTempEvent implements Vital {
    private String timeStamp;
    private double value;
    private String unit;
}
